package src.com.stock.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "produits_stock")
public class Produits_Stock {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codePdt;
	
	@Column
	private int qtePdt;
	
	@Column
	private String nomPdt;
	
	@Column
	private String descPdt;
	
	@Column
	private int prixPdt;
	
	@OneToMany(mappedBy = "produits_stock",fetch = FetchType.LAZY)
	private List<Produits_Approvisionnement> produits_approvisionnement;

	
	
	public Produits_Stock() {
		super();
	}  

	public Produits_Stock(int codePdt, int qtePdt, String nomPdt, String descPdt, int prixPdt) {
		super();
		this.codePdt = codePdt;
		this.qtePdt = qtePdt;
		this.nomPdt = nomPdt;
		this.descPdt = descPdt;
		this.prixPdt = prixPdt;
	}



	public int getCodePdt() {
		return codePdt;
	}

	public void setCodePdt(int codePdt) {
		this.codePdt = codePdt;
	}

	public int getQtePdt() {
		return qtePdt;
	}

	public void setQtePdt(int qtePdt) {
		this.qtePdt = qtePdt;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	public String getDescPdt() {
		return descPdt;
	}

	public void setDescPdt(String descPdt) {
		this.descPdt = descPdt;
	}

	public int getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}

	public List<Produits_Approvisionnement> getProduits_approvisionnement() {
		return produits_approvisionnement;
	}

	public void setProduits_approvisionnement(List<Produits_Approvisionnement> produits_approvisionnement) {
		this.produits_approvisionnement = produits_approvisionnement;
	}
	
	
	
}
