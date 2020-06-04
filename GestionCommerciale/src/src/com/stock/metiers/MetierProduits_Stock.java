package src.com.stock.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.com.stock.dao.IDao;
import src.com.stock.entities.Produits_Stock;;

@Service("metierProduits_Stock")
public class MetierProduits_Stock implements IMetier<Produits_Stock>{

	@Autowired	
	IDao<Produits_Stock> daoProduits_Stock;

	@Override
	public boolean create(Produits_Stock o) {
		// TODO Auto-generated method stub
		return daoProduits_Stock.create(o);
	}

	@Override
	public boolean update(Produits_Stock o) {
		// TODO Auto-generated method stub
		return daoProduits_Stock.update(o);
	}

	@Override
	public boolean delete(Produits_Stock o) {
		// TODO Auto-generated method stub
		return daoProduits_Stock.delete(o);
	}

	@Override
	public Produits_Stock findById(int id) {
		// TODO Auto-generated method stub
		return daoProduits_Stock.findById(id);
	}

	@Override
	public List<Produits_Stock> findAll() {
		// TODO Auto-generated method stub
		return daoProduits_Stock.findAll();
	}
}

