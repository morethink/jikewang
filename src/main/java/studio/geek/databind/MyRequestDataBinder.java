package studio.geek.databind;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;

/**
 * @author 李文浩
 * @version 2017/2/23.
 */
public class MyRequestDataBinder extends ExtendedServletRequestDataBinder {
    public MyRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);
        for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
            if (propertyValue.getValue().equals(""))
                propertyValue.setConvertedValue(null);
        }
    }
}
