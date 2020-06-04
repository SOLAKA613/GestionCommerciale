package src.com.stock_vente.entities;

import src.com.stock.entities.Produits_Stock;
import src.com.vente.entities.Produits_Prix;

public class Produits_StockVente {

	private Produits_Stock produitStock;
	private Produits_Prix produitsPrix;
	
	public Produits_StockVente() {
		super();
	}
	public Produits_StockVente(Produits_Stock produitStock, Produits_Prix produitsPrix) {
		super();
		this.produitStock = produitStock;
		this.produitsPrix = produitsPrix;
	}
	public Produits_Stock getProduitStock() {
		return produitStock;
	}
	public void setProduitStock(Produits_Stock produitStock) {
		this.produitStock = produitStock;
	}
	public Produits_Prix getProduitsPrix() {
		return produitsPrix;
	}
	public void setProduitsPrix(Produits_Prix produitsPrix) {
		this.produitsPrix = produitsPrix;
	}
	
}
