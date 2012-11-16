package example.jaxrs;

import example.jaxws.xmladapter.IntegerUserMapAdapter;
import example.model.IntegerUserEntry;
import example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;

public class JaxrsService {

    private final Logger logger = LoggerFactory.getLogger(JaxrsService.class);

    private Map<Integer, User> users = new HashMap<Integer, User>();

    @GET
    @Path(value = "/search/xml/{userName}")
    @Produces(MediaType.APPLICATION_XML)
    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    public Map<Integer, User> getUsersMap(@PathParam("userName") String userName) {
        logger.info("getUsersMap called, name = {}", userName);
        users.put(users.size(), new User(userName));
        return users;
    }

    @GET
    @Path(value = "/search/json/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public IntegerUserEntry getUser(@PathParam("userName") String userName) {
        logger.info("getUser called, name = {}", userName);
        IntegerUserEntry integerUserEntry = new IntegerUserEntry();
        integerUserEntry.setUser(new User(userName));
        return integerUserEntry;
    }
}
