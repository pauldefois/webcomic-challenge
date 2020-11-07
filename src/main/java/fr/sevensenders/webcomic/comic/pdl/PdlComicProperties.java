package fr.sevensenders.webcomic.comic.pdl;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "comic.pdl")
public class PdlComicProperties {

    private String feedUrl = "https://feeds.feedburner.com/PoorlyDrawnLines?fmt=xml";
}
