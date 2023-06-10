package com.example.computershop.service;

import com.example.computershop.dto.HDDDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.mapper.Mapper;
import com.example.computershop.model.HDD;
import com.example.computershop.model.Product;
import com.example.computershop.repository.HDDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HDDService implements ProductService {
    private HDDRepository hddRepository;
    private Mapper mapper;

    public HDDService(HDDRepository hddRepository, @Qualifier("hddMapper") Mapper mapper) {
        this.hddRepository = hddRepository;
        this.mapper = mapper;
    }

    public List getAll() {
        log.info("Получение списка всех HDD ");
        return hddRepository.findAll();
    }
    @Cacheable("hdds")
    public HDD getById(Integer id) {
        log.info("Получение HDD с  id {}", id);
        Optional<HDD> findHDD = hddRepository.findById(id);
        return findHDD.orElseThrow(ProductNotFoundException::new);
    }

    public HDD add(ProductDto newProduct) {
        HDD newHdd = (HDD) mapper.dtoToModel(newProduct);
        if (newHdd == null) {
            throw new IllegalArgumentException("Parametr newHDD is null");
        }
        // проверяем, есть ли уже такой HDD.
        // Если нет - то добавляем новый HDD в БД.
        // Если есть - то добавляем найденному HDD количество его экземпляров(quantity)
        List<HDD> hdds = getAll();
        int index = hdds.indexOf(newHdd);
        if (index == -1) {
            log.info("Добавление HDD с параметрами: {} ", newProduct);
            return hddRepository.save(newHdd);
        }
        log.info("Увеличение количества HDD с параметрами {} на {} единиц", newProduct, newProduct.getQuantity());
        HDD hddByIndex = hdds.get(index);
        hddByIndex.increase(newHdd.getQuantity());
        return hddRepository.save(hddByIndex);
    }

    @CachePut(value = "hdds", key = "#id")
    public HDD edit(Integer id, ProductDto newProduct) {
        if (newProduct == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newProduct is null");
        }
        HDDDto newHdd = (HDDDto) newProduct;
        HDD editHDD = hddRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        log.info("редактирование HDD с  id {}, переданные параметры: {} ", id, newProduct);
        editHDD.setSerNomer(newHdd.getSerNomer());
        editHDD.setManufacturer(newHdd.getManufacturer());
        editHDD.setPrice(newHdd.getPrice());
        editHDD.setQuantity(newHdd.getQuantity());

        editHDD.setStorage(newHdd.getStorage());

        return hddRepository.save(editHDD);
    }
}
