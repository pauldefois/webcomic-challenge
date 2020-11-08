package fr.sevensenders.webcomic;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WebComic implements Serializable {

    private static final long serialVersionUID = -2761451236509587922L;

    private String title;

    private String description;

    private String pictureUrl;

    private String url;

    private Date publishingDate;
}
