package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Notebook;
import com.example.computershop.model.Product;
import com.example.computershop.repository.NotebookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotebookService implements ProductService{
    private NotebookRepository notebookRepository;

    public NotebookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public List getAll() {
        log.info("Получение списка всех ноутбуков ");
        return notebookRepository.findAll();
    }
    @Cacheable("notebooks")
    public Notebook getById(Integer id) {
        log.info("Получение ноутбука с  id {}", id);
        Optional<Notebook> findNotebook = notebookRepository.findById(id);
        return findNotebook.orElseThrow(ProductNotFoundException::new);
    }

    public Notebook add(Product newProduct) {
        Notebook newNotebook = (Notebook) newProduct;
        if (newNotebook == null) {
            throw new IllegalArgumentException("Parametr newNotebook is null");
        }
        // проверяем, есть ли уже такой ноутбук.
        // Если нет - то добавляем новый ноутбук в БД.
        // Если есть - то добавляем найденному ноутбуку количество его экземпляров(quantity)
        List<Notebook> notebooks = getAll();
        int index = notebooks.indexOf(newNotebook);
        if (index == -1) {
            log.info("Добавление ноутбука с параметрами: {} ", newProduct);
            return notebookRepository.save(newNotebook);
        }
        log.info("Увеличение количества ноутбуков с параметрами {} на {} единиц", newProduct, newProduct.getQuantity());
        Notebook notebookByIndex = notebooks.get(index);
        notebookByIndex.increase(newNotebook.getQuantity());
        return notebookRepository.save(notebookByIndex);
    }

    @CachePut(value = "notebooks", key = "#id")
    public Notebook edit(Integer id, Product newProduct) {
        if (newProduct == null) {
            log.error("Переданный параметр - NULL");
            throw new IllegalArgumentException("Parametr newProduct is null");
        }
        Notebook newNotebook = (Notebook) newProduct;
        Notebook editNotebook = notebookRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        log.info("редактирование ноутбука с  id {}, переданные параметры: {} ", id, newProduct);
        editNotebook.setSerNomer(newNotebook.getSerNomer());
        editNotebook.setManufacturer(newNotebook.getManufacturer());
        editNotebook.setPrice(newNotebook.getPrice());
        editNotebook.setQuantity(newNotebook.getQuantity());

        editNotebook.setSize(newNotebook.getSize());

        return notebookRepository.save(editNotebook);
    }
}
