package fr.sevensenders.webcomic;

import java.util.List;

/**
 * Service that alloxs to fetch comics from multiple sources.
 */
public interface WebComicService {

    /**
     * Fetch comics from multiple source ordered by published date.
     *
     * @param numberOfComics the max number of comics to fetch
     *
     * @return the web comics
     */
    List<WebComic> getComicsOrderedByDate(int numberOfComics);
}
