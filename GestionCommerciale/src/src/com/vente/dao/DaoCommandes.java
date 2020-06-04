package src.com.vente.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import src.com.vente.entities.Commandes;


@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository("daoCommandes")
public class DaoCommandes implements IDao<Commandes>{

	@Autowired
	@Qualifier(value="sessionFactoryOne")
	SessionFactory sessionFactory1;
	
	@Override
	public boolean create(Commandes o) {
		try {	
			sessionFactory1.getCurrentSession().save(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Commandes o) {
		try {	
			sessionFactory1.getCurrentSession().update(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Commandes o) {
		try {	
			sessionFactory1.getCurrentSession().delete(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Commandes findById(int id) {
		return sessionFactory1.getCurrentSession().get(Commandes.class,id);
	}

	@Override
	public List<Commandes> findAll() {
		return sessionFactory1.getCurrentSession().createQuery("from Commandes").getResultList();
	}
}
