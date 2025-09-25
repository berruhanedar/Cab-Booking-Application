package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;

import java.util.List;

public interface AddressService {

    AddressResponseDTO saveUser(NewAddressRequestDTO newAddressRequestDTO);

    AddressResponseDTO getAddressById(Integer id);

    PaginationResponse<AddressResponseDTO> listPaginated(int pageNo, int size);

    List<AddressResponseDTO> searchAddressByRsql(String query);

    AddressResponseDTO updateAddress(Integer id, UpdateAddressRequestDTO updateAddressRequestDTO);

    void deleteAddressById(Integer id);

}
