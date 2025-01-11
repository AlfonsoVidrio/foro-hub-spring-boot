package com.alfonsovidrio.forohub.domain.topic;

import com.alfonsovidrio.forohub.domain.course.Course;
import com.alfonsovidrio.forohub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Topic(TopicData topicData) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.creationDate = LocalDateTime.now();
        this.status = Status.OPEN;
    }


    public void update(@Valid UpdateTopicData updateTopicData) {
        if (!this.id.equals(updateTopicData.id())) {
            throw new IllegalArgumentException("Topic id does not match");
        }
        if (updateTopicData.title() != null) {
            this.title = updateTopicData.title();
        }
        if (updateTopicData.message() != null) {
            this.message = updateTopicData.message();
        }
    }
}