package com.example.computershop.mapper;

import com.example.computershop.dto.ComputerDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.model.Computer;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper implements Mapper{

    public Computer dtoToModel(ProductDto dto) {
        ComputerDto computerDto = (ComputerDto) dto;
        return new Computer(
                defaultId,
                dto.getSerNomer(),
                dto.getManufacturer(),
                dto.getPrice(),
                dto.getQuantity(),
                computerDto.getType());
    }
}
