package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public Computer editComputer(Integer id, Computer newComputer) {
        if (newComputer == null) {
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        Computer editComputer = computerRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editComputer.setSerNomer(newComputer.getSerNomer());
        editComputer.setManufacturer(newComputer.getManufacturer());
        editComputer.setPrice(newComputer.getPrice());
        editComputer.setQuantity(newComputer.getQuantity());
        editComputer.setType(newComputer.getType());

        return computerRepository.save(editComputer);
    }

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getComputerById(Integer id) {
        Optional<Computer> findComputer = computerRepository.findById(id);
        return findComputer.orElseThrow(ProductNotFoundException::new);
    }

    public Computer addComputer(Computer newComputer) {
        if (newComputer == null) {
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        // проверяем, есть ли уже такой компьютер.
        // Если нет - то добавляем новый компьютер в БД.
        // Если есть - то добавляем найденному компьютеру количество его экземпляров(quantity)
        List<Computer> comps = getAllComputers();
        int index = comps.indexOf(newComputer);
        if (index == -1) {
            return computerRepository.save(newComputer);
        }
        Computer computerByIndex = comps.get(index);
        computerByIndex.increase(newComputer.getQuantity());
        return computerRepository.save(computerByIndex);
    }
}
