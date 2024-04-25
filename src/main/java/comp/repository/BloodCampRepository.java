package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.BloodCamp;

public interface BloodCampRepository extends JpaRepository<BloodCamp, Long>{

}
