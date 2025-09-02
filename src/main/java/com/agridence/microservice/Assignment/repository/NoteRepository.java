package com.agridence.microservice.Assignment.repository;


import com.agridence.microservice.Assignment.dto.NoteResponse;
import com.agridence.microservice.Assignment.entity.Note;
import com.agridence.microservice.Assignment.entity.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByIdAndUserId(Long id, Long userId);

    Page<Note> findByUserId(Long userId, Pageable pageable);

}