package ro.ubb.geekstack.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@SuperBuilder
public class CommentInputDto {
    private String text;
    private String author;
    private int likes;
}
