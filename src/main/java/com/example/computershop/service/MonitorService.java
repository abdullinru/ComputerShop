package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import com.example.computershop.model.Monitor;
import com.example.computershop.model.Product;
import com.example.computershop.repository.MonitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MonitorService implements ProductService{
    private MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List getAll() {
        log.info("Получение списка всех мониторов");
        return monitorRepository.findAll();
    }
    @Cacheable("monitors")
    public Monitor getById(Integer id) {
        log.info("Получение монитора с  id {}", id);
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
            log.info("Добавление монитора с параметрами: {} ", newProduct);
            return monitorRepository.save(newMonitor);
        }
        log.info("Увеличение количества мониторов с параметрами {} на {} единиц", newProduct, newProduct.getQuantity());
        Monitor monitorByIndex = monitors.get(index);
        monitorByIndex.increase(newMonitor.getQuantity());
        return monitorRepository.save(monitorByIndex);
    }

    @CachePut(value = "monitors", key = "#id")
    public Monitor edit(Integer id, Product newProduct) {
        if (newProduct == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newProduct is null");
        }
        Monitor newMonitor = (Monitor) newProduct;
        Monitor editMonitor = monitorRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        log.info("редактирование монитора с  id {}, переданные параметры: {} ", id, newProduct);
        editMonitor.setSerNomer(newMonitor.getSerNomer());
        editMonitor.setManufacturer(newMonitor.getManufacturer());
        editMonitor.setPrice(newMonitor.getPrice());
        editMonitor.setQuantity(newMonitor.getQuantity());

        editMonitor.setDiagonal(newMonitor.getDiagonal());

        return monitorRepository.save(editMonitor);
    }
}
