package com.nk.lab1dm.lab1.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericCreateMapper {

    private final ModelMapper modelMapper;

    public <I, D> D map(I input, Class<D> outputClass) {
        return modelMapper.map(input, outputClass);
    }

}
