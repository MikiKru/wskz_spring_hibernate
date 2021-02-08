package pl.wskz.spring_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.wskz.spring_hibernate.model.Post;
import pl.wskz.spring_hibernate.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "publishDateTime"));
    }
    public void publishPost(Post post){
        post.setPublishDateTime(LocalDateTime.now());
        postRepository.save(post);
    }

}
