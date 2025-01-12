package com.alfonsovidrio.forohub.controller;

import com.alfonsovidrio.forohub.domain.topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(new TopicResponse(topicService.getTopic(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponse> update(@RequestBody @Valid UpdateTopicData updateTopicData) {
        topicService.updateTopic(updateTopicData);
        return ResponseEntity.ok(new TopicResponse(topicService.getTopic(updateTopicData.id())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Topic topic = topicService.getTopic(id);
        topicService.deleteTopic(topic);
        return ResponseEntity.noContent().build();
    }

}