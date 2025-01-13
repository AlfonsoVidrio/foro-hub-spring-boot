package com.alfonsovidrio.forohub.domain.reply;

import com.alfonsovidrio.forohub.domain.topic.Topic;
import com.alfonsovidrio.forohub.domain.topic.TopicRepository;
import com.alfonsovidrio.forohub.domain.user.User;
import com.alfonsovidrio.forohub.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    public ReplyService(ReplyRepository replyRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public Reply createReply(ReplyData replyData) {
        User user = userRepository.findById(replyData.userId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Topic topic = topicRepository.findById(replyData.topicId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));

        Reply reply = new Reply(replyData);
        reply.setUser(user);
        reply.setTopic(topic);

        return replyRepository.save(reply);
    }

    public Page<GetReplyData> listReplies(Pageable pageable) {
        Page<Reply> replies = replyRepository.findAllByOrderByCreationDateDesc(pageable);
        return replies.map(GetReplyData::new);
    }


    public Reply getReply(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reply ID"));
    }

    public void updateReply(@Valid UpdateReplyDate updateReplyData) {
        Reply reply = getReply(updateReplyData.id());
        reply.update(updateReplyData);
    }

    public void deleteReply(Reply reply) {
        replyRepository.delete(reply);
    }
}
