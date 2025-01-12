package com.alfonsovidrio.forohub.domain.reply;

import java.time.LocalDateTime;

public record GetReplyData(
        Long id,
        String message,
        String userName,
        String topicTitle,
        LocalDateTime creationDate
) {
    public GetReplyData(Reply reply) {
        this(reply.getId(), reply.getMessage(), reply.getUser().getName(), reply.getTopic().getTitle(), reply.getCreationDate());
    }
}
