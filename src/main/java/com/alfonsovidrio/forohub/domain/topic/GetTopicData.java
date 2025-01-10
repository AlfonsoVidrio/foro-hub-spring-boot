package com.alfonsovidrio.forohub.domain.topic;

import java.time.LocalDateTime;

public record GetTopicData(
        Long id,
        String title,
        String message,
        String username,
        String courseName,
        String status,
        LocalDateTime creationDate
) {
    public GetTopicData(Topic topic) {
        this(topic.getId() , topic.getTitle(), topic.getMessage(), topic.getUser().getName(), topic.getCourse().getName(), topic.getStatus().name(), topic.getCreationDate());
    }
}
