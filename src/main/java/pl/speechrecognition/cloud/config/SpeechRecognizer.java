package pl.speechrecognition.cloud.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

@Component
public class SpeechRecognizer {

	@Autowired
	Configuration configuration;

	@Autowired
	StreamSpeechRecognizer streamSpeechRecognizer;

	public String recognizeCommand(String fileName) {
		String recognizedCommand = new String();
		try {
			streamSpeechRecognizer.startRecognition(new FileInputStream("C:\\speech\\" + fileName));
			SpeechResult result = streamSpeechRecognizer.getResult();
			streamSpeechRecognizer.stopRecognition();
			recognizedCommand = result.getHypothesis();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return recognizedCommand;
	}

	public String checkMatchingCommand(String command) {
		String matchedCommand = "";
		if (command != null) {
			if (command.contains("next")) {
				if (command.contains("year")) {
					matchedCommand = "nextYear";
				} else if (command.contains("moon")) {
					matchedCommand = "nextMonth";
				}
			} else if (command.contains("previous")) {
				if (command.contains("year")) {
					matchedCommand = "previousYear";
				} else if (command.contains("moon")) {
					matchedCommand = "previousMonth";
				}
			}
		}
		if (matchedCommand.equals(""))
			matchedCommand = "Command not found.";
		return matchedCommand;
	}

}
