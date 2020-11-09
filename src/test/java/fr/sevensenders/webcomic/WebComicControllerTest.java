package fr.sevensenders.webcomic;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Integration test for {@link WebComicController}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class WebComicControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetComics() throws Exception {
        final MvcResult result = mvc.perform(get("/")).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("application/json", result.getResponse().getContentType());
        final List<WebComic> comicsResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<WebComic>>() {});
        Assert.assertEquals(20, comicsResult.size());
        long lastPublishingDate = Long.MAX_VALUE;
        for (final WebComic webComic : comicsResult) {
            Assert.assertTrue(webComic.getPublishingDate().getTime() <= lastPublishingDate);
            lastPublishingDate = webComic.getPublishingDate().getTime();
        }
    }
}
