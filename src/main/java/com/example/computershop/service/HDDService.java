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
        for (int i = 0; i < getAllHDD().size(); i++) {
            HDD currentHDD = getAllHDD().get(i);
            if (currentHDD.equals(newHDD)) {
                currentHDD.increase(newHDD.getQuantity());
                return hddRepository.save(currentHDD);
            }
        }
        return hddRepository.save(newHDD);
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
