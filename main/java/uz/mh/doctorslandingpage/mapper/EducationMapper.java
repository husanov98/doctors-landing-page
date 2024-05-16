package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.EducationDto;
import uz.mh.doctorslandingpage.model.Education;

@Component
public class EducationMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public Education mapToEducation(EducationDto educationDto){return modelMapper.map(educationDto, Education.class);}
    public EducationDto mapToEducationDto(Education education){return modelMapper.map(education, EducationDto.class);}
}
