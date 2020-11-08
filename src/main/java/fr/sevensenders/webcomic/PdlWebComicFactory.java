package fr.sevensenders.webcomic;

import fr.sevensenders.webcomic.comic.pdl.PdlComic;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PdlWebComicFactory implements WebComicFactory<PdlComic> {

    @Override
    public WebComic getWebComic(final PdlComic comic) {
        final WebComic webComic = new WebComic();
        webComic.setTitle(comic.getTitle());
        webComic.setDescription(comic.getDescription());
        webComic.setPictureUrl(comic.getImage());
        webComic.setUrl(comic.getLink());
        webComic.setPublishingDate(comic.getDate());
        return webComic;
    }

    @Override
    public Class<PdlComic> getComicClass() {
        return PdlComic.class;
    }
}
