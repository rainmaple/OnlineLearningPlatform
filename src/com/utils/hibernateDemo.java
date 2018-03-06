package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class hibernateDemo {
   
	@Test
	public void testAdd(){
//		��һ��������hibernate���������ļ�
		//��src�����ҵ�������hibernate.cfg.xml
		//��hibernate�����װ�˶���
		Configuration cfg=new Configuration();
		cfg.configure();
		
		
//		�ڶ���������sessionfactory����
		//��ȡ���������ļ�����sessionFactory����
		//����ӳ���ϵ�����������ݿ���ѱ���
		
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		
//		��������ʹ��sessionfactory���󴴽�session
		//����������
		Session session=sessionFactory.openSession();
		
//		���Ĳ�����������
		Transaction tx=session.beginTransaction();
//		���岽��д�����߼�crud����
		
		System.out.println("���ǲ���");
		
//		���������ύ����
		tx.commit();
//		���߲����ر���Դ
        session.close();
        sessionFactory.close();

	}
}
