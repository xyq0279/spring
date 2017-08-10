package com.jt.common.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Table;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;

public class SysMapperProvider extends MapperProvider {

    public SysMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode deleteByIDS(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        EntityHelper.EntityColumn column = null;
        for (EntityHelper.EntityColumn entityColumn : entityColumns) {
            column = entityColumn;
            break;
        }
        
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        // 开始拼sql
        BEGIN();
        // delete from table
        DELETE_FROM(tableName(entityClass));
        // 得到sql
        String sql = SQL();
        // 静态SQL部分
        sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
        // 构造foreach sql
        SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
                + column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
        sqlNodes.add(foreach);
        return new MixedSqlNode(sqlNodes);
    }
    
    public SqlNode findCount(MappedStatement ms){
    	try {
    		//获取方法名全路径
    		String  methodName = ms.getId();
    		//截取类名
    		String className = methodName.substring(0, methodName.lastIndexOf("."));
    		//获得类对象
    		Class<?> targetClass = Class.forName(className);
    		//获得继承的上级接口
    		Type[] types = targetClass.getGenericInterfaces(); 
    		Type type = types[0];
			//判断接口上是否有泛型 之后获得泛型
    		if(type instanceof ParameterizedType){
    			//向下造型，强转为泛型
    			ParameterizedType parameterizedType = (ParameterizedType) type;
    			//获取接口上的泛型参数类型
    			Type[] argsType = parameterizedType.getActualTypeArguments();
    			//获得第一个参数类型
    			Class<?> typeClass = (Class<?>) argsType[0];
    			//获得参数类上的注解
    			Table table = typeClass.getAnnotation(Table.class);
    			//获得注解的name属性
    			String name = table.name();
    			//拼接SQL语句 
    			String sql = "select count(*) from "+name;
    			//
    			return new StaticTextSqlNode(sql);
    		}
    		
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
    	return null;
    }
    
}
