package com.example.computershop.dto;

import com.example.computershop.model.enums.NotebookSize;
import lombok.Data;

@Data
public class NotebookDto extends ProductDto{
    private NotebookSize size;
}
