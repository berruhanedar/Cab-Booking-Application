package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.AddressResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewAddressRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateAddressRequestDTO;
import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.mapper.AddressMapper;
import com.berru.app.cabbookingapplication.repository.AddressRepository;
import com.berru.app.cabbookingapplication.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }


    @Transactional
    @Override
    public AddressResponseDTO saveAddress(NewAddressRequestDTO newAddressRequestDTO) {
        Address address = addressMapper.toAddress(newAddressRequestDTO);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toAddressDTO(savedAddress);
    }

    @Transactional
    @Override
    public AddressResponseDTO getAddressById(Integer id) {
        Address address = addressRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Address not found"));
        return addressMapper.toAddressDTO(address);
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
