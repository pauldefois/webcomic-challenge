package fr.sevensenders.webcomic.comic.pdl;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * Class that maps a {@link SyndEntry} to a {@link PdlComic}.
 */
public class PdlRssFeedEntryMapper implements Function<SyndEntry, PdlComic> {

    private final PdlComicProperties pdlComicProperties;

    private final Pattern imageUrlPattern;

    /**
     * Default constructor. Compiles the pattern used to find the URL image in the RSS entry feed.
     *
     * @param pdlComicProperties the propeties related to the PoorlyDrawnLines source of comics
     */
    public PdlRssFeedEntryMapper(final PdlComicProperties pdlComicProperties) {
        this.pdlComicProperties = pdlComicProperties;
        this.imageUrlPattern = Pattern.compile(pdlComicProperties.getImageUrlRegex());
    }

    @Override
    public PdlComic apply(final SyndEntry entry) {
        final PdlComic comic = new PdlComic();
        comic.setTitle(entry.getTitle());
        comic.setLink(entry.getLink());
        comic.setDate(entry.getPublishedDate());
        final Optional<SyndContent> content = entry.getContents().stream().findFirst();
        comic.setImage(content.map(this::getImageUrlFromFeedContent).orElse(""));
        return comic;
    }

    /**
     * Get the image URL in the content of an entry RSS feed.
     *
     * @param content the content to parse
     *
     * @return the URL of the image
     */
    public String getImageUrlFromFeedContent(final SyndContent content) {
        final Matcher matcher = imageUrlPattern.matcher(content.getValue());
        if (matcher.find()) {
            return matcher.group(pdlComicProperties.getImageUrlRegexGroup());
        }
        return "";
    }
}
