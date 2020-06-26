package src.com.vente.entities;

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
@Table(name = "produits_prix")
public class Produits_Prix {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codePdt;
	
	@Column
	private String nomPdt;
	
	@Column
	private String descPdt;
	
	@Column
	private int prixPdt;

	@OneToMany(mappedBy = "produits_prix",fetch = FetchType.LAZY)
	private List<Commandes> commandes;
	
	public Produits_Prix() {
		super();
	}

	public Produits_Prix(int codePdt, String nomPdt, String descPdt, int prixPdt) {
		super();
		this.codePdt = codePdt;
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

	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
	}

	@Override
	public String toString() {
		return "Produits_Prix [codePdt=" + codePdt + ", nomPdt=" + nomPdt + ", descPdt=" + descPdt + ", prixPdt="
				+ prixPdt +"]";
	}

	
}