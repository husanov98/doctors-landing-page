package uz.mh.doctorslandingpage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mh.doctorslandingpage.enums.Languages;
import uz.mh.doctorslandingpage.enums.Professions;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "doctors")
public class Doctor extends Auditable{
    private String firstname;
    private String lastname;
    private String middleName;
    @Column(unique = true)
    private String username;
    @Enumerated(EnumType.STRING)
    private List<Languages> languages;
    @Enumerated(EnumType.STRING)
    private List<Professions> professions;
    private Integer consultationPrice;
}
