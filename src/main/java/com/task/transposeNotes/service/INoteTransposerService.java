package com.task.transposeNotes.service;

import com.task.transposeNotes.model.Note;
import com.task.transposeNotes.model.NotesRequest;

import java.util.List;

public interface INoteTransposerService {
    public List<Note> transposeNotes(List<Note> notes, int semitone);
}
