package com.alfonsovidrio.forohub.controller;

import com.alfonsovidrio.forohub.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> create(@RequestBody @Valid TopicData topicData) {
        Topic topic = topicService.createTopic(topicData);
        return ResponseEntity.ok(new TopicResponse(topic));
    }

    @GetMapping
    public ResponseEntity<Page<GetTopicData>> list(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(topicService.listTopics(pageable));
    }
}