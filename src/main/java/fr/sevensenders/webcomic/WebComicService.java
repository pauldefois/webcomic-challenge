package fr.sevensenders.webcomic;

import java.util.List;

public interface WebComicService {

    List<WebComic> getComicsOrderedByDate(int numberOfComics);
}
