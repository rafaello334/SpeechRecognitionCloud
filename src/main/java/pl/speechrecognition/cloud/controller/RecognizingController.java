package pl.speechrecognition.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.speechrecognition.cloud.SpeechRecognitionCloudApplication;
import pl.speechrecognition.cloud.config.SpeechRecognizer;

@RestController
public class RecognizingController {
	@Autowired
	SpeechRecognizer speechRecognizer;

	@RequestMapping(value = "/recognize/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getRecognizedCommand(@PathVariable("fileName") String fileName) {
		String textFromVoice = speechRecognizer.recognizeCommand(fileName);
		SpeechRecognitionCloudApplication.logger.info("Recognized text: " + textFromVoice);
		String matchedCommand = speechRecognizer.checkMatchingCommand(textFromVoice);
		SpeechRecognitionCloudApplication.logger.info("Recognized command: " + matchedCommand);
        return new ResponseEntity<String>(matchedCommand, HttpStatus.OK);
	}
}
