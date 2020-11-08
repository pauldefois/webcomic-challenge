package fr.sevensenders.webcomic;

import java.text.ParseException;

import org.apache.commons.lang3.time.FastDateFormat;

import fr.sevensenders.webcomic.comic.xkcd.XkcdComic;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class XkcdWebComicFactory implements WebComicFactory<XkcdComic> {

    @Override
    public WebComic getWebComic(final XkcdComic comic) {
        final WebComic webComic = new WebComic();
        webComic.setTitle(comic.getTitle());
        webComic.setDescription(comic.getTranscript());
        webComic.setPictureUrl(comic.getImg());
        webComic.setUrl(comic.getLink());
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
