package fr.sevensenders.webcomic;

import fr.sevensenders.webcomic.comic.Comic;

/**
 * Factory that allows to create a {@link WebComic} from a {@link Comic}.
 *
 * @param <T> the type of {@link Comic} from which create the {@link WebComic}
 */
public interface WebComicFactory<T extends Comic> {

    /**
     * Build the {@link WebComic} from the {@link Comic}.
     *
     * @param comic the comic
     *
     * @return the web comic
     */
    WebComic getWebComic(final T comic);

    /**
     * Get comic type of this factory.
     *
     * @return type of comic
     */
    Class<T> getComicClass();
}
