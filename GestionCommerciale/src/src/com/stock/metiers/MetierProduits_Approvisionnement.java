package src.com.stock.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.com.stock.dao.IDao;
import src.com.stock.entities.Produits_Approvisionnement;

@Service("metierProduits_Approvisionnement")
public class MetierProduits_Approvisionnement implements IMetier<Produits_Approvisionnement>{

	@Autowired	
	IDao<Produits_Approvisionnement> daoProduits_Approvisionnement;

	@Override
	public boolean create(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		return daoProduits_Approvisionnement.create(o);
	}

	@Override
	public boolean update(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		return daoProduits_Approvisionnement.update(o);
	}

	@Override
	public boolean delete(Produits_Approvisionnement o) {
		// TODO Auto-generated method stub
		return daoProduits_Approvisionnement.delete(o);
	}

	@Override
	public Produits_Approvisionnement findById(int id) {
		// TODO Auto-generated method stub
		return daoProduits_Approvisionnement.findById(id);
	}

	@Override
	public List<Produits_Approvisionnement> findAll() {
		// TODO Auto-generated method stub
		return daoProduits_Approvisionnement.findAll();
	}
	
	
	
}
