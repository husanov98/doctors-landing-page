package uz.mh.doctorslandingpage.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mh.doctorslandingpage.dto.DoctorDto;
import uz.mh.doctorslandingpage.response.ApiResponse;
import uz.mh.doctorslandingpage.service.DoctorService;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping(value = "/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(Pageable pageable){
        ApiResponse<Page<DoctorDto>> allDoctors = doctorService.getAllDoctors(pageable);
        return new ResponseEntity<>(allDoctors,HttpStatusCode.valueOf(allDoctors.getStatus()));
    }
    @GetMapping(value = "/getDoctor",consumes = {"multipart/form-data"})
    public ResponseEntity<?> getDoctorById(@RequestPart Long id){
        ApiResponse<DoctorDto> apiResponse = doctorService.getDoctorById(id);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PutMapping(value = "/registerDoctor",consumes = {"multipart/form-data"})
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDto doctorDto){
        ApiResponse<String> apiResponse = doctorService.registerDoctor(doctorDto);
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @PostMapping(value = "/updateDoctor",consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDto doctorDto){
        ApiResponse<String> apiResponse = doctorService.updateDoctor(doctorDto);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
    @DeleteMapping(value = "/deleteDoctor",consumes = {"multipart/form-data"})
    public ResponseEntity<?> deleteDoctor(@RequestPart Long id){
        ApiResponse<String> apiResponse = doctorService.deleteDoctor(id);
        return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(apiResponse.getStatus()));
    }
}
