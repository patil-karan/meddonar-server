package comp.repository;

import comp.model.DonateMedicines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateMedicineRepository extends JpaRepository<DonateMedicines,Long> {

}
