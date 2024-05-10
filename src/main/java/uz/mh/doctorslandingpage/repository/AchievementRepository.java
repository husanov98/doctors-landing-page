package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.doctorslandingpage.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement,Long> {
}
