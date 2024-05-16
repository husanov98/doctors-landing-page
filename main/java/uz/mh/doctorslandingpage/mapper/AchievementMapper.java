package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.AchievementDto;
import uz.mh.doctorslandingpage.model.Achievement;


@Component
public class AchievementMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    public Achievement mapToAchievement(AchievementDto achievementDto){
        return modelMapper.map(achievementDto,Achievement.class);
    }
    public AchievementDto mapToAchievementDto(Achievement achievement){
        return modelMapper.map(achievement, AchievementDto.class);
    }
}
