package com.alfonsovidrio.forohub.controller;

import com.alfonsovidrio.forohub.domain.reply.*;
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
@RequestMapping("/replies")
@SecurityRequirement(name = "bearer-key")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReplyResponse> create(@RequestBody @Valid ReplyData replyData) {
        Reply reply = replyService.createReply(replyData);
        return ResponseEntity.ok(new ReplyResponse(reply));
    }

    @GetMapping
    public ResponseEntity<Page<GetReplyData>> list(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(replyService.listReplies(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(new ReplyResponse(replyService.getReply(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ReplyResponse> update(@RequestBody @Valid UpdateReplyDate updateReplyData) {
        replyService.updateReply(updateReplyData);
        return ResponseEntity.ok(new ReplyResponse(replyService.getReply(updateReplyData.id())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Reply reply = replyService.getReply(id);
        replyService.deleteReply(reply);
        return ResponseEntity.noContent().build();
    }
}
