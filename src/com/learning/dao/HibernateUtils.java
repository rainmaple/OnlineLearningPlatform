package com.learning.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
  //静态代码块
	static Configuration cfg=null;
	static SessionFactory sessionFactory=null;
//	static{
//		//加载核心配置文件
//		cfg=new Configuration();
//		cfg.configure();
//		sessionFactory=cfg.buildSessionFactory();
//	}
	static{
		//加载核心配置文件
		cfg=new Configuration().configure();
		//在hibernate3中都是使用该种方法创建，但是在4中被禁用了
		//cfg.buildSessionFactory();	
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
		    .applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		
	}
	
	//提供方法返回sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
