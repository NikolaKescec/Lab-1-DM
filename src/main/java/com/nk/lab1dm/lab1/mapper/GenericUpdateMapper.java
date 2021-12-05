package com.nk.lab1dm.lab1.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericUpdateMapper {

    private final ModelMapper modelMapper;

    public <I> void map(I from, I to) {
        modelMapper.map(from, to);
    }

}
