package src.com.vente.entities;

public class AjouterCommande {
	
	private int code;
	private String nom;
	private int prix;
	private int quantite;
	private int total;
	
	
	
	public AjouterCommande() {
		super();
	}
	
	public AjouterCommande(int code, String nom, int prix, int quantite, int total) {
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.total = total;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
