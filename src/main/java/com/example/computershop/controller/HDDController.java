package com.example.computershop.controller;

import com.example.computershop.model.HDD;
import com.example.computershop.model.Product;
import com.example.computershop.service.HDDService;
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
@RequestMapping("/hdd")
public class HDDController {

    private ProductService productService;

    public HDDController(@Qualifier("HDDService") ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Вывести список всех жестких дисков",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = HDD.class))),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping
    public ResponseEntity<List<HDD>> getAllHDD() {
        List result = productService.getAll();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить HDD по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = HDD.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping("/{id}")
    public ResponseEntity<HDD> getHDDById(@PathVariable Integer id) {
        HDD result = (HDD) productService.getById(id);
        return ResponseEntity.ok(result);
    }
    @Operation(summary = "Добавить HDD",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = HDD.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PostMapping
    public ResponseEntity<HDD> addHDD(@RequestBody HDD newHDD) {
        HDD result = (HDD) productService.add(newHDD);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Редактировать запись о HDD по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = HDD.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PutMapping("/{id}")
    public ResponseEntity<HDD> editHDD(@PathVariable Integer id, @RequestBody HDD newHDD) {
        HDD result = (HDD) productService.edit(id, newHDD);
        return ResponseEntity.ok(result);
    }

}
