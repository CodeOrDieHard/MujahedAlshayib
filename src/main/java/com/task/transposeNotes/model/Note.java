package com.task.transposeNotes.model;

import lombok.*;

@Data
public class Note {
    private int octave;
    private int noteNumber;

    public Note() {}

    public Note(int octave, int noteNumber) {
        this.octave = octave;
        this.noteNumber = noteNumber;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }
}
