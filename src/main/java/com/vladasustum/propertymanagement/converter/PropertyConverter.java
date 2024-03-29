package com.vladasustum.propertymanagement.converter;

import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {

        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        pe.setPrice(propertyDTO.getPrice());
        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {

        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setAddress(propertyEntity.getAddress());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());
        propertyDTO.setDescription(propertyEntity.getDescription());

        return propertyDTO;
    }
}