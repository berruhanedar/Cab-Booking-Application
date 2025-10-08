package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.AddressResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewAddressRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateAddressRequestDTO;
import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.mapper.AddressMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.AddressRepository;
import com.berru.app.cabbookingapplication.service.AddressService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends GenericRsqlService<Address, AddressResponseDTO> implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final PaginationMapper paginationMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressMapper addressMapper,
                              PaginationMapper paginationMapper) {
        super(addressRepository, addressMapper::toAddressDTO);
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.paginationMapper = paginationMapper;
    }

    @Override
    @Transactional
    public AddressResponseDTO saveAddress(NewAddressRequestDTO newAddressRequestDTO) {
        Address address = addressMapper.toAddress(newAddressRequestDTO);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toAddressDTO(savedAddress);
    }

    @Override
    @Transactional
    public AddressResponseDTO getAddressById(Integer id) {
        Address address = addressRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Address not found"));
        return addressMapper.toAddressDTO(address);
    }

    @Override
    @Transactional
    public PaginationResponse<AddressResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);

        return paginationMapper.toPaginationResponse(addressPage, addressMapper::toAddressDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> searchAddressByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(Integer id, UpdateAddressRequestDTO updateAddressRequestDTO) {
        Address existing =  addressRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Address not found"));
        addressMapper.updateAddressFromDTO(updateAddressRequestDTO, existing);
        Address updatedAddress = addressRepository.save(existing);
        return addressMapper.toAddressDTO(updatedAddress);
    }

    @Override
    @Transactional
    public void deleteAddressById(Integer id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepository.delete(address);
    }
}
