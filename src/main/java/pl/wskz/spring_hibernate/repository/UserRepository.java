package pl.wskz.spring_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wskz.spring_hibernate.model.User;

import java.util.List;
import java.util.Map;
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
    // JPQL
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :status WHERE u.email = :email")
    void updateUserStatusByEmail(@Param("status") boolean status, @Param("email") String email);
    // MySQL
    @Query(nativeQuery = true,
            value = "SELECT status, count(*) FROM users GROUP BY status ORDER BY 2 DESC")
    List<Object[]> userStatusStats();
}
