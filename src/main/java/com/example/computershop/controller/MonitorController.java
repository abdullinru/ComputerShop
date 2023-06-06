package com.example.computershop.controller;

import com.example.computershop.model.HDD;
import com.example.computershop.model.Monitor;
import com.example.computershop.model.Product;
import com.example.computershop.service.MonitorService;
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
@RequestMapping("monitor")
public class MonitorController {

    private ProductService productService;

    public MonitorController(@Qualifier("monitorService") ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Вывести список всех мониторов",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Monitor.class))),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping
    public ResponseEntity<List<Product>> getAllMonitor() {
        List<Product> result = productService.getAll();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить монитор по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Monitor.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @GetMapping("/{id}")
    public ResponseEntity<Monitor> getMonitorById(@PathVariable Integer id) {
        Monitor result = (Monitor) productService.getById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Добавить монитор",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Monitor.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PostMapping
    public ResponseEntity<Monitor> addMonitor(@RequestBody Monitor newMonitor) {
        Monitor result = (Monitor) productService.add(newMonitor);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Редактировать запись о мониторе по его идентификатору",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Monitor.class))),
            @ApiResponse(responseCode = "400", description = "badRequest"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "error on server")})
    @PutMapping("/{id}")
    public ResponseEntity<Monitor> editMonitor(@PathVariable Integer id, @RequestBody Monitor newMonitor) {
        Monitor result = (Monitor) productService.edit(id, newMonitor);
        return ResponseEntity.ok(result);
    }
}
