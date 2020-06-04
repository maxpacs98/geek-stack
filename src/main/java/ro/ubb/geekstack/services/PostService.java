package ro.ubb.geekstack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.geekstack.iterators.IIterator;
import ro.ubb.geekstack.iterators.PostIterator;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.models.Post;
import ro.ubb.geekstack.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<Post> res = new ArrayList<>();
        IIterator<Post> iterator = new PostIterator(postRepository.getAll());
        while(!iterator.isFinished()) {
            Post current = iterator.getCurrent();
            if(!current.isDeleted()) {
                res.add(iterator.getCurrent());
            }
            iterator.next();
        }
        return res;
    }

    public Long addPost(Post post) {
        return postRepository.save(post).getId();
    }

    public List<Long> bulkInsert(List<Post> posts) {
        List<Long> ids = new ArrayList<>();
        List<Post> saved = postRepository.saveAll(posts);
        saved.forEach(c -> ids.add(c.getId()));
        return ids;
    }

    public Long count() {
        return this.postRepository.count();
    }

    public void clear() {
        postRepository.deleteAll();
    }

    @Transactional
    public Long updateComment(Long postId, Comment comment) {
        Optional<Post> res = postRepository.findById(postId);
        if (res.isPresent()) {
            Post post = res.get();
            post.setComments(post.getComments().stream().peek(c -> {
                if (c.getId().equals(comment.getId())) {
                    c.setLikes(comment.getLikes());
                    c.setAuthor(comment.getAuthor());
                    c.setTimestamp(comment.getTimestamp());
                    c.setText(comment.getText());
                }
            }).collect(Collectors.toList()));
            return postId;
        }
        return null;
    }
}
