package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.Address;


	public interface AddressRepository extends JpaRepository<Address, Long>{

	}

