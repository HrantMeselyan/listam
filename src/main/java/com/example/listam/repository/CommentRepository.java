package com.example.listam.repository;

import com.example.listam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByItem_id(int itemId);
}
