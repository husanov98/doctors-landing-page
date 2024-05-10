package uz.mh.doctorslandingpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mh.doctorslandingpage.model.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(nativeQuery = true,value = "select * from addresses where house_number=:houseNumber")
    Optional<Address> findBuHouseNumber(Integer houseNumber);
}
