package uz.mh.doctorslandingpage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.DoctorDto;
import uz.mh.doctorslandingpage.mapper.DoctorMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiErrorResponse;
import uz.mh.doctorslandingpage.response.ApiResponse;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public ApiResponse<Page<DoctorDto>> getAllDoctors(Pageable pageable){
        ApiResponse<Page<DoctorDto>> apiResponse = new ApiResponse<>();
        Page<Doctor> doctorsPage = doctorRepository.findAll(pageable);
        Page<DoctorDto> doctorDtos = doctorsPage.map(DoctorMapper::mapToDoctorDto);
        apiResponse.setBody(doctorDtos);
        apiResponse.setStatus(201);
        return apiResponse;
    }
    public ApiResponse<DoctorDto> getDoctorById(Long id){
        DoctorDto doctorDto;
        ApiResponse<DoctorDto> apiResponse = new ApiResponse<>();
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            doctorDto = DoctorMapper.mapToDoctorDto(optionalDoctor.get());
            apiResponse.setBody(doctorDto);
            apiResponse.setStatus(200);
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Doctor not found"));
        }
        return apiResponse;
    }
    public ApiResponse<String> registerDoctor(DoctorDto doctorDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findByUsername(doctorDto.getUsername());
        if (optionalDoctor.isEmpty()){
            doctorRepository.save(doctor);
            apiResponse.setBody("Doctor successfully registered");
            apiResponse.setSuccess(true);
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This username already taken");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(409);
        }
        return apiResponse;
    }
    public ApiResponse<String> updateDoctor(DoctorDto doctorDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Doctor> optionalDoctor = doctorRepository.findByUsername(doctorDto.getUsername());
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            existingDoctor.setFirstname(doctorDto.getFirstname());
            existingDoctor.setLastname(doctorDto.getLastname());
            existingDoctor.setMiddleName(doctorDto.getMiddleName());
            existingDoctor.setConsultationPrice(doctorDto.getConsultationPrice());
            existingDoctor.setLanguages(doctorDto.getLanguages());
            existingDoctor.setProfessions(doctorDto.getProfessions());
            existingDoctor.setUsername(doctorDto.getUsername());

            apiResponse.setBody("Doctor successfully updated");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setSuccess(false);
            apiResponse.setStatus(404);
            apiResponse.setError(new ApiErrorResponse("Doctor with this username not found."));
        }
        return apiResponse;
    }
    public ApiResponse<String> deleteDoctor(Long id){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()){
            doctorRepository.deleteById(id);
            apiResponse.setStatus(201);
            apiResponse.setBody("Doctor successfully deleted");
        }else {
            apiResponse.setSuccess(false);
            apiResponse.setStatus(404);
            apiResponse.setError(new ApiErrorResponse("Doctor with this username not found."));
        }
        return apiResponse;
    }
}
