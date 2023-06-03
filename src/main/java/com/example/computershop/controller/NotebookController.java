package com.example.computershop.controller;

import com.example.computershop.model.Notebook;
import com.example.computershop.service.NotebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebook")
public class NotebookController {

    private NotebookService notebookService;

    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping
    public ResponseEntity<List<Notebook>> getAllNotebook() {
        List<Notebook> result = notebookService.getAllNotebook();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable Integer id) {
        Notebook result = notebookService.getNotebookById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Notebook> addNotebook(@RequestBody Notebook newNotebook) {
        Notebook result = notebookService.addNotebook(newNotebook);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> editNotebook(@PathVariable Integer id, @RequestBody Notebook newNotebook) {
        Notebook result = notebookService.editNotebook(id, newNotebook);
        return ResponseEntity.ok(result);
    }

}
