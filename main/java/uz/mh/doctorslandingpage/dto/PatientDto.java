package uz.mh.doctorslandingpage.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String phoneNumber;
    private String name;
    private String complaint;
    private Long doctorId;
}
