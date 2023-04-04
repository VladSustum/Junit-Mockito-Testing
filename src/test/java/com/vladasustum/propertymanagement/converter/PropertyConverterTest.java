package com.vladasustum.propertymanagement.converter;

import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.entity.PropertyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PropertyConverterTest {

    @InjectMocks
    private PropertyConverter propertyConverter;

    @Test
    void testConvertDTOtoEntity_Success() {

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title");
        propertyDTO.setAddress("Address");
        propertyDTO.setDescription("Description");
        propertyDTO.setPrice(12345.555);
        PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);

        assertEquals(propertyDTO.getTitle(), propertyEntity.getTitle());
        assertEquals(propertyDTO.getAddress(), propertyEntity.getAddress());
        assertEquals(propertyDTO.getDescription(), propertyEntity.getDescription());
        assertEquals(propertyDTO.getPrice(), propertyEntity.getPrice());
    }

    @Test
    void testConvertEntityToDTO_Success() {

        PropertyEntity entity = new PropertyEntity();
        entity.setTitle("Title");
        entity.setAddress("Address");
        entity.setDescription("Description");
        entity.setPrice(12345.555);
        PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(entity);

        assertEquals(entity.getTitle(), propertyDTO.getTitle());
        assertEquals(entity.getAddress(), propertyDTO.getAddress());
        assertEquals(entity.getDescription(), propertyDTO.getDescription());
        assertEquals(entity.getPrice(), propertyDTO.getPrice());
    }
}