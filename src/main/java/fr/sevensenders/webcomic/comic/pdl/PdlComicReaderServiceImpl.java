package fr.sevensenders.webcomic.comic.pdl;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This service implementation allows to fetch comics from the PoorlyDrawnLines API.
 */
@RequiredArgsConstructor
@Slf4j
public class PdlComicReaderServiceImpl implements PdlComicReaderService {

    private final PdlComicProperties pdlComicProperties;

    private final PdlRssFeedEntryMapper mapper;

    @Override
    public List<PdlComic> readComics(int numberOfComics) {
        try {
            final URL feedSource = new URL(pdlComicProperties.getFeedUrl());
            final SyndFeedInput input = new SyndFeedInput();
            final SyndFeed feed = input.build(new XmlReader(feedSource));
            return ((List<SyndEntry>) feed.getEntries()).stream().limit(numberOfComics).map(mapper).collect(Collectors.toList());
        } catch (final IOException | FeedException e) {
            log.warn("Warning, unable to fetch the source {}", pdlComicProperties.getFeedUrl(), e);
            return Collections.emptyList();
        }
    }
}
