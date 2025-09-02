package com.agridence.microservice.Assignment.service;


import com.agridence.microservice.Assignment.dto.NoteRequest;
import com.agridence.microservice.Assignment.dto.NoteResponse;
import com.agridence.microservice.Assignment.entity.Note;
import com.agridence.microservice.Assignment.entity.User;
import com.agridence.microservice.Assignment.exception.ResourceNotFoundException;
import com.agridence.microservice.Assignment.repository.NoteRepository;
import com.agridence.microservice.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public NoteResponse createNote(NoteRequest request, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setUser(user);

        Note savedNote = noteRepository.save(note);
        return mapToResponse(savedNote);
    }



    public Page<NoteResponse> getUserNotes(Authentication authentication, Pageable pageable) {
        User user = getUserFromAuthentication(authentication);
        Page<Note> notes = noteRepository.findByUserId(user.getId(), pageable);
        return notes.map(this::mapToResponse);
    }

    public NoteResponse getNoteById(Long id, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        return mapToResponse(note);
    }

    public void deleteNote(Long id, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        noteRepository.delete(note);
    }

    private User getUserFromAuthentication(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private NoteResponse mapToResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}