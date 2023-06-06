package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Product;
import com.example.computershop.repository.ComputerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ComputerService implements ProductService{

    private ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

//    @CachePut(value = "computers", key = "id")
    public Computer edit(Integer id, Product newProduct) {
        if (newProduct == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newProduct is null");
        }
        Computer newComp = (Computer)newProduct;
        Computer editComputer = computerRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        log.info("редактирование компьютера с  id {}, переданные параметры: {} ", id, newProduct);
        editComputer.setSerNomer(newComp.getSerNomer());
        editComputer.setManufacturer(newComp.getManufacturer());
        editComputer.setPrice(newComp.getPrice());
        editComputer.setQuantity(newComp.getQuantity());
        editComputer.setType(newComp.getType());

        return computerRepository.save(editComputer);
    }

    public List getAll() {
        log.info("Получение списка всех компьютеров ");
        return computerRepository.findAll();
    }

//    @Cacheable("computers")
    public Computer getById(Integer id) {
        log.info("Получение компьютера с  id {}", id);
        Optional<Computer> findComputer = computerRepository.findById(id);
        return findComputer.orElseThrow(ProductNotFoundException::new);
    }

    public Computer add(Product newProduct) {
        Computer newComp = (Computer) newProduct;
        if (newComp == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        // проверяем, есть ли уже такой компьютер.
        // Если нет - то добавляем новый компьютер в БД.
        // Если есть - то добавляем найденному компьютеру количество его экземпляров(quantity)
        List<Computer> comps = getAll();
        int index = comps.indexOf(newComp);
        if (index == -1) {
            log.info("Добавление компьютера с параметрами: {} ", newProduct);
            return computerRepository.save(newComp);
        }
        log.info("Увеличение количества компьютеров с параметрами {} на {} единиц", newProduct, newProduct.getQuantity());
        Computer computerByIndex = comps.get(index);
        computerByIndex.increase(newComp.getQuantity());
        return computerRepository.save(computerByIndex);
    }

}
