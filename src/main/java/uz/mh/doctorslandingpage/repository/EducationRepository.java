package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.doctorslandingpage.model.Education;

public interface EducationRepository extends JpaRepository<Education,Long> {
}
