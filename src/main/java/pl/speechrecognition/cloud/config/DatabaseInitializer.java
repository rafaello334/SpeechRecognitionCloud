package pl.speechrecognition.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pl.speechrecognition.cloud.data.UserRepository;
import pl.speechrecognition.cloud.model.User;

@Component
public class DatabaseInitializer {

	@Autowired
	private UserRepository userRepository;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {

		userRepository.save(new User("admin", "admin"));
	}
}
