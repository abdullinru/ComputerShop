package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import com.example.computershop.model.Product;
import com.example.computershop.repository.HDDRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDDService implements ProductService {
    private HDDRepository hddRepository;

    public HDDService(HDDRepository hddRepository) {
        this.hddRepository = hddRepository;
    }

    public List getAll() {
        return hddRepository.findAll();
    }
    public HDD getById(Integer id) {
        Optional<HDD> findHDD = hddRepository.findById(id);
        return findHDD.orElseThrow(ProductNotFoundException::new);
    }

    public HDD add(Product newProduct) {
        HDD newHdd = (HDD) newProduct;
        if (newHdd == null) {
            throw new IllegalArgumentException("Parametr newHDD is null");
        }
        // проверяем, есть ли уже такой HDD.
        // Если нет - то добавляем новый HDD в БД.
        // Если есть - то добавляем найденному HDD количество его экземпляров(quantity)
        List<HDD> hdds = getAll();
        int index = hdds.indexOf(newHdd);
        if (index == -1) {
            return hddRepository.save(newHdd);
        }
        HDD hddByIndex = hdds.get(index);
        hddByIndex.increase(newHdd.getQuantity());
        return hddRepository.save(hddByIndex);
    }

    public HDD edit(Integer id, Product newProduct) {
        HDD newHdd = (HDD) newProduct;
        HDD editHDD = hddRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editHDD.setSerNomer(newHdd.getSerNomer());
        editHDD.setManufacturer(newHdd.getManufacturer());
        editHDD.setPrice(newHdd.getPrice());
        editHDD.setQuantity(newHdd.getQuantity());

        editHDD.setStorage(newHdd.getStorage());

        return hddRepository.save(editHDD);
    }
}
