package com.example.board.Service;

import com.example.board.entity.Comment;
import com.example.board.entity.Post;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public Comment createComment(Long postId, Comment comment) {
        Post post = postService.getPostById(postId);

        post.addComment(comment);
        Comment saved = commentRepository.save(comment);

        return saved;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        commentRepository.delete(comment);
    }

}
