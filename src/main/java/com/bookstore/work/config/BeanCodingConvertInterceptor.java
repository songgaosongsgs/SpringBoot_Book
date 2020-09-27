package com.bookstore.work.config;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * 实体/bean 编码转换拦截器，主要针对oracle us7ascii的情况
 * @author zhaokui
 * 2018年1月29日 
 */
@Intercepts({  
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),  
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}) })  
public class BeanCodingConvertInterceptor implements Interceptor {

	private String charset; 
	
    private static final String NAME_ENTITY = "et";
    private static final String NAME_ENTITY_WRAPPER = "ew";
	 
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0]; 
		String sqlId = mappedStatement.getId();
        String methodName = invocation.getMethod().getName();
        Object parameter = null;
        if ((methodName.equals("query") || methodName.equals("update")) && sqlId.lastIndexOf("insert!selectKey") == -1) {
            parameter = invocation.getArgs()[1];  
//            RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];  
            if("US7ASCII".equalsIgnoreCase(charset) && parameter != null){
            	converFieldValue(parameter, true);
            }       
        }
        
        Object result = invocation.proceed();
        if("US7ASCII".equalsIgnoreCase(charset)){
        	if(result != null){
        		 if (result instanceof List) {
    	    		 BeanCodingConvert.listConvert((List)result, BeanCodingConvert.ISO2GBK);
    	    	 }else{
    	    		 BeanCodingConvert.objectConvert(result, BeanCodingConvert.ISO2GBK);
    	    	 }
        	}
	    	 
	    	 if(parameter != null){
	    		 converFieldValue(parameter, false);
	    	 }
        }
        
        return result;
	}

	@Override
	public Object plugin(Object target) {
		 return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		 this.charset = properties.getProperty("charset", "");
	}

	/**
	 * 
	 * @param parameter
	 * @param flag 1 str2ISO88591 2 ISO885912str
	 * @author zhaokui
	 * 2018年6月8日 
	 */
	public void converFieldValue(Object parameter, boolean flag){
		if (parameter instanceof MapperMethod.ParamMap) {
	      	  MapperMethod.ParamMap map = (MapperMethod.ParamMap) parameter;
	  		  Wrapper ew = null;
	            if (map.containsKey(NAME_ENTITY_WRAPPER)) {//mapper.update(updEntity, EntityWrapper<>(whereEntity);
	                ew = (Wrapper) map.get(NAME_ENTITY_WRAPPER);
	            }
	            Object et = null;
	            if (map.containsKey(NAME_ENTITY)) {
	                et = map.get(NAME_ENTITY);
	            }
	            
	            if (ew != null) {
	                BeanCodingConvert.mapConvert(ew.getParamNameValuePairs(), flag ? BeanCodingConvert.GBK2ISO : BeanCodingConvert.ISO2GBK);
	            } else if (et != null) {
	          	  BeanCodingConvert.objectConvert(et, flag ? BeanCodingConvert.GBK2ISO : BeanCodingConvert.ISO2GBK);
	            }else{
	          	  BeanCodingConvert.mapConvert(map, flag ? BeanCodingConvert.GBK2ISO : BeanCodingConvert.ISO2GBK);
	            }
	  	} else if(parameter instanceof String){
	  		BeanCodingConvert.stringConvert((String)parameter, flag ? BeanCodingConvert.GBK2ISO : BeanCodingConvert.ISO2GBK);
	  	} else {
	  		BeanCodingConvert.objectConvert(parameter, flag ? BeanCodingConvert.GBK2ISO : BeanCodingConvert.ISO2GBK);
	  	}
	}
	
}
