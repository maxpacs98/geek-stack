package ro.ubb.geekstack.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.geekstack.dtos.PostDto;
import ro.ubb.geekstack.dtos.PostInputDto;
import ro.ubb.geekstack.models.Post;

import java.time.Instant;
import java.util.Date;

@Component
public class PostConverter extends AbstractConverter<Post, PostInputDto, PostDto> {

    @Autowired
    CommentConverter commentConverter;

    @Override
    public Post convertDtoToModel(PostInputDto postDto) {
        return Post.builder()
                .author(postDto.getAuthor())
                .likes(postDto.getLikes())
                .text(postDto.getText())
                .deleted(postDto.isDeleted())
                .timestamp(Date.from(Instant.now()))
                .comments(commentConverter.convertDtosToModels(postDto.getCommentsInput()))
                .build();
    }

    @Override
    public PostDto convertModelToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .deleted(post.isDeleted())
                .likes(post.getLikes())
                .text(post.getText())
                .timestamp(post.getTimestamp())
                .comments(commentConverter.convertModelsToDtos(post.getComments()))
                .build();
    }
}
