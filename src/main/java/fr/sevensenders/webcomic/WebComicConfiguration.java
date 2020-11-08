package fr.sevensenders.webcomic;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sevensenders.webcomic.comic.Comic;
import fr.sevensenders.webcomic.comic.ComicReaderService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Configuration
public class WebComicConfiguration {

    @Bean
    public PdlWebComicFactory pdlWebComicFactory() {
        return new PdlWebComicFactory();
    }

    @Bean
    public XkcdWebComicFactory xkcdWebComicFactory() {
        return new XkcdWebComicFactory();
    }

    @Bean
    public WebComicService webComicService(final List<WebComicFactory> webComicFactories, final List<ComicReaderService<? extends Comic>> comicReaderServices) {
        return new WebComicServiceImpl(webComicFactories.stream().collect(Collectors.toMap(WebComicFactory::getComicClass, Function.identity())), comicReaderServices);
    }
}
