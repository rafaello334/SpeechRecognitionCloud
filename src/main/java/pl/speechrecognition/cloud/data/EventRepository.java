package pl.speechrecognition.cloud.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.speechrecognition.cloud.model.Event;
import pl.speechrecognition.cloud.model.User;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{

	public Event findByEventID(Long eventID);
	public List<Event> findByUserOrderByDate(User user);
}
