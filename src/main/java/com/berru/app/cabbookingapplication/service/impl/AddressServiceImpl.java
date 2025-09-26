package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.AddressResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewAddressRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateAddressRequestDTO;
import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.mapper.AddressMapper;
import com.berru.app.cabbookingapplication.repository.AddressRepository;
import com.berru.app.cabbookingapplication.rsql.CustomRsqlVisitor;
import com.berru.app.cabbookingapplication.service.AddressService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
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

        List<AddressResponseDTO> addressResponseDTOList = addressPage.getContent().stream()
                .map(addressMapper::toAddressDTO)
                .collect(Collectors.toList());

        return PaginationResponse .<AddressResponseDTO>builder()
                .content(addressResponseDTOList)
                .pageNo(addressPage.getNumber())
                .pageSize(addressPage.getSize())
                .totalElements(addressPage.getTotalElements())
                .totalPages(addressPage.getTotalPages())
                .isLast(addressPage.isLast())
                .build();
    }

    @Override
    @Transactional
    public List<AddressResponseDTO> searchAddressByRsql(String query) {
        RSQLParser parser = new RSQLParser();
        Node rootNode = parser.parse(query);

        CustomRsqlVisitor<Address> visitor = new CustomRsqlVisitor<>();
        Specification<Address> spec = rootNode.accept(visitor);

        List<Address> addresses = addressRepository.findAll(spec);

        return addresses.stream()
                .map(addressMapper::toAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(Integer id, UpdateAddressRequestDTO updateAddressRequestDTO) {
        return null;
    }

    @Override
    @Transactional
    public void deleteAddressById(Integer id) {

    }
}
