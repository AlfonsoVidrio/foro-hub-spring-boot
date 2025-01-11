package com.alfonsovidrio.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByOrderByCreationDateDesc(Pageable pageable);

    boolean existsByTitleAndMessage(@NotBlank String title, String message);
}
