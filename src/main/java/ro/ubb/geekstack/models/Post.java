package ro.ubb.geekstack.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@NamedEntityGraph(name = "PostWithComments",
        attributeNodes = @NamedAttributeNode("comments")
)
public class Post extends BaseEntity<Long> {
    private String text;
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private int likes;
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
