package pl.speechrecognition.cloud.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.speechrecognition.cloud.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public boolean existsByUsername(String username);
	public User findByUsername(String username);
}
