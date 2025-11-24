package com.example.board.Service;

import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post CreatePost(Post post){
        return postRepository.save(post);
    }
    public Post getPostById(Long id){
        return postRepository.findById(id);
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public void updatePost(Long id, Post updatedPost) {
        Post post = getPostById(id);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        postRepository.update(post);
    }
    @Transactional
    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public void testFirstlevelCache(){
        Post post1 = postRepository.findById(1l);
        System.out.println(post1.getTitle());
        Post post2 = postRepository.findById(1l);
        System.out.println(post2.getTitle());
        System.out.println(post1 == post2);
    }

    @Transactional
    public void testWriteBehind(){
        Post post = postRepository.findById(1l);

        post.setTitle("hello!!!");
        System.out.println("update1");

        post.setTitle("hi!!!!!!");
        System.out.println("update2");

        post.setTitle("bye!!!!!!");
        System.out.println("update3");

        System.out.println("method end");
    }

    @Transactional
    public void testDirtyChecking(){
        Post post = postRepository.findById(1l);
        System.out.println("SELECT!!!!");

        post.setTitle("Hello!!!!!!!!!!!");
        System.out.println("change title");
    }
}
