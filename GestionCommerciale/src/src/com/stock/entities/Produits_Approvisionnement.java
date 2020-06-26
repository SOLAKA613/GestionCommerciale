package src.com.stock.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "produits_approvisionnement")
public class Produits_Approvisionnement {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codePdtApp;
	
	@Column
	private int qteCommandé;
	
	@Temporal(TemporalType.DATE)
	private Date datePrevueLivraison; 
	
	@ManyToOne
	@JoinColumn(name="codePdt")
	private Produits_Stock produits_stock;

	
	
	public Produits_Approvisionnement() {
		super();
	}
	
	

	public Produits_Approvisionnement(Produits_Stock produits_stock) {
		super();
		this.produits_stock = produits_stock;
	}
	
	
	public Produits_Approvisionnement(int codePdtApp, int qteCommandé, Date datePrevueLivraison,Produits_Stock produits_stock) {
		super();
		this.codePdtApp=codePdtApp;
		this.qteCommandé = qteCommandé;
		this.datePrevueLivraison = datePrevueLivraison;
		this.produits_stock = produits_stock;
	}
	
	
	public int getCodePdtApp() {
		return codePdtApp;
	}
	public void setCodePdtApp(int codePdtApp) {
		this.codePdtApp = codePdtApp;
	}

	public int getQteCommandé() {
		return qteCommandé;
	}
	public void setQteCommandé(int qteCommandé) {
		this.qteCommandé = qteCommandé;
	}

	public Date getDatePrevueLivraison() {
		return datePrevueLivraison;
	}
	public void setDatePrevueLivraison(Date datePrevueLivraison) {
		this.datePrevueLivraison = datePrevueLivraison;
	}

	public Produits_Stock getProduits_stock() {
		return produits_stock;
	}
	public void setProduits_stock(Produits_Stock produits_stock) {
		this.produits_stock = produits_stock;
	}
	
	
}
