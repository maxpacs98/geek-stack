package ro.ubb.geekstack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        List<Comment> res = commentRepository.findAll();
        return res;
    }

    public Long addComment(Comment c) {
        return commentRepository.save(c).getId();
    }

    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long updateComment(Long id, Comment c) {
        Optional<Comment> res = commentRepository.findById(id);
        if (res.isPresent()) {
            Comment com = res.get();
            com.setText(c.getText());
            com.setTimestamp(c.getTimestamp());
            com.setAuthor(c.getAuthor());
            com.setLikes(c.getLikes());
            return id;
        }
        return null;
    }

    public List<Long> bulkInsert(List<Comment> comments) {
        List<Long> ids = new ArrayList<>();
        List<Comment> saved = commentRepository.saveAll(comments);
        saved.forEach(c -> ids.add(c.getId()));
        return ids;
    }

    public List<Long> bulkDelete(List<Long> commentIds) {
        commentRepository.bulkDelete(commentIds);
        return commentIds;
    }

    public void clear() {
        commentRepository.deleteAll();
    }

    public Long count() {
        return commentRepository.count();
    }

    public Comment getOne(Long id) {
        Optional<Comment> res = commentRepository.findById(id);
        return res.orElse(null);
    }
}
