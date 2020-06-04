package ro.ubb.geekstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.geekstack.converters.CommentConverter;
import ro.ubb.geekstack.dtos.CIDto;
import ro.ubb.geekstack.dtos.CommentDto;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.services.CommentService;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentConverter commentConverter;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<CommentDto> getComments() {
        List<CommentDto> dtos = commentConverter.convertModelsToDtos(commentService.getAllComments());
        return dtos;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Long addComment(@RequestBody CIDto comment) {
        Comment c = commentConverter.convertDtoToModel(comment);
        Long res = commentService.addComment(c);
        return res;
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    ResponseEntity<Long> updateComment(@PathVariable Long id, @RequestBody CIDto comment) {
        Comment c = commentConverter.convertDtoToModel(comment);
        Long updated = commentService.updateComment(id, c);
        if (updated != null) {
            return ResponseEntity.ok().body(updated);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/comments/bulk", method = RequestMethod.POST)
    List<Long> bulkInsert(@RequestBody List<CIDto> comments) {
        return commentService.bulkInsert(commentConverter.convertDtosToModels(comments));
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
    ResponseEntity<HttpStatus> health() throws InterruptedException {
        Thread.sleep(200);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "comments/{id}", method = RequestMethod.GET)
    CommentDto getOne(@PathVariable Long id) {
        Comment c = this.commentService.getOne(id);
        return commentConverter.convertModelToDto(c);
    }
}