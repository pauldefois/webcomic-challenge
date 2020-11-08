package fr.sevensenders.webcomic.comic.pdl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of the beans related to the PoorlyDrawnLines comics source.
 */
@Configuration
@EnableConfigurationProperties(PdlComicProperties.class)
public class PdlComicConfiguration {

    /**
     * Initialisation of the mapper of RSS feed entries.
     *
     * @param pdlComicProperties the properties related to the PoorlyDrawnLines source
     *
     * @return the mapper
     */
    @Bean
    public PdlRssFeedEntryMapper pdlRssFeedEntryMapper(final PdlComicProperties pdlComicProperties) {
        return new PdlRssFeedEntryMapper(pdlComicProperties);
    }

    /**
     * Initialisation of the service that fetch the comics from the RSS source.
     *
     * @param pdlComicProperties    the properties related to the PoorlyDrawnLines source
     * @param pdlRssFeedEntryMapper the mapper of RSS feed entries
     *
     * @return the service
     */
    @Bean
    public PdlComicReaderService pdlComicReaderService(final PdlComicProperties pdlComicProperties, final PdlRssFeedEntryMapper pdlRssFeedEntryMapper) {
        return new PdlComicReaderServiceImpl(pdlComicProperties, pdlRssFeedEntryMapper);
    }
}
