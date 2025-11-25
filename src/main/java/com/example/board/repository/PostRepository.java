package com.example.board.repository;

import com.example.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    // 저장 (INSERT or UPDATE)
    // Post save(Post entity);

    // 조회
    // Optional<Post> findById(Long id);
    // List<Post> findAll();
    // List<Post> findAll(Sort sort);

    // 삭제
    // void deleteById(Long id);
    // void deleteById(Post entity);

    // 계수 조회
    // long count();

    // 존재 여부 확인
    // boolean existsById(Long id);
    List<Post> findByTitleContaining(String keyword);
}
