package com.example.computershop.controller;

import com.example.computershop.model.HDD;
import com.example.computershop.model.Monitor;
import com.example.computershop.service.MonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("monitor")
public class MonitorController {

    private MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping
    public ResponseEntity<List<Monitor>> getAllMonitor() {
        List<Monitor> result = monitorService.getAllMonitor();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monitor> getMonitorById(@PathVariable Integer id) {
        Monitor result = monitorService.getMonitorById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Monitor> addMonitor(@RequestBody Monitor newMonitor) {
        Monitor result = monitorService.addMonitor(newMonitor);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monitor> editMonitor(@PathVariable Integer id, @RequestBody Monitor newMonitor) {
        Monitor result = monitorService.editMonitor(id, newMonitor);
        return ResponseEntity.ok(result);
    }
}
