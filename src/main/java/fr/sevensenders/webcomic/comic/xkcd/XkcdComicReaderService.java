package fr.sevensenders.webcomic.comic.xkcd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

/**
 * Represent a service to fetch comics from the Xkcd API.
 */
@RequiredArgsConstructor
public class XkcdComicReaderService {

    private final RestTemplate restTemplate;

    private final XkcdComicProperties xkcdComicProperties;

    /**
     * Find a comic by ID.
     *
     * @param id the id of the comic to find
     *
     * @return the comic if found
     */
    public Optional<XkcdComic> findComicById(long id) {
        final String url = xkcdComicProperties.getUrlComicById().replace(xkcdComicProperties.getIdTokenUrl(), String.valueOf(id));
        return Optional.ofNullable(restTemplate.getForObject(url, XkcdComic.class));
    }

    /**
     * Find the current (last) comic.
     *
     * @return the comic if found
     */
    public Optional<XkcdComic> findCurrentComic() {
        return Optional.ofNullable(restTemplate.getForObject(xkcdComicProperties.getUrlCurrentComic(), XkcdComic.class));
    }

    /**
     * Find the N last comics published.
     *
     * @param numberOfComics the umber of comics to fetch
     *
     * @return the comics found
     */
    public List<XkcdComic> readComics(int numberOfComics) {
        final List<XkcdComic> result = new ArrayList<>(numberOfComics);
        // Get the last comic in order to find its id
        final Optional<XkcdComic> currentComic = findCurrentComic();
        if (currentComic.isPresent()) {
            result.add(currentComic.get());
            final int currentComicId = currentComic.get().getNum();
            // Retrieve the last n-1 comics
            for (int i = currentComicId - 1; i >= currentComicId - numberOfComics - 1; i--) {
                findComicById(i).ifPresent(result::add);
            }
        }
        return result;
    }
}
