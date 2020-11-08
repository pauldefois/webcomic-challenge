package fr.sevensenders.webcomic.comic.pdl;

import java.io.Serializable;
import java.util.Date;

import fr.sevensenders.webcomic.comic.Comic;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represent a comic fetched from PoorlyDrawnLines source.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PdlComic implements Serializable, Comic {

    private static final long serialVersionUID = 3811865058992215978L;

    private String title;

    private String link;

    private Date date;

    private String description;

    private String image;
}
