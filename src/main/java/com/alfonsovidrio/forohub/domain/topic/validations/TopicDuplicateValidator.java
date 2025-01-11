package com.alfonsovidrio.forohub.domain.topic.validations;

import com.alfonsovidrio.forohub.domain.topic.TopicData;
import com.alfonsovidrio.forohub.domain.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicDuplicateValidator implements Validations {
    @Autowired
    private TopicRepository topicRepository;

    public void validate(String title, String message) {
        if (topicRepository.existsByTitleAndMessage(title, message)) {
            throw new IllegalArgumentException("Topic already exists");
        }
    }
}
