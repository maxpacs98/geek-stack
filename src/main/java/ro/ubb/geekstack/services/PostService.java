package ro.ubb.geekstack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.geekstack.models.Post;
import ro.ubb.geekstack.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts() {

        List<Post> res = postRepository.getAll();
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

}
