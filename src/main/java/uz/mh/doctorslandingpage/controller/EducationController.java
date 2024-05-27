package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.EducationDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.EducationService;
import javax.xml.bind.annotation.XmlElement;
@RestController
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }
    @PostMapping(value = "/addEducation",consumes = "multipart/form-data")
    public ResponseEntity<?> addEducation(@RequestBody EducationDto educationDto){
        ApiResponse<String> apiResponse = educationService.addEducation(educationDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
