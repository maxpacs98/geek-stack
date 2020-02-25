package ro.ubb.geekstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.services.CommentService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    Long addComment(@RequestBody Comment comment) {
        throw new NotImplementedException();
    }
}
