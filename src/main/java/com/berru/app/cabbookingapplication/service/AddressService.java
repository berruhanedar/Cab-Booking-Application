package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressResponseDTO saveAddress(NewAddressRequestDTO newAddressRequestDTO);

    AddressResponseDTO getAddressById(Integer id);

    PaginationResponse<AddressResponseDTO> listPaginated(int pageNo, int size);

    List<AddressResponseDTO> searchAddressByRsql(String query);

    AddressResponseDTO updateAddress(Integer id, UpdateAddressRequestDTO updateAddressRequestDTO);

    void deleteAddressById(Integer id);

}
