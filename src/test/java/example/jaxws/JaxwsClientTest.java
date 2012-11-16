package example.jaxws;

import example.model.IntegerUserEntry;
import example.model.IntegerUserMap;
import example.model.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zfc827@gmail.com
 */
public class JaxwsClientTest {

    private Logger logger = LoggerFactory.getLogger(JaxwsClientTest.class);

    private User user;

    private IntegerUserEntry integerUserEntry;

    private IntegerUserMap integerUserMap;

    private QName serviceName;

    private QName portName;

    private Service service;

    private String endpointAddress = "http://localhost:8080/cxf_example/services/jaxws_service";

    private JaxwsService servicePort;

    @Before
    public void init() {
        buildEntity();

        buildClientService();
    }

    private void buildClientService() {
        serviceName = new QName("http://jaxws.example/", "JaxwsService");
        portName = new QName("http://jaxws.example/", "JaxwsServicePort");

        service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        servicePort = service.getPort(JaxwsService.class);
    }

    private void buildEntity() {
        user = new User("testUser");
        integerUserEntry = new IntegerUserEntry();
        integerUserEntry.setId(1);
        integerUserEntry.setUser(user);

        integerUserMap = new IntegerUserMap();
        integerUserMap.getEntries().add(integerUserEntry);
    }


    @Test
    public void testSayHi() throws Exception {
        User test = servicePort.sayHi("test");
        logger.info(test.getName());
    }

    @Test
    public void testSayHiToUser() throws Exception {
        logger.info(servicePort.sayHiToUser(user).getName());
    }

    @Test
    public void testGetUserMap() throws Exception {
        logger.info(servicePort.getUserMap(integerUserEntry).toString());
    }

    @Test
    public void testGetUsers() throws Exception {

        dynamicClient();
    }

    private void dynamicClient() throws Exception {
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://localhost:8080/cxf/services/jaxws_helloworld?wsdl");
        Object[] getUserses = client.invoke("getUsers");
        for(Object obj : getUserses) {
            Method method = obj.getClass().getMethod("getEntry");
            List result = (List)method.invoke(obj);
            for(Object o : result) {
                logger.info(ToStringBuilder.reflectionToString(o));
            }
        }
    }
}
