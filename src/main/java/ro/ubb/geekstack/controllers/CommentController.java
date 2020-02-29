package ro.ubb.geekstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.geekstack.dtos.CommentInputDto;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.services.CommentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<Comment> getComments() {
        return commentService.getAllComments();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Long addComment(@RequestBody CommentInputDto comment) {
        // TODO: See if we can generify
        Comment c = Comment.builder().author(comment.getAuthor()).likes(comment.getLikes()).text(comment.getText()).build();
        c.setTimestamp(new Date());
        return commentService.addComment(c);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    ResponseEntity<Long> updateComment(@PathVariable Long id, @RequestBody CommentInputDto comment) {
        Comment c = Comment.builder().author(comment.getAuthor()).likes(comment.getLikes()).text(comment.getText()).build();
        c.setTimestamp(new Date());
        Long updated = commentService.updateComment(id, c);
        if (updated != null) {
            return ResponseEntity.ok().body(updated);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/comments/bulk", method = RequestMethod.POST)
    List<Long> bulkInsert(@RequestBody List<CommentInputDto> comments) {
        List<Comment> toInsert = new ArrayList<>();
        comments.forEach(comment -> {
            Comment c = Comment.builder().author(comment.getAuthor()).likes(comment.getLikes()).text(comment.getText()).build();
            c.setTimestamp(new Date());
            toInsert.add(c);
        });
        return commentService.bulkInsert(toInsert);
    }

    @RequestMapping(value = "/comments/delete", method = RequestMethod.DELETE)
    List<Long> bulkDelete(@RequestBody List<Long> commentIds) {
        return this.commentService.bulkDelete(commentIds);
    }
}
