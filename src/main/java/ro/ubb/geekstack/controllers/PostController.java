package ro.ubb.geekstack.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.geekstack.converters.PostConverter;
import ro.ubb.geekstack.dtos.PostDto;
import ro.ubb.geekstack.dtos.PostInputDto;
import ro.ubb.geekstack.models.Post;
import ro.ubb.geekstack.services.PostService;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostConverter postConverter;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    List<PostDto> getPosts() {
//        long startTime = System.currentTimeMillis();
        List<PostDto> dtos = postConverter.convertModelsToDtos(postService.getAllPosts());
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//        System.out.println("Add comment took " + duration + " mili");
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
}
