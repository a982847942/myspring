package nuaa.edu.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PropertyValues
 * @Description
 * @Date 2022/10/13 19:25
 * @Created by brain
 */
public class PropertyValues {
    List<PropertyValue> propertyValueList = new ArrayList<>();

    //添加新属性
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String name) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)) {
                return propertyValue;
            }
        }
        return null;
    }

    //修改原有属性
    public boolean setPropertyValue(String name,Object value) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)) {
                while (propertyValueList.remove(propertyValue)){
                    propertyValueList.add(new PropertyValue(name,value));
                    break;
                }
                return true;
            }
        }
        return false;
    }
}
