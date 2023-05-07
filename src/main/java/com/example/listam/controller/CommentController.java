package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/addComment")
    public String addComment(@RequestParam("comment") String comment, @RequestParam("id") int id) {
        Comment commentObj = new Comment();
        commentObj.setComment(comment);
        Optional<Item> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            Item item = byId.get();
            commentObj.setItem(item);
        } else {
            return "redirect:/items";
        }
        commentRepository.save(commentObj);
        return "redirect:/items/${id}";
    }

    @GetMapping("/comments")
    public String categoriesPage(ModelMap map) {
        List<Comment> comments = commentRepository.findAll();
        map.addAttribute("comments", comments);
        return "/singleItem";
    }

    @GetMapping("/comment/remove")
    public String removeComment(@RequestParam("id") int id) {
        commentRepository.deleteById(id);
        return "redirect:/items";
    }

}
