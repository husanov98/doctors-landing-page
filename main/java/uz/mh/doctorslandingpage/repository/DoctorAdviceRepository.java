package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.doctorslandingpage.model.DoctorAdvice;

public interface DoctorAdviceRepository extends JpaRepository<DoctorAdvice,Long> {
}
