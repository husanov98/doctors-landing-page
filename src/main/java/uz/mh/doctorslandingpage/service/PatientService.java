package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.PatientDto;
import uz.mh.doctorslandingpage.mapper.PatientMapper;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.model.Patient;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.repository.PatientRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientMapper patientMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PatientService(PatientMapper patientMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.patientMapper = patientMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public ApiResponse<String> sendComplaint(PatientDto patientDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Patient patient = patientMapper.mapToPatient(patientDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(patientDto.getDoctorId());
        if (optionalDoctor.isPresent()){
            patient.setDoctors(new HashSet<>(List.of(optionalDoctor.get())));
            patientRepository.save(patient);
            apiResponse.setBody("You have successfully made an appointment");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This doctor is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }
}
