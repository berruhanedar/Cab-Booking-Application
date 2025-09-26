package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AddressResponseDTO> saveAddress(@RequestBody NewAddressRequestDTO newAddressRequestDTO) {
        AddressResponseDTO addressResponseDTO = addressService.saveAddress(newAddressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Integer id) {
        AddressResponseDTO addressResponseDTO = addressService.getAddressById(id);
        return ResponseEntity.ok(addressResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<AddressResponseDTO>> listPaginated(
            @RequestParam int pageNo,
            @RequestParam int size) {
        PaginationResponse<AddressResponseDTO> paginationResponseDTO = addressService.listPaginated(pageNo, size);
        return ResponseEntity.ok(paginationResponseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AddressResponseDTO>> searchAddressByRsql(@RequestParam String query) {
        List<AddressResponseDTO> addressResponseDTO = addressService.searchAddressByRsql(query);
        return ResponseEntity.ok(addressResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @PathVariable Integer id,
            @RequestBody UpdateAddressRequestDTO updateAddressRequestDTO) {
        AddressResponseDTO addressResponseDTO = addressService.updateAddress(id, updateAddressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Integer id) {
    }
}
