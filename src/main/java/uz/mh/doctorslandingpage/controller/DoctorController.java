package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.AddressDto;
import uz.mh.doctorslandingpage.dto.DoctorDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.DoctorService;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/registerDoctor",consumes = {"multipart/form-data"})
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDto doctorDto){
        ApiResponse<String> apiResponse = doctorService.registerDoctor(doctorDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @GetMapping(value = "/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(Pageable pageable){
        ApiResponse<Page<DoctorDto>> allDoctors = doctorService.getAllDoctors(pageable);
        return new ResponseEntity<>(allDoctors,HttpStatusCode.valueOf(allDoctors.getStatus()));
    }
}
