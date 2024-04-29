package com.example.noteService_hw.service;

import com.example.noteService_hw.model.Note;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    List<Note> getAllNotes();
    Optional<Note> getNoteById(Long id);
    Note addNote(Note note);
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);


}
