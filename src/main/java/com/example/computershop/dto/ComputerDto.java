package com.example.computershop.dto;

import com.example.computershop.model.enums.FormFactor;
import lombok.Data;

@Data
public class ComputerDto extends ProductDto{
    private FormFactor type;
}
