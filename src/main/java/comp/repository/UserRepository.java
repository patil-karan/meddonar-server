package comp.repository;



	import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.User;



	public interface UserRepository extends JpaRepository<User, Long> {

		public User findByEmail(String email);
	}


