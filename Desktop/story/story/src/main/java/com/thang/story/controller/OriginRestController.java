package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Origin;
import com.thang.story.service.origin.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/origins")
public class OriginRestController {
    @Autowired
    private IOriginService originService;

    @GetMapping
    public ResponseEntity<?> getAllOrigin() {
        return new ResponseEntity<>(this.originService.findAll(), HttpStatus.OK);
    }

}
