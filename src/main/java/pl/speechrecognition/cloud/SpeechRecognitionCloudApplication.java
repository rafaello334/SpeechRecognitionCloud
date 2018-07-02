package pl.speechrecognition.cloud;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.speechrecognition.cloud.config.SpeechRecognizer;

@SpringBootApplication
public class SpeechRecognitionCloudApplication {
	public static Log logger = LogFactory.getLog(SpeechRecognitionCloudApplication.class);

	@Autowired
	SpeechRecognizer speechRecognizer;

	public static void main(String[] args) {
		SpringApplication.run(SpeechRecognitionCloudApplication.class, args);
	}
}
