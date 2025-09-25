package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public AddressResponseDTO saveAddress(@RequestBody NewAddressRequestDTO newAddressRequestDTO) {
        return null;
    }

    @GetMapping("/{id}")
    public AddressResponseDTO getAddressById(@PathVariable Integer id) {
        return null;
    }

    @GetMapping
    public PaginationResponse<AddressResponseDTO> listPaginated(
            @RequestParam int pageNo,
            @RequestParam int size) {
        return null;
    }

    @GetMapping("/search")
    public List<AddressResponseDTO> searchAddressByRsql(@RequestParam String query) {
        return List.of();
    }

    @PutMapping("/{id}")
    public AddressResponseDTO updateAddress(
            @PathVariable Integer id,
            @RequestBody UpdateAddressRequestDTO updateAddressRequestDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Integer id) {
    }
}
