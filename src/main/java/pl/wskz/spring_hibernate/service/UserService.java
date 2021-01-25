package pl.wskz.spring_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.wskz.spring_hibernate.model.User;
import pl.wskz.spring_hibernate.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service    // klasa implementującą logikę biznesową powiązaną z encją User
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // utworzenie użytkownika i wprowadzenie go do tabelki
    // INSERT INTO users VALUES (?,?,?,?,?,?,?);
    public void addUser(User user){
        userRepository.save(user);
    }
    // SELECT * FROM users;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    // SELECT * FROM users WHERE user_id = ?;
    public Optional<User> getUserById(int userId){
        return userRepository.findById(userId);
    }
    // UPDATE users SET password = ? WHERE user_id = ?;
    public void updateUserPassword(String newPassword, int userId){
        Optional<User> userOptional = getUserById(userId);
        if(userOptional.isPresent()){
            User userToUpdate = userOptional.get();
            userToUpdate.setPassword(newPassword);
            userRepository.save(userToUpdate);      // gdy metoda save wykonywana jest na istniejącym obiekcie działa jak update
        }
    }
    // DELETE FROM users WHERE user_id =?;
    public void deleteUserById(int userId){
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        }
    }
    // SELECT * FROM users ORDER BY registration_date_time DESC;
    public List<User> getAllUsersOrderByRegistratonTimeDESC(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "registrationTime"));
    }
    // SELECT * FROM users ORDER BY param direction;
    public List<User> getAllUsersOrderd(Sort.Direction sortDir, String fieldName){
        return userRepository.findAll(Sort.by(sortDir, fieldName));
    }
    // SELECT * FROM users ORDER BY param direction LIMIT ? OFFSET ?
    public List<User> getAllUsersSotedAndPaged(int pageIndex, int size, Sort.Direction sortDir, String fieldName){
        // Pageable pageable = PageRequest.of(pageIndex, 5, Sort.by(sortDirection, sortFieldName));
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(sortDir, fieldName));
        return userRepository.findAll(pageable).getContent();
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findFirstByEmail(email);
    }
    public Optional<User> getUserByEmailAndPassword(String email, String password){
        return userRepository.findFirstByEmailAndPassword(email, password);
    }
    public List<User> getUsersByStatus(boolean status){
        return userRepository.findAllByStatus(status);
    }

}
