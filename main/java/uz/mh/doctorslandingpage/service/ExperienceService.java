package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.ExperienceDto;
import uz.mh.doctorslandingpage.mapper.ExperienceMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.model.Experience;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.repository.ExperienceRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final DoctorRepository doctorRepository;

    public ExperienceService(ExperienceRepository experienceRepository, ExperienceMapper experienceMapper, DoctorRepository doctorRepository) {
        this.experienceRepository = experienceRepository;
        this.experienceMapper = experienceMapper;
        this.doctorRepository = doctorRepository;
    }
    public ApiResponse<String> addExperience(ExperienceDto experienceDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Experience experience = experienceMapper.mapToExperience(experienceDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(experienceDto.getDoctorId());
        Optional<Experience> optionalEducation = Optional.empty();
        if (experienceDto.getId() != null){
            optionalEducation = experienceRepository.findById(experienceDto.getId());
        }
        if (optionalDoctor.isPresent()){
            if (optionalEducation.isEmpty()){
                experience.setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
                experienceRepository.save(experience);
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
