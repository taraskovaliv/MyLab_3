package com.kovaliv.lab3.config;

import com.kovaliv.lab3.mappers.OrderDtoMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MapperConfig {
    private final OrderDtoMapper orderDtoMapper;

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return addConverters(modelMapper);
    }

    private ModelMapper addConverters(ModelMapper modelMapper) {
        modelMapper.addConverter(orderDtoMapper);
        return modelMapper;
    }
}
