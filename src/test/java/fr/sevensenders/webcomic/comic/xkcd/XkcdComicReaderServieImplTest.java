package fr.sevensenders.webcomic.comic.xkcd;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for {@link XkcdComicReaderServiceImpl}.
 */
@RunWith(JUnit4.class)
public class XkcdComicReaderServieImplTest {

    private RestTemplate restTemplate;

    private XkcdComicProperties xkcdComicProperties;

    private XkcdComicReaderServiceImpl xkcdComicReaderService;

    @Before
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        xkcdComicProperties = Mockito.mock(XkcdComicProperties.class);
        xkcdComicReaderService = new XkcdComicReaderServiceImpl(restTemplate, xkcdComicProperties);
    }

    @Test
    public void testFindComicById() {
        Mockito.when(xkcdComicProperties.getUrlComic()).thenReturn("http://xkcd.com");
        Mockito.when(xkcdComicProperties.getUriJsonAPI()).thenReturn("/jsonAPI");
        final XkcdComic comic = new XkcdComic();
        comic.setAlt("Description");
        comic.setDay("25");
        comic.setMonth("12");
        comic.setYear("2020");
        comic.setImg("http://xkcd.com/image/12.png");
        comic.setNum(14);
        comic.setTitle("Noel comic");
        Mockito.when(restTemplate.getForObject("http://xkcd.com/14/jsonAPI", XkcdComic.class)).thenReturn(comic);
        final Optional<XkcdComic> opResult = xkcdComicReaderService.findComicById(14);
        Assert.assertTrue(opResult.isPresent());
        final XkcdComic result = opResult.get();
        Assert.assertEquals("Description", result.getAlt());
        Assert.assertEquals("25", result.getDay());
        Assert.assertEquals("12", result.getMonth());
        Assert.assertEquals("2020", result.getYear());
        Assert.assertEquals("http://xkcd.com/image/12.png", result.getImg());
        Assert.assertEquals(14, result.getNum());
        Assert.assertEquals("Noel comic", result.getTitle());
    }

    @Test
    public void testFindCurrentComic() {
        Mockito.when(xkcdComicProperties.getUrlComic()).thenReturn("http://xkcd.com");
        Mockito.when(xkcdComicProperties.getUriJsonAPI()).thenReturn("/jsonAPI");
        final XkcdComic comic = new XkcdComic();
        comic.setAlt("Description");
        comic.setDay("25");
        comic.setMonth("12");
        comic.setYear("2020");
        comic.setImg("http://xkcd.com/image/12.png");
        comic.setNum(14);
        comic.setTitle("Noel comic");
        Mockito.when(restTemplate.getForObject("http://xkcd.com/jsonAPI", XkcdComic.class)).thenReturn(comic);
        final Optional<XkcdComic> opResult = xkcdComicReaderService.findCurrentComic();
        Assert.assertTrue(opResult.isPresent());
        final XkcdComic result = opResult.get();
        Assert.assertEquals("Description", result.getAlt());
        Assert.assertEquals("25", result.getDay());
        Assert.assertEquals("12", result.getMonth());
        Assert.assertEquals("2020", result.getYear());
        Assert.assertEquals("http://xkcd.com/image/12.png", result.getImg());
        Assert.assertEquals(14, result.getNum());
        Assert.assertEquals("Noel comic", result.getTitle());
    }

    @Test
    public void testReadComics() {
        final XkcdComic currentComic = new XkcdComic();
        currentComic.setNum(30);
        final XkcdComic comic29 = new XkcdComic();
        comic29.setNum(29);
        final XkcdComic comic28 = new XkcdComic();
        comic28.setNum(28);
        final XkcdComicReaderServiceImpl xkcdComicReaderServicePartiallyMocked = Mockito.spy(xkcdComicReaderService);
        Mockito.doReturn(Optional.of(currentComic)).when(xkcdComicReaderServicePartiallyMocked).findCurrentComic();
        Mockito.doReturn(Optional.of(comic29)).when(xkcdComicReaderServicePartiallyMocked).findComicById(29);
        Mockito.doReturn(Optional.of(comic28)).when(xkcdComicReaderServicePartiallyMocked).findComicById(28);
        final List<XkcdComic> result = xkcdComicReaderServicePartiallyMocked.readComics(3);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(30, result.get(0).getNum());
        Assert.assertEquals(29, result.get(1).getNum());
        Assert.assertEquals(28, result.get(2).getNum());
    }
}
