package pl.speechrecognition.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pl.speechrecognition.cloud.data.UserRepository;
import pl.speechrecognition.cloud.model.User;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        userRepository.save(user);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserID()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
