package com.alfonsovidrio.forohub.domain.topic;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String userName,
        String courseName,
        String status
) {
    public TopicResponse(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getUser().getName(), topic.getCourse().getName(), topic.getStatus().name());
    }
}