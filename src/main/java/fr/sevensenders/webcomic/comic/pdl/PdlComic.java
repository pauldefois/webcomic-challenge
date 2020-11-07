package fr.sevensenders.webcomic.comic.pdl;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PdlComic implements Serializable {

    private static final long serialVersionUID = 3811865058992215978L;

    private String title;

    private String link;

    private Date pubDate;

    private String description;

    private String encoded;
}
