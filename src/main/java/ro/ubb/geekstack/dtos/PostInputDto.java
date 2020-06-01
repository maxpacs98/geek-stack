package ro.ubb.geekstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@SuperBuilder
public class PostInputDto {
    private String text;
    private String author;
    private int likes;
    private boolean deleted;
    private List<CIDto> commentsInput;
}
