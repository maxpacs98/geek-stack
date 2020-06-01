package ro.ubb.geekstack.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@SuperBuilder
public class CommentDto extends CIDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date timestamp;
}
