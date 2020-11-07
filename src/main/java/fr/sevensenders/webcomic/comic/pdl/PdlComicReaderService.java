package fr.sevensenders.webcomic.comic.pdl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PdlComicReaderService {

    private static final Function<SyndEntry, PdlComic> MAPPER = entry -> {
        final PdlComic comic = new PdlComic();
        comic.setTitle(entry.getTitle());
        comic.setLink(entry.getLink());
        comic.setPubDate(entry.getPublishedDate());
        return comic;
    };

    private final RestTemplate restTemplate;

    private final PdlComicProperties pdlComicProperties;

    public List<PdlComic> readComics(int numberOfComics) throws IOException, FeedException {
        final URL feedSource = new URL(pdlComicProperties.getFeedUrl());
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(feedSource));
        return ((List<SyndEntry>) feed.getEntries()).stream().limit(numberOfComics).map(MAPPER).collect(Collectors.toList());
    }
}
