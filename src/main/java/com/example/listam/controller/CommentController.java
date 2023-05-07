package com.example.listam.controller;

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
            commentRepository.save(commentObj);
        } else {
            return "/items";
        }
        return "redirect:/items" + id;
    }

    @GetMapping("/removeComment")
    public String removeComment(@RequestParam("id") int id,@RequestParam("itemId") int itemId) {
        commentRepository.deleteById(id);
        return "redirect:/items/" + itemId;
    }

}
