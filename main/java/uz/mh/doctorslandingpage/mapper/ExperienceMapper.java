package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.ExperienceDto;
import uz.mh.doctorslandingpage.model.Experience;


@Component
public class ExperienceMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public Experience mapToExperience(ExperienceDto experienceDto){return modelMapper.map(experienceDto, Experience.class);}
    public ExperienceDto mapToExperienceDto(Experience experience){return modelMapper.map(experience, ExperienceDto.class);}
}
