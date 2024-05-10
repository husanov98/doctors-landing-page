package uz.mh.doctorslandingpage.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import uz.mh.doctorslandingpage.enums.Cities;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ParameterObject
public class AddressDto {
    private Cities city;
    private String region;
    private String street;
    private String orient;
    private Integer houseNumber;
    private Long doctorId;
}
