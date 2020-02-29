package ro.ubb.geekstack.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.geekstack.models.Comment;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends GeekStackRepository<Comment, Long> {
    @Transactional
    @Modifying
    @Query("delete from Comment c where c.id in ?1")
    void bulkDelete(List<Long> commentIds);
}
