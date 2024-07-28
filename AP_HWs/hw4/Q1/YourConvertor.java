import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;

public class YourConvertor{

    private static String keyRegex="\"(?<key>\\.+)\"";
    private static String objectValueRegex="(?<objectValue>\\{\\.+\\})";
    private static String primitiveValueRegex="(?<primitiveValue>[^\\,]+)";
    private static String arrayValueRegex="(?<arrayValue>\\[\\.+\\])";

    public Object deserialize(String input, String className) {
        try {
            Class targetClass=Class.forName(className);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String serialize(Object input) {
        //TODO Implement this method
        try {
            return createJsonForObject(input);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createJsonForObject(Object object) throws IllegalArgumentException, IllegalAccessException{
       
        Field[] fields = object.getClass().getDeclaredFields();
        String output="{";
        int i=0;
        for (Field field : fields) {
            output=output.concat(encodeSingleField(field, object.getClass(), object));
            if(i!=fields.length-1)
                output=output.concat(",");
            i++;
        }
        output=output.concat("}");
        return output;
    }

    private String encodeSingleField(Field field,Class klass,Object object) throws IllegalArgumentException, IllegalAccessException{

        StringBuilder stringBuilder = new StringBuilder();
        field.setAccessible(true);

        stringBuilder.append(formatString(field.getName()) + ":");

        if (field.getType().isPrimitive()) 
            stringBuilder.append(field.get(object).toString());
        
        else if (field.getType().equals(String.class)) 
            stringBuilder.append(formatString(field.get(object).toString()));
        
        else if (field.getType().isArray()) 
            stringBuilder.append(encodeLists(field.get(object)));
        
        else 
            stringBuilder.append(createJsonForObject(field.get(object)));
        
            return stringBuilder.toString();
        }

    private String encodeLists(Object arrayObject) throws IllegalArgumentException, IllegalAccessException{
        Class<?> arrType = arrayObject.getClass().getComponentType();
        int size = Array.getLength(arrayObject);
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            Object item = Array.get(arrayObject, i);
            stringBuilder.append(createJsonForObject(item));
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String formatString(String value) {
        return String.format("\"%s\"", value);
    }

    private String getFieldValuePairs(String input){
        String[] fieldValuePairs;
        
    }

}

