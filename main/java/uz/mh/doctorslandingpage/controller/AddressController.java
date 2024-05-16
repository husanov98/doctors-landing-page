package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mh.doctorslandingpage.dto.AddressDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.AddressService;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping(value = "/getAllAddresses")
    public ResponseEntity<?> getAllAddresses(Pageable pageable){
        ApiResponse<Page<AddressDto>> allAddresses = addressService.getAllAddresses(pageable);
        return new ResponseEntity<>(allAddresses, HttpStatusCode.valueOf(allAddresses.getStatus()));
    }
    @GetMapping(value = "/getAddress",consumes = {"multipart/form-data"})
    public ResponseEntity<?> getAddress(@RequestParam Long id){
        ApiResponse<AddressDto> apiResponse = addressService.getAddress(id);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PostMapping(value = "/addDoctorAddress",consumes = {"multipart/form-data"})
    public ResponseEntity<?> createAddress(@RequestBody AddressDto dto){
        ApiResponse<String> apiResponse = addressService.createAddress(dto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PostMapping(value = "/updateAddress")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDto addressDto){
        ApiResponse<String> apiResponse = addressService.updateAddress(addressDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @DeleteMapping(value = "/deleteAddress")
    public ResponseEntity<?> deleteAddress(@RequestPart Long id){
        ApiResponse<String> apiResponse = addressService.deleteAddress(id);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
