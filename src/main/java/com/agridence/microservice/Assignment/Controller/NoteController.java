package com.agridence.microservice.Assignment.Controller;


import com.agridence.microservice.Assignment.dto.NoteRequest;
import com.agridence.microservice.Assignment.dto.NoteResponse;
import com.agridence.microservice.Assignment.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest request,
                                                   Authentication authentication) {
        NoteResponse note = noteService.createNote(request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping
    public ResponseEntity<Page<NoteResponse>> getUserNotes(
            Authentication authentication,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NoteResponse> notes = noteService.getUserNotes(authentication);
        return ResponseEntity.ok(notes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id,
                                                    Authentication authentication) {
        NoteResponse note = noteService.getNoteById(id, authentication);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id,
                                        Authentication authentication) {
        noteService.deleteNote(id, authentication);
        return ResponseEntity.noContent().build();
    }
}