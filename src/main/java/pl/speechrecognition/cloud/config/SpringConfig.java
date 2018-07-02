package pl.speechrecognition.cloud.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

@Component
@ComponentScan("pl.speechrecognition.cloud")
public class SpringConfig {

	@Bean
	public Configuration configuration() {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		return configuration;
	}

	@Bean
	public StreamSpeechRecognizer streamSpeechRecognizer(Configuration configuration) throws IOException {
		return new StreamSpeechRecognizer(configuration);
	}
}
