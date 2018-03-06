package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
	//�ð����ļ�����������hibernate�ĳ�ʼ������ȡ�����ļ�
  //��̬�����
	static Configuration cfg=null;
	static SessionFactory sessionFactory=null;
	static{
		//���غ��������ļ�
		cfg=new Configuration().configure();
		//��hibernate3�ж���ʹ�ø��ַ���������������4�б�������
		//cfg.buildSessionFactory();	
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
		    .applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		
	}
	
	//�ṩ��������sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
