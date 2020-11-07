package fr.sevensenders.webcomic;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import fr.sevensenders.webcomic.comic.pdl.PdlComic;
import fr.sevensenders.webcomic.comic.pdl.PdlComicReaderService;
import fr.sevensenders.webcomic.comic.xkcd.XkcdComic;
import fr.sevensenders.webcomic.comic.xkcd.XkcdComicReaderService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration
@Slf4j
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public CommandLineRunner run(final XkcdComicReaderService xkcdComicReaderService, final PdlComicReaderService pdlComicReaderService) throws Exception {
        // Test of the reader
        return args -> {
            final List<XkcdComic> comics = xkcdComicReaderService.readComics(10);
            comics.forEach(c -> {
                log.info(c.toString());
            });
            final List<PdlComic> pdlComics = pdlComicReaderService.readComics(10);
            pdlComics.forEach(c -> {
                log.info(c.toString());
            });
        };
    }
}
