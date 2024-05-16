package uz.mh.doctorslandingpage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "educations")
public class Education extends Auditable{
    private Date fromStartDate;
    private Date untilEndDate;
    private String university;
    @ManyToMany
    private Set<Doctor> doctors;
}
