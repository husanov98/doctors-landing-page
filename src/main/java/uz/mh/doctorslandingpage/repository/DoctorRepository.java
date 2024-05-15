package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mh.doctorslandingpage.model.Doctor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query(nativeQuery = true,value = "select * from doctors where username=:username")
    Optional<Doctor> findByUsername(String username);

}
