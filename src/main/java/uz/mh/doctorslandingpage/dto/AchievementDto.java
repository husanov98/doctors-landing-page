package uz.mh.doctorslandingpage.dto;

import lombok.*;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class AchievementDto {
    private String title;
    private Long doctorId;
    private Long id;
}
