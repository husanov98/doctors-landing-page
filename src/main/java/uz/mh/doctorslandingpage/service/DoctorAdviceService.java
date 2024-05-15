package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.DoctorAdviceDto;
import uz.mh.doctorslandingpage.mapper.DoctorAdviceMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.model.DoctorAdvice;
import uz.mh.doctorslandingpage.repository.DoctorAdviceRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorAdviceService {
    private final DoctorAdviceRepository doctorAdviceRepository;
    private final DoctorAdviceMapper doctorAdviceMapper;
    private final DoctorRepository doctorRepository;

    public DoctorAdviceService(DoctorAdviceRepository doctorAdviceRepository, DoctorAdviceMapper doctorAdviceMapper, DoctorRepository doctorRepository) {
        this.doctorAdviceRepository = doctorAdviceRepository;
        this.doctorAdviceMapper = doctorAdviceMapper;
        this.doctorRepository = doctorRepository;
    }

    public ApiResponse<String> addDoctorAdvice(DoctorAdviceDto dto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        DoctorAdvice doctorAdvice = doctorAdviceMapper.mapToDoctorService(dto);
        Optional<Doctor> doctorOptional = doctorRepository.findById(dto.getDoctorId());
        Optional<DoctorAdvice> adviceOptional = Optional.empty();
        if (dto.getId() != null){
            adviceOptional = doctorAdviceRepository.findById(dto.getId());
        }
        if (doctorOptional.isPresent()){
            if (adviceOptional.isEmpty()){
                doctorAdvice.setDoctor(doctorOptional.get());
                doctorAdviceRepository.save(doctorAdvice);
            }else {
                adviceOptional.get().setDoctor(doctorOptional.get());
            }
            apiResponse.setBody("DoctorAdvice successfully added");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This doctor is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }
}
