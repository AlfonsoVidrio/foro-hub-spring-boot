package com.alfonsovidrio.forohub.domain.topic;

import com.alfonsovidrio.forohub.domain.course.Course;
import com.alfonsovidrio.forohub.domain.course.CourseRepository;
import com.alfonsovidrio.forohub.domain.user.User;
import com.alfonsovidrio.forohub.domain.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Topic createTopic(TopicData topicData) {
        User user = userRepository.findById(topicData.userId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Course course = courseRepository.findById(topicData.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        Topic topic = new Topic(topicData);
        topic.setUser(user);
        topic.setCourse(course);

        return topicRepository.save(topic);
    }

    public Page<GetTopicData> listTopics(Pageable pageable) {
        Page<Topic> topics = topicRepository.findAllByOrderByCreationDateDesc(pageable);
        return topics.map(GetTopicData::new);
    }
}