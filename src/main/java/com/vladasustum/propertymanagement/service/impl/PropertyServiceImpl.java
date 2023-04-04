package com.vladasustum.propertymanagement.service.impl;

import com.vladasustum.propertymanagement.converter.PropertyConverter;
import com.vladasustum.propertymanagement.dto.PropertyDTO;
import com.vladasustum.propertymanagement.entity.PropertyEntity;
import com.vladasustum.propertymanagement.exception.BusinessException;
import com.vladasustum.propertymanagement.repository.PropertyRepository;
import com.vladasustum.propertymanagement.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {


    private final PropertyRepository propertyRepository;


    private final PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);

        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntityToDTO(pe);

        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> propertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for (PropertyEntity pe : propertyEntities) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propertyDTOList.add(dto);
        }
        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId){

        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {

            PropertyEntity pe = propertyEntity.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {
            PropertyEntity pe = propertyEntity.get();
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        } else {
            throw new BusinessException("No property found for update");
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {
            PropertyEntity pe = propertyEntity.get();
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);

    }
}
