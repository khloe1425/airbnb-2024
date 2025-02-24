package com.example.Airbnb.config;

import com.example.Airbnb.dto.request.DatPhongRequest;
import com.example.Airbnb.dto.response.DatPhongResponse;
import com.example.Airbnb.entity.DatPhongEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
