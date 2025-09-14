package org.example.seminar.repository;

import org.example.seminar.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}
