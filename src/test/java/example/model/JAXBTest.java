package example.model;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author zfc827@gmail.com
 */
public class JAXBTest {

    private User user;

    private IntegerUserMap integerUserMap;

    private ResponseType<User> responseType;

    private JAXBContext jaxbContext;

    private Marshaller marshaller;

    @Before
    public void init() throws JAXBException {
        user = new User("testUser");

        integerUserMap = new IntegerUserMap();
        for (int i = 0; i < 3; i++) {
            IntegerUserEntry integerUserEntry = new IntegerUserEntry();
            integerUserEntry.setId(i);
            integerUserEntry.setUser(new User("testUser" + i));
            integerUserMap.getEntries().add(integerUserEntry);
        }

        responseType = new ResponseType<User>(0, "this is error message...", user);

        jaxbContext = JAXBContext.newInstance(User.class, IntegerUserMap.class, ResponseType.class);
        marshaller = jaxbContext.createMarshaller();
    }

    @Test
    public void testBuildUserXML() throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user, System.out);
    }

    @Test
    public void testBuildUserMapXML() throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(integerUserMap, System.out);
    }

    @Test
    public void testBuildResponseTypeXml() throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(responseType, System.out);
    }
}
