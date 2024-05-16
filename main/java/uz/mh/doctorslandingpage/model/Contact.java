package uz.mh.doctorslandingpage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "contacts")
public class Contact extends Auditable{
    @Column(unique = true)
    private String phoneNumber;
    @OneToOne
    private Doctor doctor;
}
