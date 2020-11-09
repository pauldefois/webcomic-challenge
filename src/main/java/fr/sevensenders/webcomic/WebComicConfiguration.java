package fr.sevensenders.webcomic;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sevensenders.webcomic.comic.Comic;
import fr.sevensenders.webcomic.comic.ComicReaderService;
import fr.sevensenders.webcomic.comic.xkcd.XkcdComicProperties;
import lombok.NoArgsConstructor;

/**
 * Configuration of the beans related to the web comic API.
 */
@NoArgsConstructor
@Configuration
public class WebComicConfiguration {

    /**
     * Initialisation of the factory that builds web comics fetched from the PoorlyDrawnLines source.
     *
     * @return the factory
     */
    @Bean
    public PdlWebComicFactory pdlWebComicFactory() {
        return new PdlWebComicFactory();
    }

    /**
     * Initialisation of the factory that builds web comics fetched from the Xkcd source.
     *
     * @param xkcdComicProperties the properties related to the xkcd comic source
     *
     * @return the factory
     */
    @Bean
    public XkcdWebComicFactory xkcdWebComicFactory(final XkcdComicProperties xkcdComicProperties) {
        return new XkcdWebComicFactory(xkcdComicProperties);
    }

    /**
     * Initialisation of the service that returns the web comics fetched from different sources.
     *
     * @param webComicFactories   the factories sorted by comic source class
     * @param comicReaderServices the services to fetch the comics on specific sources
     *
     * @return the service
     */
    @Bean
    public WebComicService webComicService(final List<WebComicFactory> webComicFactories, final List<ComicReaderService<? extends Comic>> comicReaderServices) {
        return new WebComicServiceImpl(webComicFactories.stream().collect(Collectors.toMap(WebComicFactory::getComicClass, Function.identity())), comicReaderServices);
    }

    /**
     * Initialisation of the controller that provide the web comics.
     *
     * @param webComicService the service fetching the comics from multiple sources
     *
     * @return the controller
     */
    @Bean
    public WebComicController webComicController(final WebComicService webComicService) {
        return new WebComicController(webComicService);
    }
}
