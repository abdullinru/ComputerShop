package com.example.computershop.controller;

import com.example.computershop.model.Computer;
import com.example.computershop.service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Вывести список всех компьтеров",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Computer.class))),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping
    public ResponseEntity<List<Computer>> getAllComputers() {
        List result = computerService.getAll();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить компьютер по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Computer.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputerById(@PathVariable Integer id) {
        Computer result = (Computer) computerService.getById(id);
        return ResponseEntity.ok(result);
    }
    @Operation(summary = "Добавить компьютер",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Computer.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PostMapping
    public ResponseEntity<Computer> addComputer(@RequestBody Computer newComputer) {
        Computer result = (Computer) computerService.add(newComputer);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Редактировать запись о компьютере по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Computer.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PutMapping("/{id}")
    public ResponseEntity<Computer> editComputer(@PathVariable Integer id, @RequestBody Computer newComputer) {
        Computer result = (Computer) computerService.edit(id, newComputer);
        return ResponseEntity.ok(result);
    }

}
