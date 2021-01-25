package pl.wskz.spring_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wskz.spring_hibernate.model.User;

import java.util.List;
import java.util.Optional;

@Repository     // interfejs do komunikacji z bazą danych
public interface UserRepository extends JpaRepository<User, Integer> {
    // UserRepository rozszerza metody zaimplementowane w interfejsie JPA
    // SELECT * FROM users WHERE email = ?;
    Optional<User> findFirstByEmail(String email);
    // SELECT * FROM users WHERE email = ? AND password = ?;
    Optional<User> findFirstByEmailAndPassword(String email, String password);
    // SELECT * FROM users WHERE status = ?;
    List<User> findAllByStatus(boolean status);

}
