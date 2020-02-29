package ro.ubb.geekstack.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class CommentInputDto {
    private String text;
    private String author;
    private int likes;
}
