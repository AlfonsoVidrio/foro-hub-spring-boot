package com.alfonsovidrio.forohub.domain.reply;

import java.time.LocalDateTime;

public record ReplyResponse(
        Long id,
        String message,
        LocalDateTime creationDate,
        String userName,
        String topicTitle
) {

    public ReplyResponse(Reply reply) {
        this(reply.getId(), reply.getMessage(), reply.getCreationDate(), reply.getUser().getName(), reply.getTopic().getTitle());
    }
}
