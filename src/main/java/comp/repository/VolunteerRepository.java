package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
