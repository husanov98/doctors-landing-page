package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.doctorslandingpage.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
}
