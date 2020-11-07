package fr.sevensenders.webcomic.comic.pdl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(PdlComicProperties.class)
public class PdlComicConfiguration {

    @Bean
    public PdlComicReaderService pdlComicReaderService(final RestTemplate restTemplate, final PdlComicProperties pdlComicProperties) {
        return new PdlComicReaderService(restTemplate, pdlComicProperties);
    }
}
