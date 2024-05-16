package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.PatientDto;
import uz.mh.doctorslandingpage.model.Patient;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    public Patient mapToPatient(PatientDto patientDto){return modelMapper.map(patientDto, Patient.class);}
    public PatientDto mapToPatientDto(Patient patient){return modelMapper.map(patient, PatientDto.class);}
}
