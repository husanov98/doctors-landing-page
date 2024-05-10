package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.AchievementDto;
import uz.mh.doctorslandingpage.mapper.AchievementMapper;
import uz.mh.doctorslandingpage.model.Achievement;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.AchievementRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
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

}
