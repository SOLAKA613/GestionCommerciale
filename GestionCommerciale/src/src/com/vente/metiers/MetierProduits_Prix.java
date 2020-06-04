package src.com.vente.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.com.vente.dao.IDao;
import src.com.vente.entities.Produits_Prix;

@Service("metierProduits_Prix")
public class MetierProduits_Prix implements IMetier<Produits_Prix>{

	@Autowired	
	IDao<Produits_Prix> daoProduits_Prix;
	
	@Override
	public boolean create(Produits_Prix o) {
		// TODO Auto-generated method stub
		return daoProduits_Prix.create(o);
	}

	@Override
	public boolean update(Produits_Prix o) {
		// TODO Auto-generated method stub
		return daoProduits_Prix.update(o);
	}

	@Override
	public boolean delete(Produits_Prix o) {
		// TODO Auto-generated method stub
		return daoProduits_Prix.delete(o);
	}

	@Override
	public Produits_Prix findById(int id) {
		// TODO Auto-generated method stub
		return daoProduits_Prix.findById(id);
	}

	@Override
	public List<Produits_Prix> findAll() {
		// TODO Auto-generated method stub
		return daoProduits_Prix.findAll();
	}

	
}
