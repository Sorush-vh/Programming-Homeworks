import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isStatic;

public class YourConvertor{

    private static Object recursiveObject=null;
    private static String keyRegex="(\"(?<key>[^\"]+)\":)";
    private static String objectValueRegex="(\\{(?<objectValue>[^\\}]+)\\})";
    private static String primitiveValueRegex="((?<primitiveValue>[^\\,\\{\\[]+)\\,?)";
    private static String arrayValueRegex="(\\[(?<arrayValue>[^\\]]+)\\])";
    private static String keyValPairRegex=keyRegex+"("+objectValueRegex+"|"+primitiveValueRegex
    +"|"+arrayValueRegex+")";

    public Object deserialize(String input, String className) {

            Class targetClass=null;
            try {
                targetClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object obj=getObjectInstanceOfClass(targetClass);
            HashMap<String,String> keyVal=getKeyValPairsFromSerializedString(input);

            for (Field field : addParentClassFields(obj))
                try {
                    restoreFieldOfObject(field, obj, keyVal.get(field.getName()));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
        recursiveObject=obj;
        return obj;
    }

    private void setValueToFieldOfList(Field field,Object object,String value) throws NumberFormatException, IllegalArgumentException, IllegalAccessException{
        String[] members=value.split(",");
        if(value.startsWith("\""))
            value=value.substring(1,value.length());

        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass.getSimpleName());

        ArrayList<Object> targetList=new ArrayList<>();
        for (int i = 0; i < members.length; i++) {
            System.out.println(members[i]);
        }

        if (stringListClass.getSimpleName().equals(Integer.class.getSimpleName())) 
            for (int i = 0; i < members.length; i++) 
                targetList.add(Integer.parseInt(members[i]));
            
        
        else if(stringListClass.getSimpleName().equals(Double.class.getSimpleName()))
            for (int i = 0; i < members.length; i++) 
                targetList.add(Double.parseDouble(members[i]));

        else if(stringListClass.getSimpleName().equals(Float.class.getSimpleName()))
            for (int i = 0; i < members.length; i++) 
                targetList.add(Float.parseFloat(members[i]));
    
        else if (stringListClass.getSimpleName().equals(String.class.getSimpleName())) 
            for (int i = 0; i < members.length; i++) 
                targetList.add(members[i]);
        

        field.set(object, targetList);
    }

    private Method getSetterMethod(Object object,Field field){
        Method setter=null;
        String setterName=getEdittedFieldsName(field);
        try  {
                setter =  object.getClass().getDeclaredMethod("set" + setterName,String.class);
            } catch (Exception ex) {
                return null;
            }
            return setter;
    }

    private void restoreFieldOfObject(Field field,Object object,String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        if(!java.lang.reflect.Modifier.isStatic(field.getModifiers()))
            field.setAccessible(true);

        if(value.startsWith("\""))
            value=value.substring(1,value.length()-1);
        
        if(field.getType().equals(Integer.TYPE))
            field.set(object, Integer.parseInt(value));
        
        else if(field.getType().equals(Double.TYPE))
            field.set(object, Double.parseDouble(value));

        else if(field.getType().equals(Float.TYPE))
            field.set(object, Float.parseFloat(value));

        else if(field.getType().equals(Character.TYPE))
            field.set(object, value.charAt(0));
        
        else if (field.getType().equals(String.class)) 
            field.set(object, value);

        else if (Collection.class.isAssignableFrom(field.getType())) 
           setValueToFieldOfList(field, object, value);

        else {
            value="\""+value+"\"";
            deserialize(value, field.getGenericType().getTypeName());
            field.set(object, recursiveObject);
        }

        Method setter=getSetterMethod(object, field);
        if(setter!=null)
            setter.invoke(object, value);
    }

    private Object getObjectInstanceOfClass(Class klass){
        System.out.println(klass.getSimpleName());
       try {
         Constructor cons = klass.getConstructor();
         Object targetObj=cons.newInstance();
         return targetObj;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
        return null;
    }
    }

    public HashMap<String,String> getKeyValPairsFromSerializedString(String serialInput){
        String trimmedString=serialInput;
        if(serialInput.startsWith("{"))
            trimmedString=serialInput.substring(1, serialInput.length()-1);

        // if(!serialInput.startsWith( "\"")){
        //     trimmedString="\""+trimmedString+"\"";
        // }


        Matcher matcher=getMatcher(trimmedString, keyValPairRegex);
        HashMap<String,String> keyValPairs=new HashMap<>();

        while (matcher.find()) {
            System.out.println(matcher.group("key")+"==="+getValue(matcher));
            keyValPairs.put(matcher.group("key"), getValue(matcher));
        }
        return keyValPairs;
    }

    private Matcher getMatcher(String input, String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);
        return matcher;
    }

    private String getValue(Matcher matcher){
        if(matcher.group("objectValue") !=null) return matcher.group("objectValue");
        else if(matcher.group("primitiveValue") !=null) return matcher.group("primitiveValue");
        else if(matcher.group("arrayValue") !=null) return matcher.group("arrayValue");
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
       
        Field[] fields = addParentClassFields(object);
        String[] fieldnames=new String[fields.length];
        HashMap<String,Field> fieldMapper=new HashMap<>();

        for (int i = 0; i < fields.length; i++){ 
            fieldnames[i]=fields[i].getName();
            fieldMapper.put(fieldnames[i],fields[i]);
        }    
        Arrays.sort(fieldnames);
        for (int i = 0; i < fieldnames.length; i++) 
            fields[i]=fieldMapper.get(fieldnames[i]);
        

        String output="{";
        int i=0;
        for (Field field : fields) {
            if (isStatic(field.getModifiers())) 
                continue;
            output=output.concat(encodeSingleField(field, object.getClass(), object));
            if(i!=fields.length-1)
                output=output.concat(",");
            i++;
        }
        output=output.concat("}");
        return output;
    }

    private Field[] addParentClassFields(Object object){
        Field[] self=object.getClass().getDeclaredFields();
        Field[] parentFields=object.getClass().getSuperclass().getDeclaredFields();
        Field[] result=new Field[self.length+parentFields.length];

        for (int i = 0; i < self.length; i++) 
            result[i]=self[i];

            int j=0;
        for (int i = self.length; i < result.length; i++) {
            result[i]=parentFields[j];
            j++;
        }
            return result;
    }

    private String encodeSingleField(Field field,Class klass,Object object) throws IllegalArgumentException, IllegalAccessException{

        StringBuilder stringBuilder = new StringBuilder();
        if(!Collection.class.isAssignableFrom(field.getType()))
        field.setAccessible(true);

        stringBuilder.append(formatString(field.getName()) + ":");
        String value="";
        
        if (field.getType().isPrimitive() && !field.getType().equals(Character.TYPE)) 
            value=field.get(object).toString();
        
        else if (field.getType().equals(String.class) || field.getType().isPrimitive()) 
            value=formatString(field.get(object).toString());

        else if (Collection.class.isAssignableFrom(field.getType())) 
            value=encodeLists(field, object);

        else 
            value=createJsonForObject(field.get(object));

        if(getCustomGetterOutput(field, object)!=null)
            value=formatString(getCustomGetterOutput(field, object));

            stringBuilder.append(value);
        
            return stringBuilder.toString();
        }

    private String encodeLists(Field field,Object object) throws IllegalArgumentException, IllegalAccessException{
        String getterName=getEdittedFieldsName(field);
        Method getter;

            try  {
                getter = object.getClass().getDeclaredMethod("get" + getterName);
            } catch (Exception ex) {
                return null;
            }
        if(getter!=null)
            try {
                ArrayList<Object> targetList=(ArrayList<Object>) getter.invoke(object);
                return convertArraylistToString(targetList);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
    }

    private String convertArraylistToString(ArrayList<Object> arrayList){
        String output="[";
        int i=0;
        for (Object object : arrayList) {
            output=output.concat(object.toString());
            if(i!=arrayList.size()-1)
                output=output.concat(",");
            i++;
        }
        output=output.concat("]");
        return output;
    }

    private String formatString(String value) {
        return String.format("\"%s\"", value);
    }

    private String getCustomGetterOutput(Field field,Object object){
        if(!field.getType().equals(String.class) ) return null;
        String getterName=getEdittedFieldsName(field);
        Method getter;
       try  {
                getter = object.getClass().getDeclaredMethod("get" + getterName);
            } catch (Exception ex) {
                return null;
            }
        if(getter!=null)
            try {
                return (String) getter.invoke(object);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
    }

    private String getEdittedFieldsName(Field field){
        String s1 = field.getName().substring(0, 1).toUpperCase(); // first letter = J.
        String s2 = field.getName().substring(1); // after 1st letter = avatpoint.
        return s1+s2;
    }

}

