package com.example.noteService_hw.controller;

import com.example.noteService_hw.model.Note;
import com.example.noteService_hw.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")

public class NoteController {
    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Note addNote(@RequestBody Note note){
        return noteService.addNote(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        // Получение существующей заметки по ее идентификатору
        Optional<Note> existingNote = noteService.getNoteById(id);
        // Проверка наличия существующей заметки в Optional.
        if (existingNote.isPresent()) {
            // Если заметка существует, обновляем ее заголовок и содержимое
            existingNote.get().setTitle(updatedNote.getTitle());
            existingNote.get().setContent(updatedNote.getContent());
            //Вызов метода noteService.updateNote для выполнения обновления заметки
            //Возвращает обновленную заметку
            Note savedNote = noteService.updateNote(id, existingNote.orElseThrow(null));
            // Возвращаем обновленную заметку со статусом 200 (OK).
            return new ResponseEntity<>(savedNote, HttpStatus.OK);
        } else {
            // Возвращаем статус 404, если заметка не найдена
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
