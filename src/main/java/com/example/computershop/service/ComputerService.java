package com.example.computershop.service;

import com.example.computershop.dto.ComputerDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.mapper.ComputerMapper;
import com.example.computershop.mapper.Mapper;
import com.example.computershop.model.Computer;
import com.example.computershop.repository.ComputerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ComputerService implements ProductService{

    private ComputerRepository computerRepository;

    private final ComputerMapper mapper;

    public ComputerService(ComputerRepository computerRepository, ComputerMapper mapper) {
        this.computerRepository = computerRepository;
        this.mapper = mapper;
    }

    public List getAll() {
        log.info("Получение списка всех компьютеров ");
        return computerRepository.findAll();
    }

    @Cacheable("computers")
    public Computer getById(Integer id) {
        log.info("Получение компьютера с  id {}", id);
        Optional<Computer> findComputer = computerRepository.findById(id);
        return findComputer.orElseThrow(ProductNotFoundException::new);
    }
    public Computer add(ProductDto newProduct) {
        Computer newComp = (Computer) mapper.dtoToModel(newProduct);
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

    @CachePut(value = "computers", key = "#id")
    public Computer edit(Integer id, ProductDto newProduct) {
        if (newProduct == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newProduct is null");
        }
        ComputerDto newComp = (ComputerDto)newProduct;
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

}
