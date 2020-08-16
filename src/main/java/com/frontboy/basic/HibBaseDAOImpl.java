package com.frontboy.basic;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frontboy.util.LogUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.SessionFactoryUtils;

import static com.frontboy.basic.HibernateSessionFactory.getSessionFactory;

public class HibBaseDAOImpl implements iHibBaseDAO {
	Session globesession = null;

	//定义日志管理对象
	private static final Log log = LogFactory.getLog(HibBaseDAOImpl.class);

	public static final int INSERT = 1; //代表添加操作
	public static final int UPDATE = 2; //代表修改操作
	public static final int DELETE = 3; //代表删除操作

	public void close(){
		globesession.close();
	}

	@Override
	public int insert(Object obj) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try{
			Serializable key = session.save(obj);
			tx.commit();
			session.close(); //关闭连接
			return ((Integer)key).intValue();

		}catch(ClassCastException e){
			return Integer.MAX_VALUE;
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类insert(Object obj)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类insert(Object obj)方法调用错误：");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean insert(List<Object> list) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try{
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i);
				session.save(obj);
			}
			tx.commit();
			session.close();
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类insert(Object obj)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类insert(Object obj)方法调用错误：");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Class cls, Serializable id) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try{
			session.delete(session.get(cls, id));
			tx.commit();
			session.close(); //关闭连接
			return true;

		}catch(Exception e){
			if(tx!=null) tx.rollback();
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类delete(Object obj)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类delete(Object obj)方法调用错误：");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try{
			session.delete(obj);
			tx.commit();
			session.close(); //关闭连接
			return true;

		}catch(Exception e){
			if(tx!=null) tx.rollback();
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类delete(Object obj)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类delete(Object obj)方法调用错误：");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Object obj) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(obj);
			//TSchool school = (TSchool)obj;
			//System.out.println(school.getSchoolid() + "," + school.getSchoolname());
			tx.commit();
			session.close(); //关闭连接
			return true;

		}catch(Exception e){
			if(tx!=null) tx.rollback();
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类update(Object obj)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类update(Object obj)方法调用错误：");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List select(String hql) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		try{
			Query query = session.createQuery(hql);
			List list =  query.list();
			session.close();
			return list;
		}catch(Exception e){
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类select(String hql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类select(String hql)方法调用错误：");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List select(String hql, int startIndex, int length) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		try {
			Query query =session.createQuery(hql);
			query.setFirstResult(startIndex); //设置起始记录位置
			query.setMaxResults(length); //设置返回记录数
			List list  = query.list();
			session.close();
			return list;
		} catch (Exception e) {
			log.error("basic.HibBaseDAO类select(String hql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类select(String hql)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public List select(String hql, Object[] para )throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		try{
			Query query = session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query.setParameter(i, para[i]);
			}
			List list = query.list();
			session.close();
			return list;

		}catch(Exception e){
			session.close(); //关闭连接
			log.error("basic.HibBaseDAO类select(String hql, Object[] para)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类select(String hql, Object[] para)方法调用错误：");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List select(String hql, Object[] para, int startIndex, int length) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		try {
			Query query =session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query.setParameter(i, para[i]);
			}
			query.setFirstResult(startIndex); //设置起始记录位置
			query.setMaxResults(length); //设置返回记录数
			List list  = query.list();
			session.close();
			return list;
		} catch (Exception e) {
			log.error("HibBaseDAO类select(String hql, Object[] para, int startIndex, int length)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("HibBaseDAO类select(String hql, Object[] para, int startIndex, int length)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public int selectValue(String hql) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		int count;
		try {
			Object obj = session.createQuery(hql).uniqueResult();
			session.close();
			if(obj instanceof Long)
				return ((Long)obj).intValue();
			else if(obj instanceof Integer)
				return ((Integer)obj).intValue();
		} catch (Exception e) {
			log.error("basic.HibBaseDAO类selectCount(String hql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类selectCount(String hql)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return 0;
	}

	@Override
	public int selectValue(String hql, Object[] para)throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		try {
			Query query = session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query.setParameter(i, para[i]);
			}

			Object obj = query.uniqueResult();
			session.close();
			if(obj instanceof Long)
				return ((Long)obj).intValue();
			else if(obj instanceof Integer)
				return ((Integer)obj).intValue();

		} catch (Exception e) {
			log.error("basic.HibBaseDAO类selectValue(String hql, Object[] para)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类selectValue(String hql, Object[] para)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return 0;
	}

	@Override
	public int selectPages(String hql, int pageSize) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		//编程思想：先获得查询记录数，再使用算法来计算出分页的页数
		long pages_all = 0;
		try {
			List list = session.createQuery(hql).list();
			//获得查询记录总数
			long records = list.size();
			//计算分页数
			pages_all = records%pageSize==0?records/pageSize:records/pageSize + 1;  //获得总页数
			session.close();
			return (int)pages_all;
		}catch(Exception e){
			log.error("getSizeByPage(String hql,int pageSize)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("getSizeByPage(String hql,int pageSize)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return 0;
	}

	@Override
	public int selectPages(String hql, Object[] para, int pageSize)throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		//编程思想：先获得查询记录数，再使用算法来计算出分页的页数
		long pages_all = 0;
		try {
			Query query = session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query.setParameter(i, para[i]);
			}
			List list = query.list();
			//获得查询记录总数
			long records = list.size();
			//计算分页数
			pages_all = records%pageSize==0?records/pageSize:records/pageSize + 1;  //获得总页数
			session.close();
			return (int)pages_all;
		}catch(Exception e){
			log.error("getSizeByPage(String hql,int pageSize)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("getSizeByPage(String hql,int pageSize)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return 0;
	}

	@Override
	public List selectByPage(String hql, int startPage, int pageSize)throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		List pList = null;
		int currentPage;
		try {
			Query query = session.createQuery(hql);

			List list = query.list();
			//获得查询记录总数
			long records = list.size();
			//获得总页数
			int pages_all = (int)(records%pageSize==0?records/pageSize:records/pageSize + 1);  //获得总页数

			//设置类成员当前页面的操作页码
			if(startPage<=1){
				currentPage = 1;
			}else if(startPage >=pages_all){
				currentPage = pages_all;
			}else {
				currentPage = startPage;
			}

			Query query2 = session.createQuery(hql);
			query2.setFirstResult((currentPage -1)* pageSize);//从第几条记录开始查询
			query2.setFetchSize(pageSize);//每页显示多少条记录
			pList = query2.list();
			session.close();
		} catch (Exception e) {
			log.error("selectByPage(String hql,int startPage, int pageSize)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("selectByPage(String hql,int startPage, int pageSize)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return pList;
	}

	@Override
	public List selectByPage(String hql, Object[] para, int startPage,
							 int pageSize) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		List pList = null;
		int currentPage;
		try {
			Query query = session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query.setParameter(i, para[i]);
			}
			List list = query.list();
			//获得查询记录总数
			long records = list.size();
			//获得总页数
			int pages_all = (int)(records%pageSize==0?records/pageSize:records/pageSize + 1);  //获得总页数

			//设置类成员当前页面的操作页码
			if(startPage<=1){
				currentPage = 1;
			}else if(startPage >=pages_all){
				currentPage = pages_all;
			}else {
				currentPage = startPage;
			}

			Query query2 = session.createQuery(hql);
			//根据para设置参数
			for(int i=0;i<para.length;i++){
				query2.setParameter(i, para[i]);
			}
			query2.setFirstResult((currentPage -1)* pageSize);//从第几条记录开始查询
			query2.setFetchSize(pageSize);//每页显示多少条记录
			pList = query2.list();
			session.close();
		} catch (Exception e) {
			log.error("selectByPage(String hql,int startPage, int pageSize)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("selectByPage(String hql,int startPage, int pageSize)方法调用错误：");
			e.printStackTrace();
			session.close();
		}
		return pList;
	}

	@Override
	public Object findById(Class cls, Serializable id)throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		try {
			Object obj = session.get(cls, id);
			session.close();
			return obj;
		} catch (Exception e) {
			session.close();
			log.error("basic.HibBaseDAO类findById(Class cls, Serializable id)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.println("basic.HibBaseDAO类findById(Class cls, Serializable id)方法调用错误：");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List selectBysql(String sql) throws HibernateException {
		Session session = HibernateSessionFactory.getSession();

		// 将会话session对象转换为jdbc的connection
		try {
		Connection con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		PreparedStatement ptmt;
			ptmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ptmt.executeQuery();
			// 转list
			List list = new ArrayList();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount(); // Map rowData;
			while (rs.next()) { // rowData = new HashMap(columnCount);
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(String sql)throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Connection con=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.executeUpdate();
			tx.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			log.error("basic.HibBaseDAO类update(String sql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类update(String sql)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}

	@Override
	public boolean update(String hql, Object[] para) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Connection con=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			PreparedStatement stmt=con.prepareStatement(hql);
			for(int i=0;i<para.length;i++){
				stmt.setObject(i+1, para[i]);
			}
			stmt.executeUpdate();
			tx.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			log.error("basic.HibBaseDAO类update(String hql,Object[] para)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类update(String hql,Object[] para)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}

	@Override
	public boolean delete(String sql) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Connection con=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.executeUpdate();
			tx.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			log.error("basic.HibBaseDAO类delete(String sql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类delete(String sql)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}

	@Override
	public boolean delete(String sql, Object[] para)throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Connection con=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			PreparedStatement stmt=con.prepareStatement(sql);
			for(int i=0;i<para.length;i++){
				stmt.setObject(i+1, para[i]);
			}
			stmt.executeUpdate();
			tx.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			log.error("basic.HibBaseDAO类HibBaseDAO类delete(String sql)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类HibBaseDAO类delete(String sql)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}

	@Override
	public Object executeProduce(String procName) throws HibernateException{
		//System.out.println("executeProduce1");
		globesession = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = globesession.beginTransaction();
			Connection conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			CallableStatement ctmt=conn.prepareCall("{? = call "+procName+"}");
			ctmt.registerOutParameter(1, Types.INTEGER);

			boolean type = ctmt.execute();
			tx.commit();//执行数据库提交操作
			if(type){ //为true表明存储过程是一个select语句

				ResultSet rs = ctmt.getResultSet();
				//globesession.close();
				return rs;
			}else{ //不是select，则获取返回值
				int isSuccess = ctmt.getInt(1); //获得返回值。
				globesession.close();
				return new Integer(isSuccess);
			}

		} catch (Exception e) {
			log.error("basic.HibBaseDAO类executeProduce(String procName,Object[] para, ResultSet rs)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类executeProduce(String procName,Object[] para, ResultSet rs)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			globesession.close();
		}
		return null;
	}

	@Override
	public Object executeProduce(String procName, Object[] para) throws HibernateException{
		//System.out.println("executeProduce2");
		globesession = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = globesession.beginTransaction();
			Connection conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			CallableStatement ctmt=conn.prepareCall("{? = call "+procName+"}");
			ctmt.registerOutParameter(1, Types.INTEGER);
			for(int i=0;i<para.length;i++){
				ctmt.setObject(i+2, para[i]);
			}
			boolean type = ctmt.execute();
			tx.commit();
			if(type){ //为true表明存储过程是一个select语句

				ResultSet rs = ctmt.getResultSet();
				//globesession.close();
				return rs;
			}else{ //不是select，则获取返回值

				int isSuccess = ctmt.getInt(1); //获得返回值。
				globesession.close();
				return new Integer(isSuccess);
			}

		} catch (Exception e) {
			log.error("basic.HibBaseDAO类executeProduce(String procName,Object[] para, ResultSet rs)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类executeProduce(String procName,Object[] para, ResultSet rs)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			globesession.close();
		}
		return null;
	}

	//批处理执行的是基于hibernate的单个对象的添加、更新、删除操作
	//添加insert()   更新update()   删除delete()
	//model是一个用于存储对应Object对象实现的更新操作类型，INSERT,UPDATE,DELETE
	@Override
	public boolean executeBatch(Object[] obj, int[] model) throws HibernateException{
		System.out.println("executeBatch");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		try {
			for(int i=0; i<obj.length; i++){
				if(model[i]==INSERT)
					session.save(obj[i]);
				else if(model[i]==UPDATE)
					session.update(obj[i]);
				else if(model[i]==DELETE)
					session.delete(obj[i]);
			}
			tx.commit();
			session.close();
			return true;

		}catch(Exception e){
			log.error("basic.HibBaseDAO类executeBatch(Object[] obj, int[] model)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类executeBatch(Object[] obj, int[] model)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}

	public boolean executeBatch(List<Object> list,List<Integer> models) {
		//System.out.println("executeBatch");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(int i=0; i<list.size(); i++){
				Object obj = list.get(i);
				Integer model = (Integer)models.get(i);
				if(model.intValue()==INSERT){
					System.out.println("Insert");
					session.save(obj);
				}else if(model.intValue()==UPDATE){
					System.out.println("update");
					session.update(obj);

				}else if(model.intValue()==DELETE){
					System.out.println("delete");
					session.delete(obj);

				}
			}
			tx.commit();
			session.close();
			return true;
		}catch(Exception e){
			log.error("basic.HibBaseDAO类executeBatch(Object[] obj, int[] model)方法调用错误：");
			log.error(LogUtil.getMsg(e));
			System.out.print("basic.HibBaseDAO类executeBatch(Object[] obj, int[] model)方法调用错误：");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();  //错误时，回滚操作
			}
			session.close();
		}
		return false;
	}
}