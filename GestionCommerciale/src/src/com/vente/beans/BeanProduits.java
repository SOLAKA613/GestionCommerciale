package src.com.vente.beans;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import src.com.stock.entities.Produits_Stock;
import src.com.stock.metiers.MetierProduits_Stock;
import src.com.stock.metiers.IMetier;
import src.com.stock_vente.entities.Produits_StockVente;
import src.com.vente.entities.Produits_Prix;
import src.com.vente.metiers.MetierProduits_Prix;

@ManagedBean
@Component
public class BeanProduits {

	@Autowired
	@Qualifier(value="metierProduits_Stock")
	IMetier<Produits_Stock> metierProduits_Stock =new MetierProduits_Stock();
	
	@Autowired
	@Qualifier(value="metierProduits_Prix")
	MetierProduits_Prix metierProduits_Prix=new MetierProduits_Prix();
	
	public List<Produits_StockVente> getListProduits() {
		
		List<Produits_StockVente> listProduits=new ArrayList<Produits_StockVente>();
        List<Produits_Stock> listPdtStock= metierProduits_Stock.findAll();
        List<Produits_Prix> listPdtPrix= metierProduits_Prix.findAll();
		
		for(Produits_Stock prdStock: listPdtStock) {
			for(Produits_Prix prdPrix: listPdtPrix)  {
				if(prdStock.getCodePdt()==prdPrix.getCodePdt()) {
					
					listProduits.add(new Produits_StockVente(prdStock,prdPrix));
				}					
			}		
		}
		
		return listProduits;
	}	
	
	
}
