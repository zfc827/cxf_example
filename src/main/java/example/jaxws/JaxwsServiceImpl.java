package example.jaxws;

import example.model.IntegerUserEntry;
import example.model.IntegerUserMap;
import example.model.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JaxwsServiceImpl implements JaxwsService {

    private final Logger logger = LoggerFactory.getLogger(JaxwsServiceImpl.class);

    private Map<Integer, User> users = new HashMap<Integer, User>();

    private IntegerUserMap integerUserMap = new IntegerUserMap();

    public User sayHi(String text) {
        logger.info("sayHi called, text = {}", text);
        return new User(text);
    }

    public User sayHiToUser(User user) {
        logger.info("sayHiToUser called, user = {}", user.toString());
        users.put(users.size(), user);
        return new User("result of " + user.getName());
    }

    public IntegerUserMap getUserMap(IntegerUserEntry integerUserEntry) {
        logger.info("getUserMap called, entry.id = {}, entry.user = {}", String.valueOf(integerUserEntry.getId()), integerUserEntry.getUser().toString());
        integerUserMap.getEntries().add(integerUserEntry);
        return integerUserMap;
    }

    public Map<Integer, User> getUsers() {
        logger.info("getUsers called, users.size = {}", users.size());
        return users;
    }
}
