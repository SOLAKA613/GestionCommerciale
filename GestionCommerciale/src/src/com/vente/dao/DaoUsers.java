package src.com.vente.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import src.com.vente.entities.Users;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository("daoUsers")
public class DaoUsers implements IDao<Users>{

	@Autowired
	@Qualifier(value="sessionFactoryOne")
	SessionFactory sessionFactory1;
	
	@Override
	public boolean create(Users o) {
		try {	
			sessionFactory1.getCurrentSession().save(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Users o) {
		try {	
			sessionFactory1.getCurrentSession().update(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Users o) {
		try {	
			sessionFactory1.getCurrentSession().delete(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Users findById(int id) {
		return sessionFactory1.getCurrentSession().get(Users.class,id);
	}

	@Override
	public List<Users> findAll() {
		return sessionFactory1.getCurrentSession().createQuery("from Users").getResultList();
	}

}
