package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.PatientDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.PatientService;

@RestController
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping(value = "/sendComplaint",consumes = {"multipart/form-data"})
    public ResponseEntity<?> sendComplaint(@RequestBody PatientDto patientDto){
        ApiResponse<String> apiResponse = patientService.sendComplaint(patientDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
