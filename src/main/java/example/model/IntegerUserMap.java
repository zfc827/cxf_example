package example.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "IntegerUserMap")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class IntegerUserMap {

    @XmlElement(name = "entry", nillable = false)
    List<IntegerUserEntry> entries = new ArrayList<IntegerUserEntry>();

    public List<IntegerUserEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<IntegerUserEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
