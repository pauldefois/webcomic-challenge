package fr.sevensenders.webcomic;

import fr.sevensenders.webcomic.comic.Comic;

public interface WebComicFactory<T extends Comic> {

    WebComic getWebComic(final T comic);

    Class<T> getComicClass();
}
