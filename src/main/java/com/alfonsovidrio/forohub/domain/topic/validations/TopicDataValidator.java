package com.alfonsovidrio.forohub.domain.topic.validations;

import com.alfonsovidrio.forohub.domain.topic.TopicData;
import org.springframework.stereotype.Component;

@Component
public class TopicDataValidator implements Validations {
    public void validate(String title, String message) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be blank");
        }
    }
}