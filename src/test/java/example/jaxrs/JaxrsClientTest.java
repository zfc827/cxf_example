package example.jaxrs;

import example.model.IntegerUserEntry;
import example.model.IntegerUserMap;
import example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zfc827@gmail.com
 */
@ContextConfiguration("testContext.xml")
public class JaxrsClientTest extends AbstractJUnit4SpringContextTests {

    private final Logger logger = LoggerFactory.getLogger(JaxrsClientTest.class);

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    private Map<String,String> urlVariables;

    private String for_json_url = "http://localhost:8080/cxf_example/services/jaxrs_service/search/json/{userName}";

    private String for_xml_url = "http://localhost:8080/cxf_example/services/jaxrs_service/search/xml/{userName}";

    @Before
    public void init() {
        urlVariables = new HashMap<String,String>();
        urlVariables.put("userName", "testUserName");
    }

    @Test
    public void testGetUsersMap() {
        IntegerUserMap integerUserMap = restTemplate.getForObject(for_xml_url, IntegerUserMap.class, urlVariables);
        logger.info("integerUserMap = {}", integerUserMap.toString());
    }

    @Test
    public void testGetUser() {
        Map map = restTemplate.getForObject(for_json_url, Map.class, urlVariables);
        for(Iterator<Map.Entry> iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = iterator.next();
            logger.info("entry = {}", entry.toString());
        }
    }
}
