package com.vladasustum.propertymanagement.service;

import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.exception.BusinessException;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) throws BusinessException;

    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);
}
