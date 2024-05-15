package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.DoctorAdviceDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.DoctorAdviceService;

@RestController
public class DoctorAdviceController {
    private final DoctorAdviceService doctorAdviceService;

    public DoctorAdviceController(DoctorAdviceService doctorAdviceService) {
        this.doctorAdviceService = doctorAdviceService;
    }
    @PostMapping(value = "/addDoctorAdvice",consumes = {"multipart/form-data"})
    public ResponseEntity<?> addDoctorAdvice(@RequestBody DoctorAdviceDto dto){
        ApiResponse<String> apiResponse = doctorAdviceService.addDoctorAdvice(dto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
