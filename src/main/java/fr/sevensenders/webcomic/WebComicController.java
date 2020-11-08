package fr.sevensenders.webcomic;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Controller that provides the comics fetched on different sources.
 */
@RestController
@RequiredArgsConstructor
public class WebComicController {

    private static final int NUMBER_OF_COMICS = 20;

    private final WebComicService webComicService;

    /**
     * Get the 10 last web comics ordered by published date.
     *
     * @return the web comics
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WebComic> getComics() {
        return webComicService.getComicsOrderedByDate(NUMBER_OF_COMICS);
    }
}
