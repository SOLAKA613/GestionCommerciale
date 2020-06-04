package src.com.stock.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import src.com.stock.entities.Produits_Approvisionnement;

@Transactional("transactionManagerSecond")
@EnableTransactionManagement
@Repository("daoProduits_Approvisionnement")
public class DaoProduits_Approvisionnement implements IDao<Produits_Approvisionnement>{
	
	@Autowired
	@Qualifier(value="sessionFactoryTwo")
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().save(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().update(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		try {	
			sessionFactory.getCurrentSession().delete(o);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Produits_Approvisionnement findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Produits_Approvisionnement.class,id);
	}

	@Override
	public List<Produits_Approvisionnement> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Produits_Approvisionnement").getResultList();
	}
	
	

}
