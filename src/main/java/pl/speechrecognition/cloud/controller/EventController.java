package pl.speechrecognition.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pl.speechrecognition.cloud.data.EventRepository;
import pl.speechrecognition.cloud.data.UserRepository;
import pl.speechrecognition.cloud.model.Event;
import pl.speechrecognition.cloud.model.User;

@RestController
public class EventController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(value = "/events/", method = RequestMethod.POST)
	public ResponseEntity<Void> addEvent(@RequestBody Event event, UriComponentsBuilder ucBuilder) {
		event.setUser(userRepository.findByUsername(event.getUser().getUsername()));
		eventRepository.save(event);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/event/{id}").buildAndExpand(event.getEventID()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/events/{username}", method = RequestMethod.GET)
	   public ResponseEntity<List<Event>> listAllEventsForUser(@PathVariable("username") String username) {
		User user = userRepository.findByUsername(username);
        List<Event> eventsList = eventRepository.findByUserOrderByDate(user);

        if(eventsList.isEmpty()){
            return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(eventsList, HttpStatus.OK);
    }
}
