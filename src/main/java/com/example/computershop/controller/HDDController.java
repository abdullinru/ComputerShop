package com.example.computershop.controller;

import com.example.computershop.model.HDD;
import com.example.computershop.service.HDDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hdd")
public class HDDController {

    private HDDService hddService;

    public HDDController(HDDService hddService) {
        this.hddService = hddService;
    }


    @GetMapping
    public ResponseEntity<List<HDD>> getAllHDD() {
        List<HDD> result = hddService.getAllHDD();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HDD> getHDDById(@PathVariable Integer id) {
        HDD result = hddService.getHDDById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<HDD> addHDD(@RequestBody HDD newHDD) {
        HDD result = hddService.addHDD(newHDD);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HDD> editHDD(@PathVariable Integer id, @RequestBody HDD newHDD) {
        HDD result = hddService.editHDD(id, newHDD);
        return ResponseEntity.ok(result);
    }

}
