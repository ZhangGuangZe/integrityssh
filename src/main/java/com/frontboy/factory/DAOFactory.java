package com.frontboy.factory;

import com.frontboy.dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DAOFactory {
	private static ApplicationContext ctx = null;

	static{
		ctx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
	}

	/**
	 * 获取用户业务实现类对象
	 * @return UserDAO
	 */
	public static UserDAO getUserDAO(){
		return (UserDAO)ctx.getBean("userDAO");
	}

	/**
	 * 获取角色业务实现类对象
	 * @return RoleDAO
	 */
	public static RoleDAO getRoleDAO(){
		return (RoleDAO)ctx.getBean("roleDAO");
	}

	/**
	 * 获取旅行社业务实现类对象
	 * @return AgencyDAO
	 */
	public static AgencyDAO getAgencyDAO(){
		return (AgencyDAO)ctx.getBean("agencyDAO");
	}

	/**
	 * 获取黑名单业务实现类对象
	 * @return BlockDAO
	 */
	public static BlockDAO getBlockDAO(){
		return (BlockDAO)ctx.getBean("blockDAO");
	}

	/**
	 * 获取红黑榜业务实现类对象
	 * @return RedBlockDAO
	 */
	public static RedBlockDAO getRedBlockDAO(){
		return (RedBlockDAO)ctx.getBean("redBlockDAO");
	}
	/**
	 * 获取投诉业务实现类对象
	 * @return ComplaintDAO
	 */
	public static ComplaintDAO getComplaintDAO(){
		return (ComplaintDAO)ctx.getBean("complaintDAO");
	}
	/**
	 * 获取评价业务实现类对象
	 * @return BlockDAO
	 */
	public static EvaluationDAO getEvaluationDAO(){
		return (EvaluationDAO)ctx.getBean("evaluationDAO");
	}

}
