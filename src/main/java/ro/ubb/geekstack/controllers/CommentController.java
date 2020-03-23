package ro.ubb.geekstack.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.geekstack.dtos.CommentDto;
import ro.ubb.geekstack.dtos.CommentInputDto;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.services.CommentService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<CommentDto> getComments() {
        long startTime = System.currentTimeMillis();
        List<CommentDto> dtos = new ArrayList<>();
        commentService.getAllComments().forEach(c -> dtos.add(CommentDto.
                builder()
                .id(c.getId())
                .author(c.getAuthor())
                .likes(c.getLikes())
                .timestamp(c.getTimestamp())
                .text(c.getText())
                .build()));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        logger.info("Get comments CONTROLLER took " + duration + " mili");
//        System.out.println("Get comments CONTROLLER took " + duration + " mili");
        return dtos;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Long addComment(@RequestBody CommentInputDto comment) {
        // TODO: See if we can generify
//        long startTime = System.currentTimeMillis();
        Comment c = Comment.builder().author(comment.getAuthor()).likes(comment.getLikes()).text(comment.getText()).build();
        c.setTimestamp(Date.from(Instant.now()));
        Long res = commentService.addComment(c);
//        long endTime = System.currentTimeMillis();
//        long duration = (endTime - startTime);
//        System.out.println("Add comment took " + duration + " mili");
        return res;
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
            c.setTimestamp(Date.from(Instant.now()));
            toInsert.add(c);
        });
        return commentService.bulkInsert(toInsert);
    }

    @RequestMapping(value = "/comments/delete", method = RequestMethod.DELETE)
    List<Long> bulkDelete(@RequestBody List<Long> commentIds) {
        return this.commentService.bulkDelete(commentIds);
    }

    @RequestMapping(value = "/comments/clear", method = RequestMethod.DELETE)
    ResponseEntity<HttpStatus> clear() {
        this.commentService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/count", method = RequestMethod.GET)
    Long count() {
        return this.commentService.count();
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    ResponseEntity<HttpStatus> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "comments/{id}", method = RequestMethod.GET)
    CommentDto getOne(@PathVariable Long id) {
        Comment c = this.commentService.getOne(id);
        return CommentDto.
                builder()
                .id(c.getId())
                .author(c.getAuthor())
                .likes(c.getLikes())
                .timestamp(c.getTimestamp())
                .text(c.getText())
                .build();
    }
}