package com.example.computershop.service;

import com.example.computershop.exception.ComputerNotFoundException;
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
        Computer editComputer = computerRepository.findById(id)
                .orElseThrow(ComputerNotFoundException::new);
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
        return findComputer.orElseThrow(ComputerNotFoundException::new);
    }

    public Computer addComputer(Computer newСomputer) {
        if (newСomputer == null) {
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        for (int i = 0; i < getAllComputers().size(); i++) {
            Computer currentComputer = getAllComputers().get(i);
            if (currentComputer.equals(newСomputer)) {
                currentComputer.increase(newСomputer.getQuantity());
                return computerRepository.save(currentComputer);
            }
        }
        return computerRepository.save(newСomputer);
    }
}
