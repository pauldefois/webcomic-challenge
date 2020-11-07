package fr.sevensenders.webcomic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public CommandLineRunner run(XkcdComicReaderService comicReaderService) throws Exception {
        // Test of the reader
        return args -> {
            final List<XkcdComic> comics = comicReaderService.readComics(10);
            comics.forEach(c -> {
                log.info(c.toString());
            });
        };
    }
}
