package uz.mh.doctorslandingpage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.AddressDto;
import uz.mh.doctorslandingpage.mapper.AddressMapper;
import uz.mh.doctorslandingpage.model.Address;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.AddressRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiErrorResponse;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final DoctorRepository doctorRepository;


    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper, DoctorRepository doctorRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.doctorRepository = doctorRepository;
    }

    public ApiResponse<String> createAddress(AddressDto addressDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Address address = addressMapper.mapToAddress(addressDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(addressDto.getDoctorId());
        Optional<Address> addressOptional = addressRepository.findBuHouseNumber(addressDto.getHouseNumber());
        if (optionalDoctor.isPresent()){
            if (addressOptional.isEmpty()){
                address.setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
                addressRepository.save(address);
            }else {
                addressOptional.get().setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
            }
            apiResponse.setBody("Address successfully added");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This user is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }
    public ApiResponse<Page<AddressDto>> getAllAddresses(Pageable pageable){
        ApiResponse<Page<AddressDto>> apiResponse = new ApiResponse<>();
        Page<Address> addresses = addressRepository.findAll(pageable);
        Page<AddressDto> addressDtoPage = addresses.map(addressMapper::mapToAddressDto);
        apiResponse.setBody(addressDtoPage);
        apiResponse.setStatus(200);
        return apiResponse;
    }
    public ApiResponse<AddressDto> getAddress(Long id){
        AddressDto addressDto;
        ApiResponse<AddressDto> apiResponse = new ApiResponse<>();
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            addressDto = addressMapper.mapToAddressDto(optionalAddress.get());
            apiResponse.setStatus(200);
            apiResponse.setBody(addressDto);
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Address not found"));
        }
        return apiResponse;
    }
    public ApiResponse<String> updateAddress(AddressDto addressDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Address> optionalAddress = addressRepository.findById(addressDto.getId());
        if (optionalAddress.isPresent()) {
            Address exitingAddress = optionalAddress.get();
            exitingAddress.setHouseNumber(addressDto.getHouseNumber());
            exitingAddress.setCity(addressDto.getCity());
            exitingAddress.setRegion(addressDto.getRegion());
            exitingAddress.setStreet(addressDto.getStreet());
            exitingAddress.setOrient(addressDto.getOrient());

            addressRepository.save(exitingAddress);

            apiResponse.setBody("Address successfully changed");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Address not found"));
        }
        return apiResponse;
    }
    public ApiResponse<String> deleteAddress(Long id){
        return null;
    }
}
