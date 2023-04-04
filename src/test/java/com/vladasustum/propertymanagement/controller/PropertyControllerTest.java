package com.vladasustum.propertymanagement.controller;

import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.service.PropertyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @Test
    @DisplayName("Test success scenario for saving new Property")
    void testSaveProperty() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Dummy property");

        PropertyDTO saveProperty = new PropertyDTO();
        saveProperty.setId(1L);
        saveProperty.setTitle(propertyDTO.getTitle());

        Mockito.when(propertyService.saveProperty(propertyDTO)).thenReturn(saveProperty);

        ResponseEntity<PropertyDTO> responseEntity = propertyController.saveProperty(propertyDTO);

        assertNotNull(responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Test success scenario for fetching all properties")
    void testGetAllProperties() {
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setTitle("Dummy property");
        propertyDTOList.add(propertyDTO);

        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyDTOList);

        ResponseEntity<List<PropertyDTO>> responseEntities = propertyController.getAllProperties();

        assertEquals(1, responseEntities.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntities.getStatusCode().value());
    }

    @Test
    @DisplayName("Test success scenario for updating only price of property")
    void testUpdatePropertyPrice() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPrice(300.500);
        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(), Mockito.anyLong())).thenReturn(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyPrice(propertyDTO, 1L);

        assertEquals(300.500, responseEntity.getBody().getPrice());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Test success scenario for updating property")
    void testUpdateProperty() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title");
        propertyDTO.setOwnerEmail("test@email.com");
        propertyDTO.setAddress("testAddress");

        Mockito.when(propertyService.updateProperty(Mockito.any(), Mockito.anyLong())).thenReturn(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = propertyController.updateProperty(propertyDTO, 1L);

        assertEquals("Title", propertyDTO.getTitle());
        assertEquals("test@email.com", propertyDTO.getOwnerEmail());
        assertEquals("testAddress", propertyDTO.getAddress());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());

    }

    @Test
    @DisplayName("Test success scenario for updating only description of property")
    void testUpdatePropertyDescription() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setDescription("Dummy Desc");
        Mockito.when(propertyService.updatePropertyDescription(Mockito.any(), Mockito.anyLong())).thenReturn(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyDescription(propertyDTO, 1L);

        assertEquals("Dummy Desc", responseEntity.getBody().getDescription());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Test success scenario for deletion of property")
    void testDeleteProperty() {

        ResponseEntity<Void> responseEntity = propertyController.deleteProperty(1L);


        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCode().value());
    }
}