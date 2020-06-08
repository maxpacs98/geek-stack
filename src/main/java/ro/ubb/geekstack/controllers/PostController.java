package ro.ubb.geekstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.geekstack.converters.CommentConverter;
import ro.ubb.geekstack.converters.PostConverter;
import ro.ubb.geekstack.dtos.PostDto;
import ro.ubb.geekstack.dtos.PostInputDto;
import ro.ubb.geekstack.models.Comment;
import ro.ubb.geekstack.models.Post;
import ro.ubb.geekstack.services.PostService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostConverter postConverter;

    @Autowired
    CommentConverter commentConverter;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    List<PostDto> getPosts() {
        /* Retrieves all the posts and converts them into DTOs */

        return postConverter.convertModelsToDtos(postService.getAllPosts());
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    Long addPost(@RequestBody PostInputDto post) {
        /* Transforms a DTO into a post and sends it to the service to be added */

        Post p = postConverter.convertDtoToModel(post);
        return postService.addPost(p);
    }

    @RequestMapping(value = "/posts/bulk", method = RequestMethod.POST)
    List<Long> addPosts(@RequestBody List<PostInputDto> posts) {
        /* Transforms multiple DTOs into comments and sends them to the service to be inserted */

        return postService.bulkInsert(postConverter.convertDtosToModels(posts));
    }

    @RequestMapping(value = "/posts/count", method = RequestMethod.GET)
    Long count() {
        /* Returns the count of all the comments */

        return this.postService.count();
    }

    @RequestMapping(value = "/posts/clear", method = RequestMethod.DELETE)
    ResponseEntity<HttpStatus> clear() {
        /* Sends a clear all comments command to the service */

        this.postService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.PUT)
    ResponseEntity<Long> updateComment(@PathVariable Long postId, @RequestBody Comment comm) {
        /* Transforms a comment belonging to a post from DTO to model
        and sends it to the service update it*/

        comm.setTimestamp(Date.from(Instant.now()));
        Long updated = postService.updateComment(postId, comm);
        if (updated != null) {
            return ResponseEntity.ok().body(updated);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "posts/first/", method = RequestMethod.GET)
    PostDto getOne() {
        /* Retrieves the first post from the database */

        Post p = this.postService.getOne(null);
        return postConverter.convertModelToDto(p);
    }
}
