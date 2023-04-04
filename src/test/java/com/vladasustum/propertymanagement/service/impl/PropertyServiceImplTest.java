package com.vladasustum.propertymanagement.service.impl;

import com.vladasustum.propertymanagement.converter.PropertyConverter;
import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.entity.PropertyEntity;
import com.vladasustum.propertymanagement.exception.BusinessException;
import com.vladasustum.propertymanagement.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {

    @InjectMocks
    private PropertyServiceImpl propertyServiceImpl;
    @Mock
    private PropertyConverter propertyConverter;
    @Mock
    private PropertyRepository propertyRepository;

    @Test
    void testSaveProperty_Success() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title");
        propertyServiceImpl.saveProperty(propertyDTO);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Title");

        PropertyEntity savedProperty = new PropertyEntity();
        savedProperty.setTitle("Title");
        savedProperty.setId(1L);

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);

        when(propertyConverter.convertDTOtoEntity(Mockito.any())).thenReturn(propertyEntity);
        when(propertyRepository.save(Mockito.any())).thenReturn(savedProperty);
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO result = propertyServiceImpl.saveProperty(propertyDTO);

        assertEquals(savedDTO.getId(), result.getId());
        assertEquals(savedDTO.getTitle(), result.getTitle());
    }

    @Test
    void testGetAllProperties_Success() {
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        PropertyEntity property = new PropertyEntity();
        property.setTitle("Title");
        property.setId(1L);
        propertyEntities.add(property);

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);

        when(propertyRepository.findAll()).thenReturn(propertyEntities);
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);
        List<PropertyDTO> propertyDTOList = propertyServiceImpl.getAllProperties();

        assertEquals(1, propertyDTOList.size());
    }

    @Test
    void testUpdateProperty_Success() {

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);
        savedDTO.setPrice(2334.4);
        savedDTO.setAddress("random street");
        savedDTO.setDescription("abc");
        savedDTO.setOwnerEmail("owner@email.com");
        savedDTO.setOwnerName("owner");

        PropertyEntity pe = new PropertyEntity();
        pe.setTitle("Title");
        pe.setId(1L);
        pe.setPrice(2334.4);
        pe.setAddress("random street");
        pe.setDescription("abc");
        pe.setOwnerEmail("owner@email.com");
        pe.setOwnerName("owner");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedProperty = propertyServiceImpl.updateProperty(savedDTO, 1L);

        assertEquals(savedDTO.getTitle(), updatedProperty.getTitle());
        assertEquals(savedDTO.getPrice(), updatedProperty.getPrice());
        assertEquals(savedDTO.getAddress(), updatedProperty.getAddress());
        assertEquals(savedDTO.getId(), updatedProperty.getId());
        assertEquals(savedDTO.getDescription(), updatedProperty.getDescription());
        assertEquals(savedDTO.getOwnerEmail(), updatedProperty.getOwnerEmail());
        assertEquals(savedDTO.getOwnerName(), updatedProperty.getOwnerName());


    }

    @Test
    void updatePropertyDescription() {

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);
        savedDTO.setPrice(2334.4);
        savedDTO.setAddress("random street");
        savedDTO.setDescription("updated desc");
        savedDTO.setOwnerEmail("owner@email.com");
        savedDTO.setOwnerName("owner");


        PropertyEntity pe = new PropertyEntity();
        pe.setTitle("Title");
        pe.setId(1L);
        pe.setPrice(2334.4);
        pe.setAddress("random street");
        pe.setDescription("updated desc");
        pe.setOwnerEmail("owner@email.com");
        pe.setOwnerName("owner");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);
        PropertyDTO updatedDesc = propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);

        assertEquals(savedDTO.getDescription(), updatedDesc.getDescription());
    }

    @Test
    void updatePropertyDescription_Failure() {


        PropertyDTO savedDTO = new PropertyDTO();

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            PropertyDTO updatedPropertyDesc = propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);
        });

        assertEquals("No property found for update", exception.getMessage());
    }

    @Test
    void updatePropertyPrice() {

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);
        savedDTO.setPrice(2334.4);
        savedDTO.setAddress("random street");
        savedDTO.setDescription("updated desc");
        savedDTO.setOwnerEmail("owner@email.com");
        savedDTO.setOwnerName("owner");


        PropertyEntity pe = new PropertyEntity();
        pe.setTitle("Title");
        pe.setId(1L);
        pe.setPrice(2334.4);
        pe.setAddress("random street");
        pe.setDescription("updated desc");
        pe.setOwnerEmail("owner@email.com");
        pe.setOwnerName("owner");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);
        PropertyDTO updatedDesc = propertyServiceImpl.updatePropertyPrice(savedDTO, 1L);

        assertEquals(savedDTO.getPrice(), updatedDesc.getPrice());
    }


}