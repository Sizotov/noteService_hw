package com.example.noteService_hw.service;

import com.example.noteService_hw.aspect.TrackUserAction;
import com.example.noteService_hw.model.Note;
import com.example.noteService_hw.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
//Применяем аннотацию @TrackUserAction для всех методов service
    @Override
    @TrackUserAction
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    @TrackUserAction
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id) ;
    }

    @Override
    @TrackUserAction
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    @TrackUserAction
    public Note updateNote(Long id, Note updatedNote) {
        // Получение существующей заметки по идентификатору.
        Optional<Note> existingNoteOptional = getNoteById(id);
        // Проверка наличия существующей заметки в Optional.
        if (existingNoteOptional.isPresent()) {
            // Извлечение объекта Note из Optional для обновления.
            Note existingNote = existingNoteOptional.get();
            // Обновление заголовка и содержимого заметки
            existingNote.setTitle(updatedNote.getTitle());
            existingNote.setContent(updatedNote.getContent());
            // Сохранение обновленной заметки в репозитории и возврат обновленной заметки
            return noteRepository.save(existingNote);
        } else {
            return null;
        }
    }

    @Override
    @TrackUserAction
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);

    }
}
