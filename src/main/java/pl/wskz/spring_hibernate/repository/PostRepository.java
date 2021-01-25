package pl.wskz.spring_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wskz.spring_hibernate.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
