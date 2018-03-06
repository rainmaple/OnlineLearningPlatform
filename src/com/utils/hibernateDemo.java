package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class hibernateDemo {
   
	@Test
	public void testAdd(){
//		第一步：加载hibernate核心配置文件
		//到src下面找到名称是hibernate.cfg.xml
		//在hibernate里面封装了对象
		Configuration cfg=new Configuration();
		cfg.configure();
		
		
//		第二步：创建sessionfactory对象
		//读取核心配置文件创建sessionFactory对象
		//根据映射关系，在配置数据库里把表创建
		
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		
//		第三步：使用sessionfactory对象创建session
		//类似于连接
		Session session=sessionFactory.openSession();
		
//		第四步：开启事务
		Transaction tx=session.beginTransaction();
//		第五步：写具体逻辑crud操作
		
		System.out.println("这是测试");
		
//		第六步：提交事务
		tx.commit();
//		第七步：关闭资源
        session.close();
        sessionFactory.close();

	}
}
