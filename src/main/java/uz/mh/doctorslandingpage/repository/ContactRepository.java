package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mh.doctorslandingpage.model.Contact;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    @Query(nativeQuery = true,value = "select * from contacts where phone_number=:phoneNumber")
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
