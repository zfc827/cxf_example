package example.jaxws;

import example.jaxws.xmladapter.IntegerUserMapAdapter;
import example.model.IntegerUserEntry;
import example.model.IntegerUserMap;
import example.model.ResponseType;
import example.model.User;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

@WebService(portName = "JaxwsServicePort", serviceName = "JaxwsService")
public interface JaxwsService {

    User sayHi(String text);

    User sayHiToUser(User user);

    IntegerUserMap getUserMap(IntegerUserEntry integerUserEntry);

    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    Map<Integer, User> getUsers();

    ResponseType<User> getResponseUser(String userName);
}
