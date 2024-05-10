package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.AddressDto;
import uz.mh.doctorslandingpage.mapper.AddressMapper;
import uz.mh.doctorslandingpage.model.Address;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.AddressRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
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

}
