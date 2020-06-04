package src.com.stock.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import src.com.stock.entities.Produits_Stock;
import src.com.stock.metiers.IMetier;

@ManagedBean
@Component
public class BeanProduits_Stock {

	@Autowired
	IMetier<Produits_Stock> metierProduits_Stock;
	
	public List<Produits_Stock> getListProduits_Stock() {
		return metierProduits_Stock.findAll();
	}

}
