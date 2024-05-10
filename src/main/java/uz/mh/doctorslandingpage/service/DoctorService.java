package uz.mh.doctorslandingpage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.DoctorDto;
import uz.mh.doctorslandingpage.mapper.DoctorMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ApiResponse<String> registerDoctor(DoctorDto doctorDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findByUsername(doctorDto.getUsername());
        if (optionalDoctor.isEmpty()){
            doctorRepository.save(doctor);
            apiResponse.setBody("User successfully registered");
            apiResponse.setSuccess(true);
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This username already taken");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(409);
        }
        return apiResponse;
    }
    public ApiResponse<Page<DoctorDto>> getAllDoctors(Pageable pageable){
        ApiResponse<Page<DoctorDto>> apiResponse = new ApiResponse<>();
        Page<Doctor> doctorsPage = doctorRepository.findAll(pageable);
        Page<DoctorDto> doctorDtos = doctorsPage.map(DoctorMapper::mapToDoctorDto);
        apiResponse.setBody(doctorDtos);
        apiResponse.setStatus(201);
        return apiResponse;
    }
}
