package fr.sevensenders.webcomic.comic;

import java.util.List;

/**
 * This service is used to fetch comics from a specific source.
 *
 * @param <T> the type of comic to fetch
 */
public interface ComicReaderService<T extends Comic> {

    /**
     * Finds the N last comics published.
     *
     * @param numberOfComics the number of comics to fetch
     *
     * @return the comics found
     */
    List<T> readComics(int numberOfComics);
}
