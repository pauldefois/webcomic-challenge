package fr.sevensenders.webcomic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.sevensenders.webcomic.comic.Comic;
import fr.sevensenders.webcomic.comic.ComicReaderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebComicServiceImpl implements WebComicService {

    private final Map<Class<? extends Comic>, WebComicFactory> webComicFactories;

    private final List<ComicReaderService<? extends Comic>> comicReaderServices;

    @Override
    public List<WebComic> getComicsOrderedByDate(final int numberOfComics) {
        final List<Comic> comics = new ArrayList<>();
        for (ComicReaderService<? extends Comic> comicReaderService : comicReaderServices) {
            comics.addAll(comicReaderService.readComics(numberOfComics));
        }
        final List<WebComic> result = new ArrayList<>();
        for (final Comic comic : comics) {
            result.add(webComicFactories.get(comic.getClass()).getWebComic(comic));
        }
        return result.stream().sorted(Comparator.comparing(WebComic::getPublishingDate)).limit(numberOfComics).collect(Collectors.toList());
    }
}
