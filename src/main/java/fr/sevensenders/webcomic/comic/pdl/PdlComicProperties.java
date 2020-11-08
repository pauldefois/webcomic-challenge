package fr.sevensenders.webcomic.comic.pdl;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the properties related to the PoorlyDrawnLines API.
 */
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "comic.pdl")
public class PdlComicProperties {

    /**
     * Defines the URL of the API PoorlyDrawnLines to get RSS feed.
     */
    private String feedUrl = "https://feeds.feedburner.com/PoorlyDrawnLines?fmt=xml";

    /**
     * Defines the regular expression used to find the image URL in the content feed.
     */
    private String imageUrlRegex = "<div class=\"wp-block-image\">((.|\\s)+?)<img ((.|\\s)+?)src=\"(.+?)\" alt=\"((.|\\s)+?)\"((.|\\s)+?)</div>";

    /**
     * Defines which groups is located the URL of the image in the previous regular expression.
     */
    private int imageUrlRegexGroup = 5;
}
