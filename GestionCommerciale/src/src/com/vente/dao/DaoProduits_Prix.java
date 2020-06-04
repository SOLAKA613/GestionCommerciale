package src.com.vente.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import src.com.vente.entities.Produits_Prix;


@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository("daoProduits_Prix")
public class DaoProduits_Prix implements IDao<Produits_Prix>{

	@Autowired
	@Qualifier(value="sessionFactoryOne")
	SessionFactory sessionFactory1;
	
	@Override
	public boolean create(Produits_Prix o) {
		try {
			sessionFactory1.getCurrentSession().save(o);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Produits_Prix o) {
		try {
			sessionFactory1.getCurrentSession().update(o);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Produits_Prix o) {
		try {
			sessionFactory1.getCurrentSession().delete(o);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Produits_Prix findById(int id) {
		return sessionFactory1.getCurrentSession().get(Produits_Prix.class,id);
	}

	@Override
	public List<Produits_Prix> findAll() {
		return sessionFactory1.getCurrentSession().createQuery("from Produits_Prix").getResultList();
	}

}
