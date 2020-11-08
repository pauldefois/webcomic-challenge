package fr.sevensenders.webcomic;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebComicController {

    private final WebComicService webComicService;

    @GetMapping(value = "/comics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WebComic> getComics() {
        return webComicService.getComicsOrderedByDate(10);
    }
}
