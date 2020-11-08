package fr.sevensenders.webcomic.comic.xkcd;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.sevensenders.webcomic.comic.Comic;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represent a comic fetched from Xkcd source.
 */
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@EqualsAndHashCode
public class XkcdComic implements Serializable, Comic {

    private static final long serialVersionUID = 7048126316068014259L;

    private String month;

    private int num;

    private String link;

    private String year;

    private String news;

    @JsonAlias("safe_title")
    private String safeTitle;

    private String transcript;

    private String alt;

    private String img;

    private String string;

    private String title;

    private String day;
}
