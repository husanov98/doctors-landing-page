package uz.mh.doctorslandingpage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "patients")
public class Patient extends Auditable{
    @Column(unique = true)
    private String phoneNumber;
    private String name;
    private String complaint;
    @ManyToMany
    private Set<Doctor> doctors;
}
