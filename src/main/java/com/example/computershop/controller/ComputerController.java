package com.example.computershop.controller;

import com.example.computershop.model.Computer;
import com.example.computershop.service.ComputerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computer")
public class ComputerController {

    private ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public ResponseEntity<List<Computer>> getAllComputers() {
        List<Computer> result = computerService.getAllComputers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputerById(@PathVariable Integer id) {
        Computer result = computerService.getComputerById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Computer> addComputer(@RequestBody Computer newComputer) {
        Computer result = computerService.addComputer(newComputer);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Computer> editComputer(@PathVariable Integer id, @RequestBody Computer newComputer) {
        Computer result = computerService.editComputer(id, newComputer);
        return ResponseEntity.ok(result);
    }

}
