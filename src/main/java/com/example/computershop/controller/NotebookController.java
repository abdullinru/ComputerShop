package com.example.computershop.controller;

import com.example.computershop.model.Notebook;
import com.example.computershop.service.NotebookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Вывести список всех ноутбуков",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Notebook.class))),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping
    public ResponseEntity<List<Notebook>> getAllNotebook() {
        List<Notebook> result = notebookService.getAllNotebook();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить ноутбук по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Notebook.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping("/{id}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable Integer id) {
        Notebook result = notebookService.getNotebookById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Добавить ноутбук",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Notebook.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PostMapping
    public ResponseEntity<Notebook> addNotebook(@RequestBody Notebook newNotebook) {
        Notebook result = notebookService.addNotebook(newNotebook);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Редактировать запись о ноутбуке по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Notebook.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PutMapping("/{id}")
    public ResponseEntity<Notebook> editNotebook(@PathVariable Integer id, @RequestBody Notebook newNotebook) {
        Notebook result = notebookService.editNotebook(id, newNotebook);
        return ResponseEntity.ok(result);
    }

}
