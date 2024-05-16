package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.EducationDto;
import uz.mh.doctorslandingpage.mapper.EducationMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.model.Education;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.repository.EducationRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;
    private final DoctorRepository doctorRepository;

    public EducationService(EducationRepository educationRepository, EducationMapper educationMapper, DoctorRepository doctorRepository) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
        this.doctorRepository = doctorRepository;
    }

    public ApiResponse<String> addEducation(EducationDto educationDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Education education = educationMapper.mapToEducation(educationDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(educationDto.getDoctorId());
        Optional<Education> optionalEducation = Optional.empty();
        if (educationDto.getId() != null){
            optionalEducation = educationRepository.findById(educationDto.getId());
        }
        if (optionalDoctor.isPresent()){
            if (optionalEducation.isEmpty()){
                education.setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
                educationRepository.save(education);
            }else {
                optionalEducation.get().setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
            }
            apiResponse.setBody("Education successfully added");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This user is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }
}
