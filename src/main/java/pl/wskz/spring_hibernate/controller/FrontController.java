package pl.wskz.spring_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wskz.spring_hibernate.model.Category;
import pl.wskz.spring_hibernate.model.Post;
import pl.wskz.spring_hibernate.model.User;
import pl.wskz.spring_hibernate.service.PostService;
import pl.wskz.spring_hibernate.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class FrontController {
    private PostService postService;
    private UserService userService;
    @Autowired
    public FrontController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")                // otwarty dla wszystkich użytkowników
    public String getAllPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("post", new Post());
        model.addAttribute("categories", new ArrayList<>(Arrays.asList(Category.values())));
        model.addAttribute("user", new User());
        return "postsView";
    }
    @GetMapping("/posts&{postId}")
    public String getPost(
            @PathVariable("postId") int postId, Model model){
        if(postService.getPostById(postId).isPresent()) {
            model.addAttribute("post", postService.getPostById(postId).get());
        }
        return "postView";
    }
    @GetMapping("/login")           // otwarty dla wszystkich użytkowników
    public String loginUser(){
        return "loginView";
    }
    @PostMapping("/post")           // tylko dla zarejestrowanych użytkowników
    public String addNewPost(@ModelAttribute("post") Post post){
        postService.publishPost(post);
        return "redirect:/";        // przekierowanie metodą get na adres :url
    }
    @PostMapping("/user")           // otwarty dla wszystkich użytkowników
    public String registerUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/login";
    }

}
