package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Product;
import com.example.computershop.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService implements ProductService{

    private ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public Computer edit(Integer id, Product newProduct) {
        Computer newComp = (Computer)newProduct;
        if (newComp == null) {
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        Computer editComputer = computerRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editComputer.setSerNomer(newComp.getSerNomer());
        editComputer.setManufacturer(newComp.getManufacturer());
        editComputer.setPrice(newComp.getPrice());
        editComputer.setQuantity(newComp.getQuantity());
        editComputer.setType(newComp.getType());

        return computerRepository.save(editComputer);
    }

    public List getAll() {
        return computerRepository.findAll();
    }

    public Computer getById(Integer id) {
        Optional<Computer> findComputer = computerRepository.findById(id);
        return findComputer.orElseThrow(ProductNotFoundException::new);
    }

    public Computer add(Product newProduct) {
        Computer newComp = (Computer) newProduct;
        if (newComp == null) {
            throw new IllegalArgumentException("Parametr newСomputer is null");
        }
        // проверяем, есть ли уже такой компьютер.
        // Если нет - то добавляем новый компьютер в БД.
        // Если есть - то добавляем найденному компьютеру количество его экземпляров(quantity)
        List<Computer> comps = getAll();
        int index = comps.indexOf(newComp);
        if (index == -1) {
            return computerRepository.save(newComp);
        }
        Computer computerByIndex = comps.get(index);
        computerByIndex.increase(newComp.getQuantity());
        return computerRepository.save(computerByIndex);
    }

}
