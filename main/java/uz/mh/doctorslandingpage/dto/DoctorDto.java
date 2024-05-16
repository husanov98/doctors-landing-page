package uz.mh.doctorslandingpage.dto;


import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import uz.mh.doctorslandingpage.enums.Languages;
import uz.mh.doctorslandingpage.enums.Professions;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class DoctorDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String middleName;
    private List<Languages> languages;
    private List<Professions> professions;
    private Integer consultationPrice;
    private String username;
}
