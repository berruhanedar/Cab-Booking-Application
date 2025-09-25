package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.AddressResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewAddressRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateAddressRequestDTO;
import com.berru.app.cabbookingapplication.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponseDTO toAddressDTO(Address address);

    Address toAddress(AddressResponseDTO addressResponseDTO);

    Address toAddress(NewAddressRequestDTO newAddressRequestDTO);

    void updateAddressFromDTO(UpdateAddressRequestDTO updateAddressRequestDTO, @MappingTarget Address address);

}