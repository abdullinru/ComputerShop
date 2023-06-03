package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Notebook;
import com.example.computershop.repository.NotebookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotebookService {
    private NotebookRepository notebookRepository;

    public NotebookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public List<Notebook> getAllNotebook() {
        return notebookRepository.findAll();
    }
    public Notebook getNotebookById(Integer id) {
        Optional<Notebook> findNotebook = notebookRepository.findById(id);
        return findNotebook.orElseThrow(ProductNotFoundException::new);
    }

    public Notebook addNotebook(Notebook newNotebook) {
        if (newNotebook == null) {
            throw new IllegalArgumentException("Parametr newNotebook is null");
        }
        for (int i = 0; i < getAllNotebook().size(); i++) {
            Notebook currentNotebook = getAllNotebook().get(i);
            if (currentNotebook.equals(newNotebook)) {
                currentNotebook.increase(newNotebook.getQuantity());
                return notebookRepository.save(currentNotebook);
            }
        }
        return notebookRepository.save(newNotebook);
    }

    public Notebook editNotebook(Integer id, Notebook newNotebook) {
        Notebook editNotebook = notebookRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        editNotebook.setSerNomer(newNotebook.getSerNomer());
        editNotebook.setManufacturer(newNotebook.getManufacturer());
        editNotebook.setPrice(newNotebook.getPrice());
        editNotebook.setQuantity(newNotebook.getQuantity());

        editNotebook.setSize(newNotebook.getSize());

        return notebookRepository.save(editNotebook);
    }
}
