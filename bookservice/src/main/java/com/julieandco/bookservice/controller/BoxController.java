package com.julieandco.bookservice.controller;


import com.julieandco.bookservice.entities.Box;
import com.julieandco.bookservice.repo.BoxRepository;
import com.julieandco.bookservice.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {
    private final BoxService boxService;
    private final BoxRepository boxRepository;


    @Autowired
    public BoxController(BoxService boxService, BoxRepository boxRepository) {
        this.boxService = boxService;
        this.boxRepository=boxRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBox(@RequestBody String addressJson){
        Box newBox=new Box();
        newBox.setAddress(addressJson);
        boxService.addBox(newBox);

        return ResponseEntity.ok().build();
    }
}
