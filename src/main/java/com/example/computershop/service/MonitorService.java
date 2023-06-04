package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import com.example.computershop.model.Monitor;
import com.example.computershop.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {
    private MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List<Monitor> getAllMonitor() {
        return monitorRepository.findAll();
    }
    public Monitor getMonitorById(Integer id) {
        Optional<Monitor> findMonitor = monitorRepository.findById(id);
        return findMonitor.orElseThrow(ProductNotFoundException::new);
    }

    public Monitor addMonitor(Monitor newMonitor) {
        if (newMonitor == null) {
            throw new IllegalArgumentException("Parametr newMonitor is null");
        }
        // проверяем, есть ли уже такой монитор.
        // Если нет - то добавляем новый монитор в БД.
        // Если есть - то добавляем найденному монитору количество его экземпляров(quantity)
        List<Monitor> monitors = getAllMonitor();
        int index = monitors.indexOf(newMonitor);
        if (index == -1) {
            return monitorRepository.save(newMonitor);
        }
        Monitor monitorByIndex = monitors.get(index);
        monitorByIndex.increase(newMonitor.getQuantity());
        return monitorRepository.save(monitorByIndex);
    }

    public Monitor editMonitor(Integer id, Monitor newMonitor) {
        Monitor editMonitor = monitorRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editMonitor.setSerNomer(newMonitor.getSerNomer());
        editMonitor.setManufacturer(newMonitor.getManufacturer());
        editMonitor.setPrice(newMonitor.getPrice());
        editMonitor.setQuantity(newMonitor.getQuantity());

        editMonitor.setDiagonal(newMonitor.getDiagonal());

        return monitorRepository.save(editMonitor);
    }
}
