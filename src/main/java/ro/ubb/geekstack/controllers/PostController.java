package ro.ubb.geekstack.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.geekstack.converters.CommentConverter;
import ro.ubb.geekstack.converters.PostConverter;
import ro.ubb.geekstack.dtos.CommentDto;
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
        List<PostDto> dtos = postConverter.convertModelsToDtos(postService.getAllPosts());
        return dtos;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    Long addPost(@RequestBody PostInputDto post) {
        Post p = postConverter.convertDtoToModel(post);
        Long res = postService.addPost(p);
        return res;
    }

    @RequestMapping(value = "/posts/bulk", method = RequestMethod.POST)
    List<Long> addPosts(@RequestBody List<PostInputDto> posts) {
        List<Long> res = postService.bulkInsert(postConverter.convertDtosToModels(posts));
        return res;
    }

    @RequestMapping(value = "/posts/count", method = RequestMethod.GET)
    Long count() {
        return this.postService.
                count();
    }

    @RequestMapping(value = "/posts/clear", method = RequestMethod.DELETE)
    ResponseEntity<HttpStatus> clear() {
        this.postService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.PUT)
    ResponseEntity<Long> updateComment(@PathVariable Long postId, @RequestBody Comment comm) {
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
        Post p = this.postService.getOne(null);
        return postConverter.convertModelToDto(p);
    }
}
