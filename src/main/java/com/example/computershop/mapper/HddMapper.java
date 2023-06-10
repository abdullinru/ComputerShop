package com.example.computershop.mapper;

import com.example.computershop.dto.ComputerDto;
import com.example.computershop.dto.HDDDto;
import com.example.computershop.dto.ProductDto;
import com.example.computershop.model.Computer;
import com.example.computershop.model.HDD;
import org.springframework.stereotype.Component;

@Component
public class HddMapper implements Mapper{
    public HDD dtoToModel(ProductDto dto) {
        HDDDto hddDto = (HDDDto) dto;
        return new HDD(
                defaultId,
                dto.getSerNomer(),
                dto.getManufacturer(),
                dto.getPrice(),
                dto.getQuantity(),
                hddDto.getStorage());
    }
}
