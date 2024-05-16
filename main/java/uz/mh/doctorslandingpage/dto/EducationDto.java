package uz.mh.doctorslandingpage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

import java.time.LocalDate;
import java.util.Date;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class EducationDto {
    private Long id;
    @ApiModelProperty(required = true,example = "2016-01-01")
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate fromStartDate;
    @ApiModelProperty(required = true,example = "2016-01-01")
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate untilEndDate;
    private String university;
    private Long doctorId;

}
