package com.alfonsovidrio.forohub.domain.reply;

import com.alfonsovidrio.forohub.domain.topic.Topic;
import com.alfonsovidrio.forohub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Reply(ReplyData replyData) {
        this.message = replyData.message();
        this.creationDate = LocalDateTime.now();
    }

    public void update(@Valid UpdateReplyDate updateReplyData) {
        if (!this.id.equals(updateReplyData.id())) {
            throw new IllegalArgumentException("Reply id does not match");
        }
        if (updateReplyData.message() != null) {
            this.message = updateReplyData.message();
        }


    }
}
