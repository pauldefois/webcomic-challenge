package fr.sevensenders.webcomic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.sevensenders.webcomic.comic.Comic;
import fr.sevensenders.webcomic.comic.ComicReaderService;
import lombok.RequiredArgsConstructor;

/**
 * Service implementation to fetch comics from multiple sources.
 */
@RequiredArgsConstructor
public class WebComicServiceImpl implements WebComicService {

    private final Map<Class<? extends Comic>, WebComicFactory> webComicFactories;

    private final List<ComicReaderService<? extends Comic>> comicReaderServices;

    @Override
    public List<WebComic> getComicsOrderedByDate(final int numberOfComics) {
        final List<Comic> comics = new ArrayList<>();
        // Gets all the comics of each source
        for (ComicReaderService<? extends Comic> comicReaderService : comicReaderServices) {
            comics.addAll(comicReaderService.readComics(numberOfComics / 2));
        }
        final List<WebComic> result = new ArrayList<>();
        // Builds all the web comics
        for (final Comic comic : comics) {
            result.add(webComicFactories.get(comic.getClass()).getWebComic(comic));
        }
        // Returns the list of web comics limited regarding to numberOfComics and orderd by published date
        // @formatter:off
        return result.stream()
                   .sorted(Comparator.comparing(WebComic::getPublishingDate).reversed())
                   .limit(numberOfComics)
                   .collect(Collectors.toList());
        // @formatter:on
    }
}
