package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.ExperienceDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.ExperienceService;

@RestController
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }
    @PostMapping(value = "/addExperience",consumes = {"multipart/form-data"})
    public ResponseEntity<?> addExperience(@RequestBody ExperienceDto experienceDto){
        ApiResponse<String> apiResponse = experienceService.addExperience(experienceDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
