package src.com.stock.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import src.com.stock.entities.Produits_Stock;

@Transactional("transactionManagerSecond")
@EnableTransactionManagement
@Repository("daoProduits_Stock")
public class DaoProduits_Stock implements IDao<Produits_Stock>{

	@Autowired
	@Qualifier(value="sessionFactoryTwo")
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Produits_Stock o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().save(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Produits_Stock o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().update(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Produits_Stock o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().delete(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Produits_Stock findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Produits_Stock.class,id);
	}

	@Override
	public List<Produits_Stock> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Produits_Stock").getResultList();
	}
		
}
