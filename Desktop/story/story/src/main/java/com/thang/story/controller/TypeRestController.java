package com.thang.story.controller;

import com.thang.story.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/types")
public class TypeRestController {
    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ResponseEntity<?> getAllTypes() {
        return new ResponseEntity<>(this.typeService.findAll(), HttpStatus.OK);
    }
  @GetMapping
    public ResponseEntity<?> getAllTypesewewewe() {
        return new ResponseEntity<>(this.typeService.findAll(), HttpStatus.OK);
    }
  @GetMapping
    public ResponseEntity<?> getAllTypewewewewes() {
        return new ResponseEntity<>(this.typeService.findAll(), HttpStatus.OK);
    }
}
