package pl.wskz.spring_hibernate.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @NotBlank(message = "Tytuł posta nie może być pusty")
    private String title;
    @Type(type = "text")            // typ longtext
    @Size(min = 10, message = "Zawartość posta nie może być krótsza niż {min}")
    private String content;
    private LocalDateTime publishDateTime;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    // REALCJA N : 1
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;
}
