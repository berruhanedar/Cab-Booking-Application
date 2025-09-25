package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.AddressResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewAddressRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateAddressRequestDTO;
import com.berru.app.cabbookingapplication.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    @Override
    public AddressResponseDTO saveUser(NewAddressRequestDTO newAddressRequestDTO) {
        return null;
    }

    @Override
    public AddressResponseDTO getAddressById(Integer id) {
        return null;
    }

    @Override
    public PaginationResponse<AddressResponseDTO> listPaginated(int pageNo, int size) {
        return null;
    }

    @Override
    public List<AddressResponseDTO> searchAddressByRsql(String query) {
        return List.of();
    }

    @Override
    public AddressResponseDTO updateAddress(Integer id, UpdateAddressRequestDTO updateAddressRequestDTO) {
        return null;
    }

    @Override
    public void deleteAddressById(Integer id) {

    }
}
