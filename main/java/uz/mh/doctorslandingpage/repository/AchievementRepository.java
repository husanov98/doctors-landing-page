package uz.mh.doctorslandingpage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.mh.doctorslandingpage.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    @Query(value = "select * from achievements as a join \n" +
            "achievements_doctors on a.id = achievements_doctors.achievements_id\n" +
            "where achievements_doctors.doctors_id =:doctorId",nativeQuery = true)
    Page<Achievement> findAchievementsByDoctorId(@Param("doctorId") Long doctorId, Pageable pageable);
}
