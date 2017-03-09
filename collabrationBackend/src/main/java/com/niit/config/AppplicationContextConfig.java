package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.UserDAO;
import com.niit.DAO.UserDAOImpl;
import com.niit.Model.BlogModel;
import com.niit.Model.EventModel;
import com.niit.Model.ForumModel;
import com.niit.Model.FriendModel;
import com.niit.Model.JobModel;
import com.niit.Model.UserModel;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class AppplicationContextConfig {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("COLLOBRATION");
	dataSource.setPassword("admin");
	 
	    return dataSource;
	}
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	  // properties.put("hibernate.hbm2ddl.auto", "update");
	    return properties;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	 LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClasses(UserModel.class);
	sessionBuilder.addAnnotatedClasses(EventModel.class);
	sessionBuilder.addAnnotatedClasses(ForumModel.class);
	sessionBuilder.addAnnotatedClasses(FriendModel.class);
	sessionBuilder.addAnnotatedClasses(JobModel.class);
	sessionBuilder.addAnnotatedClasses(BlogModel.class);
	
     return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	return transactionManager;
	}
	@Autowired
	   @Bean(name = "userDao")
	   public UserDAO getCategoryDao(SessionFactory sessionFactory) {
	   return new UserDAOImpl(sessionFactory);
	   }
}
