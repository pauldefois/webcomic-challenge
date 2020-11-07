package fr.sevensenders.webcomic.comic.xkcd;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration of the bean related to the Xkcd source of comics.
 */
@Configuration
@EnableConfigurationProperties(XkcdComicProperties.class)
public class XkcdComicConfiguration {

    /**
     * Initialize the rest template to query the comics API.
     *
     * @param restTemplateBuilder the builder of {@link RestTemplate}
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    /**
     * Initialize the reader service of Xkcd comics.
     *
     * @param restTemplate        the rest template
     * @param xkcdComicProperties the properties related to the Xkcd source
     *
     * @return the service
     */
    @Bean
    public XkcdComicReaderService xkcdComicReaderService(final RestTemplate restTemplate, final XkcdComicProperties xkcdComicProperties) {
        return new XkcdComicReaderService(restTemplate, xkcdComicProperties);
    }
}
