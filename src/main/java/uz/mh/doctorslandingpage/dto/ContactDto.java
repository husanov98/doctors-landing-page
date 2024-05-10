package uz.mh.doctorslandingpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class ContactDto {
    private String phoneNumber;
    private Long doctorId;
}
