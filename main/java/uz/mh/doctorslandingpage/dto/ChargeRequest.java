package uz.mh.doctorslandingpage.dto;

import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class ChargeRequest {
    private Long amount;
}
