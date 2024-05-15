package uz.mh.doctorslandingpage.service;

import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.AchievementDto;
import uz.mh.doctorslandingpage.mapper.AchievementMapper;
import uz.mh.doctorslandingpage.model.Achievement;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.AchievementRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiErrorResponse;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final DoctorRepository doctorRepository;
    private final AchievementMapper achievementMapper;

    public AchievementService(AchievementRepository achievementRepository, DoctorRepository doctorRepository, AchievementMapper achievementMapper) {
        this.achievementRepository = achievementRepository;
        this.doctorRepository = doctorRepository;
        this.achievementMapper = achievementMapper;
    }

    public ApiResponse<Page<AchievementDto>> getAllDoctor(Pageable pageable){
        ApiResponse<Page<AchievementDto>> apiResponse = new ApiResponse<>();
        Page<Achievement> achievements = achievementRepository.findAll(pageable);
        Page<AchievementDto> achievementDtos = achievements.map(achievementMapper::mapToAchievementDto);
        apiResponse.setBody(achievementDtos);
        apiResponse.setStatus(200);
        return apiResponse;
    }

    public ApiResponse<AchievementDto> getAchievement(Long id){
        AchievementDto achievementDto;
        ApiResponse<AchievementDto> apiResponse = new ApiResponse<>();
        Optional<Achievement> optionalAchievement = achievementRepository.findById(id);
        if (optionalAchievement.isPresent()) {
            achievementDto = achievementMapper.mapToAchievementDto(optionalAchievement.get());
            apiResponse.setStatus(200);
            apiResponse.setBody(achievementDto);
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Achievement not found"));
        }
        return apiResponse;
    }
    public ApiResponse<String> createAchievement(AchievementDto achievementDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Achievement achievement = achievementMapper.mapToAchievement(achievementDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(achievementDto.getDoctorId());
        Optional<Achievement> optionalAchievement = Optional.empty();
        if (achievementDto.getId() != null) {
            optionalAchievement = achievementRepository.findById(achievementDto.getId());

        }
        if (optionalDoctor.isPresent()){
            if (optionalAchievement.isEmpty()){
                achievement.setTitle(achievement.getTitle());
                achievement.setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
                achievementRepository.save(achievement);
            }else {
                optionalAchievement.get().setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
            }
            apiResponse.setBody("Achievement successfully added");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This user is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }

    public ApiResponse<String> updateAchievement(AchievementDto achievementDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Achievement> optionalAchievement = achievementRepository.findById(achievementDto.getId());
        if (optionalAchievement.isPresent()) {
            Achievement exitingAchievement =optionalAchievement.get();
            exitingAchievement.setTitle(achievementDto.getTitle());
            achievementRepository.save(exitingAchievement);

            apiResponse.setBody("Achievement successfully changed");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Achievement not found"));
        }
        return apiResponse;
    }

    public ApiResponse<String> deleteAchievement(Long id){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Achievement> optionalAchievement = achievementRepository.findById(id);
        if (optionalAchievement.isPresent()) {
            achievementRepository.deleteById(id);
            apiResponse.setStatus(201);
            apiResponse.setBody("Achievement successfully deleted");
        }else {
            apiResponse.setStatus(404);
            apiResponse.setSuccess(false);
            apiResponse.setError(new ApiErrorResponse("Achievement not found"));
        }
        return apiResponse;
    }
    public ApiResponse<?> getAchievementsByDoctorId(Long id,Pageable pageable){
        ApiResponse<Page<AchievementDto>> apiResponse = new ApiResponse<>();
        Page<Achievement> achievements = achievementRepository.findAchievementsByDoctorId(id, pageable);
        Page<AchievementDto> achievementDtos = achievements.map(achievementMapper::mapToAchievementDto);
        apiResponse.setBody(achievementDtos);
        apiResponse.setStatus(200);
        return apiResponse;
    }
}
