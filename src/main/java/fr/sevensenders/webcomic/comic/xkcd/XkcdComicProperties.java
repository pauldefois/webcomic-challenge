package fr.sevensenders.webcomic.comic.xkcd;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the properties related to the xkcd API.
 */
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "comic.xkcd")
public class XkcdComicProperties {

    /**
     * Defines the URL of the API Xkcd to get the current comic.
     */
    private String urlCurrentComic = "https://xkcd.com/info.0.json";

    /**
     * Defines the URL of the API Xkcd to get a specific comic by ID.
     */
    private String urlComicById = "https://xkcd.com/{id}/info.0.json";

    /**
     * Defines the token to replace in the URL {@link XkcdComicProperties#urlComicById} to fetch a specific comic.
     */
    private String idTokenUrl = "{id}";
}
