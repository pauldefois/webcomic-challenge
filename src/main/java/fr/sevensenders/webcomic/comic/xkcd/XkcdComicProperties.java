package fr.sevensenders.webcomic.comic.xkcd;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the properties related to the Xkcd API.
 */
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "comic.xkcd")
public class XkcdComicProperties {

    /**
     * Defines the base URL to browse Xkcd comics.
     */
    private String urlComic = "https://xkcd.com";

    /**
     * Defines the URI to consume to get the comic in json format.
     */
    private String uriJsonAPI = "/info.0.json";
}
