package com.example.listam.controller;

import com.example.listam.entity.Comment;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/items/" + comment.getItem().getId();
    }

    @GetMapping("/removeComment")
    public String removeComment(@RequestParam("id") int id, @RequestParam("itemId") int itemId) {
        commentRepository.deleteById(id);
        return "redirect:/items/" + itemId;
    }

}
