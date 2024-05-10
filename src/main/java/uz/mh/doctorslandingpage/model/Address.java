package uz.mh.doctorslandingpage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mh.doctorslandingpage.enums.Cities;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "addresses")
public class Address extends Auditable{
    @Enumerated(EnumType.STRING)
    private Cities city;
    private String region;
    private String street;
    private String orient;
    @Column(unique = true)
    private Integer houseNumber;
    @ManyToMany
    private Set<Doctor> doctors;
}
