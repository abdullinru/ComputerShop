package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import com.example.computershop.model.Monitor;
import com.example.computershop.model.Product;
import com.example.computershop.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorService implements ProductService{
    private MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List getAll() {
        return monitorRepository.findAll();
    }
    public Monitor getById(Integer id) {
        Optional<Monitor> findMonitor = monitorRepository.findById(id);
        return findMonitor.orElseThrow(ProductNotFoundException::new);
    }

    public Monitor add(Product newProduct) {
        Monitor newMonitor = (Monitor) newProduct;
        if (newMonitor == null) {
            throw new IllegalArgumentException("Parametr newMonitor is null");
        }
        // проверяем, есть ли уже такой монитор.
        // Если нет - то добавляем новый монитор в БД.
        // Если есть - то добавляем найденному монитору количество его экземпляров(quantity)
        List<Monitor> monitors = getAll();
        int index = monitors.indexOf(newMonitor);
        if (index == -1) {
            return monitorRepository.save(newMonitor);
        }
        Monitor monitorByIndex = monitors.get(index);
        monitorByIndex.increase(newMonitor.getQuantity());
        return monitorRepository.save(monitorByIndex);
    }

    public Monitor edit(Integer id, Product newProduct) {
        Monitor newMonitor = (Monitor) newProduct;
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
