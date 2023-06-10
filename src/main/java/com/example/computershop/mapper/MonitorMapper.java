package com.example.computershop.mapper;

import com.example.computershop.dto.ComputerDto;
import com.example.computershop.dto.MonitorDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Monitor;
import org.springframework.stereotype.Component;

@Component
public class MonitorMapper implements Mapper{
    public Monitor dtoToModel(ProductDto dto) {
        MonitorDto monitorDto = (MonitorDto) dto;
        return new Monitor(
                defaultId,
                dto.getSerNomer(),
                dto.getManufacturer(),
                dto.getPrice(),
                dto.getQuantity(),
                monitorDto.getDiagonal());
    }
}
