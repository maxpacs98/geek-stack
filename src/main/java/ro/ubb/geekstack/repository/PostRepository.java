package ro.ubb.geekstack.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.geekstack.models.Post;

import java.util.List;


public interface PostRepository extends GeekStackRepository<Post, Long> {
    @Query(value = "SELECT p FROM Post p")
    @EntityGraph(value = "PostWithComments")
    List<Post> getAll();
}
