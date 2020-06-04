package src.com.vente.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.com.vente.entities.Commandes;
import src.com.vente.dao.IDao;

@Service("metierCommandes")
public class MetierCommandes implements IMetier<Commandes> {

	@Autowired
	IDao<Commandes> daoCommandes;
	
	@Override
	public boolean create(Commandes o) {
		// TODO Auto-generated method stub
		return daoCommandes.create(o);
	}

	@Override
	public boolean update(Commandes o) {
		// TODO Auto-generated method stub
		return daoCommandes.update(o);
	}

	@Override
	public boolean delete(Commandes o) {
		// TODO Auto-generated method stub
		return daoCommandes.delete(o);
	}

	@Override
	public Commandes findById(int id) {
		// TODO Auto-generated method stub
		return daoCommandes.findById(id);
	}

	@Override
	public List<Commandes> findAll() {
		// TODO Auto-generated method stub
		return daoCommandes.findAll();
	}

}
