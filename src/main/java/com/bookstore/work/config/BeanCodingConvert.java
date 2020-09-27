package com.bookstore.work.config;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.bean.BeanUtil;


/**
 * 实体bean 编码转换，主要针对oracle us7ascii的情况
 * @author zhaokui
 * 2018年1月29日 
 */
public class BeanCodingConvert {

	public final static int ISO2GBK = 1;  
	public final static int GBK2ISO = 2;  
	  
    public BeanCodingConvert() {  
        super();  
    }  
	  
    public static void listConvert(List list,int encodeType) {  
        if (list == null && !(list instanceof List)) {  
            return;  
        }  
        for (int i = 0; i < list.size(); i++) {  
            Object co = list.get(i); 
            if(co == null){
            	continue;
            }
            if(co instanceof Object[])  
                objectConvert((Object[])co, encodeType);  
            else{  
                if(co instanceof String){  
                    co = stringConvert((String)co, encodeType);  
                    list.set(i, co);  
                }else if(co instanceof Map){ 
                	mapConvert((Map)co, encodeType);
                }else{  
                    objectConvert(co,encodeType);  
                }  
            }  
        }  
    }  
  
    public static void iteratorConvert(Iterator iter,int encodeType) {  
        if (iter == null && !(iter instanceof Iterator)) {  
            return;  
        }  
        while (iter.hasNext()) {  
            Object co = iter.next();  
            objectConvert(co,encodeType);  
        }  
    }  
      
    public static void objectConvert(Object[] o,int encodeType) {  
        for(int i = 0;i < o.length; i++){  
            if (o[i] instanceof String)  
                o[i] = stringConvert((String)o[i],encodeType);  
        }  
    }  
  
    public static void objectConvert(Object o, int encodeType) {
    	if(o == null) return;
    	if(!BeanUtil.isBean(o.getClass())) return;
        try {
            Map map = BeanUtil.beanToMap(o);
            Set keySet = map.keySet();  
            for (Iterator iter = keySet.iterator(); iter.hasNext();) {
                Object element = iter.next(); 
                
            	PropertyDescriptor desc = BeanUtil.getPropertyDescriptor(o.getClass(), element.toString());
            	if(desc == null) return;
            	 Class cl = desc.getPropertyType(); 
            	 if (cl.equals(String.class)) { 
            		 BeanUtil.createDynaBean(o).set(element.toString(), encodeType==ISO2GBK?objectCharacterConvertChinese(map.get(element)):objectCharacterConvertISO(map.get(element)));
            	 }
                	 
            }
        } catch (IntrospectionException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static void mapConvert(Map map, int encodeType) {
    	 if (map == null) {
             return;  
         }
    	 
    	 Set keySet = map.keySet();  
         for (Iterator iter = keySet.iterator(); iter.hasNext();) {
             Object element = iter.next(); 
             Object value = map.get(element);
             if(value == null || "".equals(value.toString().trim())){
            	 continue;
             }
             if(value instanceof String){
            	 map.put(element, stringConvert((String)value, encodeType));
             }
         }
    	 
    }
      
    public static String stringConvert(String s,int encodeType){  
        String tmp = encodeType==ISO2GBK?objectCharacterConvertChinese(s):objectCharacterConvertISO(s);  
        return tmp;  
    }  
      
    public static String[] stringConvert(String[] args, int encodeType){  
        for(int i=0; i<args.length; i++){  
            args[i] = stringConvert(args[i], encodeType);  
        }  
        return args;  
    }  
  
    private static String objectCharacterConvertChinese(Object value) {  
        if (value == null && !(value instanceof String)) {  
            return null;  
        }  
        try {  
            String temp_p = (String) value;  
            byte[] temp_t = temp_p.getBytes("ISO-8859-1");  
            String unicode = new String(temp_t, "gbk");  
            return unicode;  
        } catch (UnsupportedEncodingException e) {  
            return "";  
        }  
    }  
  
    private static String objectCharacterConvertISO(Object value) {  
        if (value == null && !(value instanceof String)) {  
            return null;  
        }  
        try {  
            String temp_p = (String) value;  
            byte[] temp_t = temp_p.getBytes("gbk");  
            String unicode = new String(temp_t, "ISO-8859-1");  
            return unicode;  
        } catch (UnsupportedEncodingException e) {  
            return "";  
        }  
    }  
	
    public static void main(String[] args) {
/*		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a",1);
		map.put("b","张三");
		map.put("c","c,");
		
		mapConvert(map, 2);
		
		 for (Map.Entry<String, Object> entry : map.entrySet()) {
	            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
	        }*/
    	
    	System.out.println(objectCharacterConvertISO("测试部门7"));
    	System.out.println(objectCharacterConvertISO(objectCharacterConvertISO("测试部门7")));
	}
    
}
