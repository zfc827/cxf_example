package example.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zfc827@gmail.com
 */
@XmlRootElement(name = "ResponseType")
public class ResponseType<E> {

    private int errorCode;

    private String errorMsg;

    private E result;

    public ResponseType() {
    }

    public ResponseType(int errorCode, String errorMsg, E result) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    @XmlElement(name = "ErrorCode")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @XmlElement(name = "ErrorMessage")
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @XmlElement(name = "Result", type = User.class)
    public E getResult() {
        return result;
    }

    public void setResult(E result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseType{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
