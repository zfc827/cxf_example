package example.jaxws.xmladapter;


import example.model.IntegerUserEntry;
import example.model.IntegerUserMap;
import example.model.User;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;

public class IntegerUserMapAdapter extends XmlAdapter<IntegerUserMap, Map<Integer, User>> {

    @Override
    public Map<Integer, User> unmarshal(IntegerUserMap v) throws Exception {
        List<IntegerUserEntry> entries = v.getEntries();
        Map<Integer, User> map = new HashMap<Integer, User>();

        for (Iterator<IntegerUserEntry> iterator = entries.iterator(); iterator.hasNext(); ) {
            IntegerUserEntry integerUserEntry = entries.iterator().next();
            map.put(integerUserEntry.getId(), integerUserEntry.getUser());
        }
        return map;
    }

    @Override
    public IntegerUserMap marshal(Map<Integer, User> v) throws Exception {
        IntegerUserMap integerUserMap = new IntegerUserMap();

        Iterator<Map.Entry<Integer, User>> iterator = v.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, User> entry = iterator.next();
            IntegerUserEntry integerUserEntry = new IntegerUserEntry();
            integerUserEntry.setId(entry.getKey());
            integerUserEntry.setUser(entry.getValue());
            integerUserMap.getEntries().add(integerUserEntry);
        }
        return integerUserMap;
    }
}
