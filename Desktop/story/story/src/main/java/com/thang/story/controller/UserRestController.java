package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.User;
import com.thang.story.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("User không đúng!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
}
