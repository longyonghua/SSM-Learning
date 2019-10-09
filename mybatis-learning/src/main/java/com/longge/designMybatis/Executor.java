package com.longge.designMybatis;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Executor{
	public<E> List<E> selectList(Mapper mapper, Connection conn){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String queryString = mapper.getQueryString();
			String resultType = mapper.getResultType();
			Class domainClass = Class.forName(resultType);
			pstm = conn.prepareStatement(queryString);
			rs = pstm.executeQuery();
			List<E> list = new ArrayList<E>();
			while(rs.next()){
				//实例化要封装的实体类对象
				E obj = (E)domainClass.getDeclaredConstructor().newInstance();
				//取出结果集的元信息
				ResultSetMetaData rsmd = rs.getMetaData();
				//取出总列数
				int columnCount = rsmd.getColumnCount();
				for(int i=1;i<=columnCount;i++){
					//获取每列的名称，列名的序号是从1开始的
					String columnName = rsmd.getColumnName(i);
					//根据列名获取每列的值
					Object columnValue = rs.getObject(columnName);
					//给obj赋值：使用java内省机制（借助PropertyDescriptor实现属性的封装）
					PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj,columnValue);
				}
				list.add(obj);
			}
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			release(pstm,rs);
		}
	}

	private void release(PreparedStatement pstm,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(pstm!=null){
			try{
				pstm.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}