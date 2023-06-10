package com.example.computershop.mapper;

import com.example.computershop.dto.ComputerDto;
import com.example.computershop.dto.NotebookDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Notebook;
import org.springframework.stereotype.Component;

@Component
public class NotebookMapper implements Mapper{

    public Notebook dtoToModel(ProductDto dto) {
        NotebookDto notebookDto = (NotebookDto) dto;
        return new Notebook(
                defaultId,
                dto.getSerNomer(),
                dto.getManufacturer(),
                dto.getPrice(),
                dto.getQuantity(),
                notebookDto.getSize());
    }
}
