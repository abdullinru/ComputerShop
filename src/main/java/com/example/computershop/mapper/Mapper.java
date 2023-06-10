package com.example.computershop.mapper;

import com.example.computershop.dto.*;
import com.example.computershop.model.*;

public interface Mapper {

    final Integer defaultId = 0;
    Product dtoToModel(ProductDto dto);

}
