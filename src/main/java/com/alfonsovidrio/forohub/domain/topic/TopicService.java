package com.alfonsovidrio.forohub.domain.topic;

import com.alfonsovidrio.forohub.domain.course.Course;
import com.alfonsovidrio.forohub.domain.course.CourseRepository;
import com.alfonsovidrio.forohub.domain.topic.validations.Validations;
import com.alfonsovidrio.forohub.domain.user.User;
import com.alfonsovidrio.forohub.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private List<Validations> validations;

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

        validations.forEach(v -> v.validate(topicData.title(), topicData.message()));

        Topic topic = new Topic(topicData);
        topic.setUser(user);
        topic.setCourse(course);

        return topicRepository.save(topic);
    }

    public Page<GetTopicData> listTopics(Pageable pageable) {
        Page<Topic> topics = topicRepository.findAllByOrderByCreationDateDesc(pageable);
        return topics.map(GetTopicData::new);
    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));
    }

    public void updateTopic(@Valid UpdateTopicData updateTopicData) {
        validations.forEach(v -> v.validate(updateTopicData.title(), updateTopicData.message()));
        Topic topic = getTopic(updateTopicData.id());
        topic.update(updateTopicData);
        topicRepository.save(topic);
    }

    public void deleteTopic(Topic topic) {
        topicRepository.delete(topic);
    }
}