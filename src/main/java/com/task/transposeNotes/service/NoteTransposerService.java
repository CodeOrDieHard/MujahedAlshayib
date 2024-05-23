package com.task.transposeNotes.service;

import com.task.transposeNotes.controller.TransposeController;
import com.task.transposeNotes.model.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteTransposerService implements INoteTransposerService{
    private static final Logger logger = LogManager.getLogger(NoteTransposerService.class);

    private static final int MIN_SEMITONE = -33;
    private static final int MAX_SEMITONE = 61;

    @Override
    public List<Note> transposeNotes(List<Note> notes, int semitone) {
        List<Note> transposedNotes = new ArrayList<>();
        for (Note note : notes) {
            int totalSemitone = note.getOctave() * 12 + note.getNoteNumber() + semitone;
            if (totalSemitone < MIN_SEMITONE || totalSemitone > MAX_SEMITONE) {
                logger.error("Transposed note out of keyboard range: [{} {}]", note.getOctave(), note.getNoteNumber());

                throw new IllegalArgumentException("Transposed note out of keyboard range");
            }
            int newOctave = totalSemitone / 12;
            int newNote = totalSemitone % 12;
            if (newNote < 0) {
                newNote += 12;
                newOctave -= 1;
            }
            transposedNotes.add(new Note(newOctave, newNote));
        }
        return transposedNotes;
    }
}