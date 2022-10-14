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
    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String name){
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)){
                return propertyValue;
            }
        }
        return null;
    }
}
