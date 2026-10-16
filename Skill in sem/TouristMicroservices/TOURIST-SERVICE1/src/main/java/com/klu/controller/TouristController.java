package com.klu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.service.TouristService;

@RestController
@RequestMapping("/tourist")
public class TouristController {

    @Autowired
    private TouristService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTourist() {
        return ResponseEntity.ok(service.getTouristDetails());
    }
}
