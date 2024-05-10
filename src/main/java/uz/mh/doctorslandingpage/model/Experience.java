package uz.mh.doctorslandingpage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "experiences")
public class Experience extends Auditable{
    private Date fromStartDate;
    private Date untilEndDate;
    private String company;
    @ManyToMany
    private Set<Doctor> doctors;
}
