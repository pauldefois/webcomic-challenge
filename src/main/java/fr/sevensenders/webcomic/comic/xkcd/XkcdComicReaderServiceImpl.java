package fr.sevensenders.webcomic.comic.xkcd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

/**
 * This service implementation allows to fetch comics from the Xkcd API.
 */
@RequiredArgsConstructor
public class XkcdComicReaderServiceImpl implements XkcdComicReaderService {

    private final RestTemplate restTemplate;

    private final XkcdComicProperties xkcdComicProperties;

    @Override
    public Optional<XkcdComic> findComicById(long id) {
        final String url = xkcdComicProperties.getUrlComic() + "/" + id + xkcdComicProperties.getUriJsonAPI();
        return Optional.ofNullable(restTemplate.getForObject(url, XkcdComic.class));
    }

    @Override
    public Optional<XkcdComic> findCurrentComic() {
        final String url = xkcdComicProperties.getUrlComic() + xkcdComicProperties.getUriJsonAPI();
        return Optional.ofNullable(restTemplate.getForObject(url, XkcdComic.class));
    }

    @Override
    public List<XkcdComic> readComics(int numberOfComics) {
        final List<XkcdComic> result = new ArrayList<>(numberOfComics);
        // Get the last comic in order to find its id
        final Optional<XkcdComic> currentComic = findCurrentComic();
        if (currentComic.isPresent()) {
            result.add(currentComic.get());
            final int currentComicId = currentComic.get().getNum();
            // Retrieve the last n-1 comics
            for (int i = currentComicId - 1; i > currentComicId - numberOfComics; i--) {
                findComicById(i).ifPresent(result::add);
            }
        }
        return result;
    }
}
