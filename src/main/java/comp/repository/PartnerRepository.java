package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
