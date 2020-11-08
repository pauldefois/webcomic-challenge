package fr.sevensenders.webcomic.comic.xkcd;

import java.util.Optional;

import fr.sevensenders.webcomic.comic.ComicReaderService;

/**
 * This service allows to fetch comics from the Xkcd API.
 */
public interface XkcdComicReaderService extends ComicReaderService<XkcdComic> {

    /**
     * Find a comic by ID.
     *
     * @param id the id of the comic to find
     *
     * @return the comic if found
     */
    Optional<XkcdComic> findComicById(long id);

    /**
     * Find the current (last) comic.
     *
     * @return the comic if found
     */
    Optional<XkcdComic> findCurrentComic();
}
