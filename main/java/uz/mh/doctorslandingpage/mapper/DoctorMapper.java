package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.DoctorDto;
import uz.mh.doctorslandingpage.model.Doctor;

@Component
public class DoctorMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Doctor mapToDoctor(DoctorDto doctorDto){return modelMapper.map(doctorDto, Doctor.class);}
    public static DoctorDto mapToDoctorDto(Doctor doctor){return modelMapper.map(doctor, DoctorDto.class);}
}
