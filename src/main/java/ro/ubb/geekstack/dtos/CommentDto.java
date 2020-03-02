package ro.ubb.geekstack.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private String author;
    private int likes;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date timestamp;
}
