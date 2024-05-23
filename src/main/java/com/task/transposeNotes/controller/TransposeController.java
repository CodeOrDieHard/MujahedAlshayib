package com.task.transposeNotes.controller;

import com.task.transposeNotes.model.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.task.transposeNotes.service.NoteTransposerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transpose")
public class TransposeController {
    private static final Logger logger = LogManager.getLogger(TransposeController.class);

    @Autowired
    private NoteTransposerService transposerService;

    @GetMapping("/file")
    public ResponseEntity<?> transposeNotesFromFile(
            @RequestParam String inputFile,
            @RequestParam int semitone,
            @RequestParam String outputFile) {

        try {
            logger.info("Reading input file: {}", inputFile);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<int[]>>() {
            }.getType();
            FileReader reader = new FileReader(inputFile);
            List<int[]> noteArrays = gson.fromJson(reader, listType);
            reader.close();
            // Convert list of int arrays to a readable string format to check Notes inputs for debugging purpose
            String noteArraysString = noteArrays.stream()
                    .map(Arrays::toString)
                    .collect(Collectors.joining(", "));
            logger.info("NoteArrays: [{}]", noteArraysString);
            // [$octaveNumber, $noteNumber]

            List<Note> notes = new ArrayList<>();
            for (int[] noteArray : noteArrays) {
                notes.add(new Note(noteArray[0], noteArray[1]));
            }
            logger.info("Transposing notes by {} semitones", semitone);

            List<Note> transposedNotes = transposerService.transposeNotes(notes, semitone);

            List<int[]> transposedNoteArrays = new ArrayList<>();
            for (Note note : transposedNotes) {
                transposedNoteArrays.add(new int[]{note.getOctave(), note.getNoteNumber()});
            }
            logger.info("Writing transposed notes to output file: {}", outputFile);

            FileWriter writer = new FileWriter(outputFile);
            gson.toJson(transposedNoteArrays, writer);
            writer.close();
            logger.info("Transposition complete. Output written to {}", outputFile);
            return ResponseEntity.ok("Transposed notes written to " + outputFile);
        } catch (IOException e) {
            logger.error("Error reading or writing files: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error reading or writing files: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
