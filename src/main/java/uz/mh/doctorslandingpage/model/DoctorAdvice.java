package uz.mh.doctorslandingpage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "services")
public class DoctorAdvice extends Auditable{
    private String name;
    private Integer price;
    @ManyToOne
    private Doctor doctor;
}
