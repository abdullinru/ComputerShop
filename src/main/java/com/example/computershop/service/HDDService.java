package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import com.example.computershop.repository.HDDRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDDService {
    private HDDRepository hddRepository;

    public HDDService(HDDRepository hddRepository) {
        this.hddRepository = hddRepository;
    }

    public List<HDD> getAllHDD() {
        return hddRepository.findAll();
    }
    public HDD getHDDById(Integer id) {
        Optional<HDD> findHDD = hddRepository.findById(id);
        return findHDD.orElseThrow(ProductNotFoundException::new);
    }

    public HDD addHDD(HDD newHDD) {
        if (newHDD == null) {
            throw new IllegalArgumentException("Parametr newHDD is null");
        }
        // проверяем, есть ли уже такой HDD.
        // Если нет - то добавляем новый HDD в БД.
        // Если есть - то добавляем найденному HDD количество его экземпляров(quantity)
        List<HDD> hdds = getAllHDD();
        int index = hdds.indexOf(newHDD);
        if (index == -1) {
            return hddRepository.save(newHDD);
        }
        HDD hddByIndex = hdds.get(index);
        hddByIndex.increase(newHDD.getQuantity());
        return hddRepository.save(hddByIndex);
    }

    public HDD editHDD(Integer id, HDD newHDD) {
        HDD editHDD = hddRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editHDD.setSerNomer(newHDD.getSerNomer());
        editHDD.setManufacturer(newHDD.getManufacturer());
        editHDD.setPrice(newHDD.getPrice());
        editHDD.setQuantity(newHDD.getQuantity());

        editHDD.setStorage(newHDD.getStorage());

        return hddRepository.save(editHDD);
    }
}
