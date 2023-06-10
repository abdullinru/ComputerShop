package com.example.computershop.controller;

import com.example.computershop.dto.NotebookDto;
import com.example.computershop.model.Notebook;
import com.example.computershop.model.Product;
import com.example.computershop.service.NotebookService;
import com.example.computershop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebook")
public class NotebookController {

    private ProductService productService;

    public NotebookController(@Qualifier("notebookService") ProductService productService) {
        this.productService = productService;
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
        List result = productService.getAll();
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
        Notebook result = (Notebook) productService.getById(id);
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
    public ResponseEntity<Notebook> addNotebook(@RequestBody NotebookDto newNotebook) {
        Notebook result = (Notebook) productService.add(newNotebook);
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
    public ResponseEntity<Notebook> editNotebook(@PathVariable Integer id, @RequestBody NotebookDto newNotebook) {
        Notebook result = (Notebook) productService.edit(id, newNotebook);
        return ResponseEntity.ok(result);
    }

}
