package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.DoctorAdviceDto;
import uz.mh.doctorslandingpage.dto.DoctorAdviceDto;
import uz.mh.doctorslandingpage.model.DoctorAdvice;

@Component
public class DoctorAdviceMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static DoctorAdvice mapToDoctorService(DoctorAdviceDto doctorServiceDto){
        return modelMapper.map(doctorServiceDto, DoctorAdvice.class);
    }
    public static DoctorAdviceDto mapToDoctorServiceDto(DoctorAdvice doctorService){
        return modelMapper.map(doctorService, DoctorAdviceDto.class);
    }
}
