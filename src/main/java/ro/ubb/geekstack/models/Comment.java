package ro.ubb.geekstack.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Comment extends BaseEntity<Long> {
    private String text;
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    Date timestamp;
    private int likes;
}