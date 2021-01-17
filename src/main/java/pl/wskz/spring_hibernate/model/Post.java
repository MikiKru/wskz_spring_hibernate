package pl.wskz.spring_hibernate.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    @Type(type = "text")            // typ longtext
    private String content;
    private LocalDateTime publishDateTime;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    // REALCJA N : 1
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;
}
