package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.doctorslandingpage.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
