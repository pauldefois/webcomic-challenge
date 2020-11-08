package fr.sevensenders.webcomic;

import java.text.ParseException;

import org.apache.commons.lang3.time.FastDateFormat;

import fr.sevensenders.webcomic.comic.xkcd.XkcdComic;
import fr.sevensenders.webcomic.comic.xkcd.XkcdComicProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Factory implementation that allows to create a {@link WebComic} from a {@link XkcdComic}.
 */
@Slf4j
@RequiredArgsConstructor
public class XkcdWebComicFactory implements WebComicFactory<XkcdComic> {

    private final XkcdComicProperties xkcdComicProperties;

    @Override
    public WebComic getWebComic(final XkcdComic comic) {
        final WebComic webComic = new WebComic();
        webComic.setTitle(comic.getTitle());
        webComic.setDescription(comic.getAlt());
        webComic.setPictureUrl(comic.getImg());
        webComic.setUrl(xkcdComicProperties.getUrlComic() + "/" + comic.getNum());
        final FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/YYYY");
        final String rawDate = String.format("%s/%s/%s", comic.getDay(), comic.getMonth(), comic.getYear());
        try {
            webComic.setPublishingDate(dateFormat.parse(rawDate));
        } catch (final ParseException e) {
            log.warn("Date not found", e);
        }
        return webComic;
    }

    @Override
    public Class<XkcdComic> getComicClass() {
        return XkcdComic.class;
    }
}
