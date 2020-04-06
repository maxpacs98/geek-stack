package ro.ubb.geekstack.converters;

import org.springframework.stereotype.Component;
import ro.ubb.geekstack.dtos.CommentDto;
import ro.ubb.geekstack.dtos.CommentInputDto;
import ro.ubb.geekstack.models.Comment;

import java.time.Instant;
import java.util.Date;

@Component
public class CommentConverter extends AbstractConverter<Comment, CommentInputDto, CommentDto> {

    @Override
    public Comment convertDtoToModel(CommentInputDto commentDto) {
        return Comment.builder()
                .author(commentDto.getAuthor())
                .likes(commentDto.getLikes())
                .text(commentDto.getText())
                .timestamp(Date.from(Instant.now()))
                .build();
    }

    @Override
    public CommentDto convertModelToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .author(comment.getAuthor())
                .likes(comment.getLikes())
                .text(comment.getText())
                .timestamp(comment.getTimestamp())
                .build();
    }
}
