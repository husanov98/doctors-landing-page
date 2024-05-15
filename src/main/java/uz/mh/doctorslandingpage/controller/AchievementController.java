package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mh.doctorslandingpage.dto.AchievementDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.AchievementService;

@RestController
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }
    @GetMapping(value = "/getAllAchievements")
    public ResponseEntity<?> getAllAchievements(Pageable pageable){
        ApiResponse<Page<AchievementDto>> allDoctor = achievementService.getAllDoctor(pageable);
        return new ResponseEntity<>(allDoctor,HttpStatusCode.valueOf(allDoctor.getStatus()));
    }
    @GetMapping(value = "/getAchievement")
    public ResponseEntity<?> getAchievement(@RequestParam Long id){
        ApiResponse<AchievementDto> apiResponse = achievementService.getAchievement(id);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PostMapping(value = "/createAchievement",consumes = {"multipart/form-data"})
    public ResponseEntity<?> createAchievement(@RequestBody AchievementDto achievementDto){
        ApiResponse<String> apiResponse = achievementService.createAchievement(achievementDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PostMapping(value = "/updateAchievement",consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateAchievement(@RequestBody AchievementDto achievementDto){
        ApiResponse<String> apiResponse = achievementService.updateAchievement(achievementDto);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @DeleteMapping(value = "/deleteAchievement",consumes = {"multipart/form-data"})
    public ResponseEntity<?> deleteAchievement(@RequestPart Long id){
        ApiResponse<String> apiResponse = achievementService.deleteAchievement(id);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @GetMapping(value = "/getAchievementsByDoctorId")
    public ResponseEntity<?> getAchievementsByDoctorId(Long id,Pageable pageable){
        ApiResponse<?> apiResponse = achievementService.getAchievementsByDoctorId(id,pageable);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
