package com.ych.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ResultUtils {

	public static void toJson(HttpServletResponse response, Object data)   
	         throws IOException {  
	    	GsonBuilder b = new GsonBuilder();
//	    	b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	         Gson gson = b.create();  
	         String result = gson.toJson(data);  
	         response.setContentType("text/json; charset=utf-8");  
    //        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
	         PrintWriter out = response.getWriter();  
	         out.print(result);  
	         System.out.println(result.toString());
	         out.flush();  
	         out.close();  
		     }  
	
	
	public static void toJsonDateSerializer(HttpServletResponse response, Object data, final String dateformat) throws IOException{
		 String jsonStr = null;    
	        Gson gson = new GsonBuilder()    
	                .registerTypeHierarchyAdapter(Date.class,    
	                        new JsonSerializer<Date>() {    
	                            public JsonElement serialize(Date d,    
	                                    Type typeOfSrc,    
	                                    JsonSerializationContext context) {    
	                            	StringBuffer str = new StringBuffer();
	                            	long delta = (new Date().getTime()-d.getTime())/1000;  
	                                if(delta<=0)str.append( d.toLocaleString());  
	                                else if(delta/(60*60*24*3) > 0){
	                                	 SimpleDateFormat format = new SimpleDateFormat(dateformat);    
	                                	 str.append(format.format(d));
	                               }else if(delta/(60*60*24) > 0) str.append( delta/(60*60*24) +"天前");  
	                                else if(delta/(60*60) > 0)str.append(delta/(60*60) +"小时前");  
	                                else if(delta/(60) > 0)str.append( delta/(60) +"分钟前"); 
	                                else
	                                	str.append(delta+"秒前");   
	                                return new JsonPrimitive(str.toString());    
	                            }    
	                        }).setDateFormat(dateformat).create();    
	        if (gson != null) {    
	            jsonStr = gson.toJson(data);    
	        }   
	        
	        response.setContentType("text/json; charset=utf-8");  
	        //        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
	        PrintWriter out = response.getWriter();  
	    	out.print(jsonStr);  
	        System.out.println(jsonStr.toString());
	    	out.flush();  
	    	out.close();  
	}
	
	public static void toJson(HttpServletResponse response, Object data,JsonConfig jsonConfig)   
	         throws IOException {  
			JSONArray jsonArray = JSONArray.fromObject(data,jsonConfig);
	         response.setContentType("text/json; charset=utf-8");  
	         PrintWriter out = response.getWriter();  
	         System.out.println(jsonArray.toString());
	         out.print(jsonArray.toString());  
	         out.flush();  
	         out.close();  
		     } 
	/**
	 * object 转 json String
	 * @param obj
	 * @return
	 */
	public static String writeEntityToJSON(Object obj) {
		
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			System.out.println(objectMapper.writeValueAsString(obj));
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**  
     * 将对象转换成json格式(并自定义日期格式)  
     *   
     * @param ts  
     * @return  
     */    
//    public static String objectToJsonDateSerializer(Object ts,    
//            final String dateformat) {    
//        String jsonStr = null;    
//        Gson gson = new GsonBuilder()    
//                .registerTypeHierarchyAdapter(Date.class,    
//                        new JsonSerializer<Date>() {    
//                            public JsonElement serialize(Date src,    
//                                    Type typeOfSrc,    
//                                    JsonSerializationContext context) {    
//                                SimpleDateFormat format = new SimpleDateFormat(    
//                                        dateformat);    
//                                return new JsonPrimitive(format.format(src));    
//                            }    
//                        }).setDateFormat(dateformat).create();    
//        if (gson != null) {    
//            jsonStr = gson.toJson(ts);    
//        }   
//        System.out.println(jsonStr);
//        return jsonStr;    
//    }    
	
    public static String objectToJsonDateSerializer(Object ts,    
            final String dateformat) {    
        String jsonStr = null;    
        Gson gson = new GsonBuilder()    
                .registerTypeHierarchyAdapter(Date.class,    
                        new JsonSerializer<Date>() {    
                            public JsonElement serialize(Date d,    
                                    Type typeOfSrc,    
                                    JsonSerializationContext context) {      
                            	StringBuffer str = new StringBuffer();
                            	long delta = (new Date().getTime()-d.getTime())/1000;  
                                if(delta<=0)str.append( d.toLocaleString());  
                                else if(delta/(60*60*24*3) > 0){
                                	 SimpleDateFormat format = new SimpleDateFormat(dateformat);    
                                	 str.append(format.format(d));
                               }else if(delta/(60*60*24) > 0) str.append( delta/(60*60*24) +"天前");  
                                else if(delta/(60*60) > 0)str.append(delta/(60*60) +"小时前");  
                                else if(delta/(60) > 0)str.append( delta/(60) +"分钟前"); 
                                else
                                 str.append(delta+"秒前");  
                                return new JsonPrimitive(str.toString());    
                            }    
                        }).setDateFormat(dateformat).create();    
        if (gson != null) {    
            jsonStr = gson.toJson(ts);    
        }   
        System.out.println(jsonStr);
        return jsonStr;    
    }    
    
    public static String objectToJsonDate(Object ts,    
            final String dateformat) {    
        String jsonStr = null;    
        Gson gson = new GsonBuilder()    
                .registerTypeHierarchyAdapter(Date.class,    
                        new JsonSerializer<Date>() {    
                            public JsonElement serialize(Date d,    
                                    Type typeOfSrc,    
                                    JsonSerializationContext context) {      
                            	SimpleDateFormat format = new SimpleDateFormat(dateformat);    
                              return new JsonPrimitive(format.format(d));  
                            }    
                        }).setDateFormat(dateformat).create();    
        if (gson != null) {    
            jsonStr = gson.toJson(ts);    
        }   
        System.out.println(jsonStr);
        return jsonStr;    
    }    
	
	
	/**
	 * 
	* @Title: writeToJSONNotNull 
	* @Description: TODO(不返回null值) 
	* @param @param obj
	* @param @return    设定文件 
	* @return String    返回类型
	 */
	public static String writeToJSONNotNull(Object obj){
		
		Gson gson = new GsonBuilder().create();
		 String result = gson.toJson(obj);
		 System.out.println(result);
		 return result;
	}
	
	public static void toJsonDate(HttpServletResponse response, Object data) throws IOException{
		
		GsonBuilder b = new GsonBuilder().setDateFormat("yyyy-MM-dd");
//    	b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		
    	Gson gson = b.create();  
  
         String result = gson.toJson(data);  
		
         response.setContentType("text/json; charset=utf-8");  
//        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
         PrintWriter out = response.getWriter();  
         out.print(result);  
         System.out.println(result.toString());
         out.flush();  
         out.close();  
         
		      
		
	//	response.getWriter().write(jsonObject.toString()); 
	}
	
}
