package pl.wskz.spring_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wskz.spring_hibernate.model.SortDir;
import pl.wskz.spring_hibernate.model.SortParams;
import pl.wskz.spring_hibernate.model.User;
import pl.wskz.spring_hibernate.service.UserService;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")         // localhost:8080/api
@RestController     // klasa mapująca żądania protokołu http i zwracająca dane w formacie JSON
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public void addUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("passwordRepeat") String passwordRepeat
    ){
        User user = new User(name, lastName, email, password, passwordRepeat);
        userService.addUser(user);
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId).orElse(new User());
    }
    @PutMapping("/users/{userId}")
    public void updateUserPassword(
            @PathVariable("userId") int userId,
            @RequestParam("newPassword") String newPassword){
        userService.updateUserPassword(newPassword, userId);
    }
    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable("userId") int userId){
        userService.deleteUserById(userId);
    }
    @GetMapping("/users/sortedByRegistrationTime")
    public List<User> getAllUsersOrderByRegistrationTimeDesc(){
        return userService.getAllUsersOrderByRegistratonTimeDESC();
    }
    @GetMapping("/users/sorted")
    public List<User> getAllUsersOrderd(
            @RequestParam("sortDir") SortDir sortDir,
            @RequestParam("sortParams") SortParams sortParams
    ){
        return userService.getAllUsersOrderd(sortDir.getSortDirection(), sortParams.getFiledName());
    }
    @GetMapping("/users/sortedAndPaged")
    public List<User> getAllUsersSortedAndPaged(
            @RequestParam("sortDir") SortDir sortDir,
            @RequestParam("sortParams") SortParams sortParams,
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("size") int size
    ){
        return userService.getAllUsersSotedAndPaged(
                pageIndex, size, sortDir.getSortDirection(), sortParams.getFiledName());
    }
    @GetMapping("/users/email={email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email).orElse(new User());
    }
    @GetMapping("/user/logging")
    public boolean isUserLogged(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.getUserByEmailAndPassword(email,password).isPresent();
    }
    @GetMapping("/users/status={status}")
    public List<User> getUsersByStatus(@PathVariable("status") boolean status){
        return userService.getUsersByStatus(status);
    }
    @PutMapping("/users/updateStatus")
    public void updateUserStatusByEmail(@RequestParam("status") boolean status, @RequestParam("email") String email){
        userService.updateUserStatusByEmail(status,email);
    }
    @GetMapping("/users/statusStats")
    public Map getUserStats(){
        return userService.getUserStats();
    }
}
