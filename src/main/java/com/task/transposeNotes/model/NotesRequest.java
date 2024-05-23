package com.task.transposeNotes.model;

import lombok.*;

import java.util.List;
@Data
@ToString
public class NotesRequest {
    private List<Note> notes;
    private int semitone;

}
