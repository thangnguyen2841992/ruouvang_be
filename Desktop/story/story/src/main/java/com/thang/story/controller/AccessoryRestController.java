package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.service.accessory.IAccessoryService;
import com.thang.story.service.origin.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/accessories")
public class AccessoryRestController {
    @Autowired
    private IAccessoryService accessoryService;

    @GetMapping
    public ResponseEntity<?> getAllAccessory() {
        return new ResponseEntity<>(this.accessoryService.findAll(), HttpStatus.OK);
    }

}
