package src.com.vente.entities;

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
@Table(name = "commandes")
public class Commandes {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeCmd;
	
	@Column
	private String client;
	
	@Column
	private int qteCmd;
	
	@Temporal(TemporalType.DATE)
	private Date dateCmd;

	@ManyToOne
	@JoinColumn(name="codePdt")
	private Produits_Prix produits_prix;

	
	public Commandes() {
		super();
	}
    
	public Commandes(Produits_Prix produits_prix) {
		super();
		this.produits_prix = produits_prix;
	}


	public Commandes(int codeCmd, String client, int qteCmd, Date dateCmd, Produits_Prix produits_prix) {
		super();
		this.codeCmd = codeCmd;
		this.client = client;
		this.qteCmd = qteCmd;
		this.dateCmd = dateCmd;
		this.produits_prix = produits_prix;
	}



	public int getCodeCmd() {
		return codeCmd;
	}

	public void setCodeCmd(int codeCmd) {
		this.codeCmd = codeCmd;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(int qteCmd) {
		this.qteCmd = qteCmd;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public Produits_Prix getProduits_prix() {
		return produits_prix;
	}

	public void setProduits_prix(Produits_Prix produits_prix) {
		this.produits_prix = produits_prix;
	}

	@Override
	public String toString() {
		return "Commandes [codeCmd=" + codeCmd + ", client=" + client + ", qteCmd=" + qteCmd + ", dateCmd=" + dateCmd
				+ "]";
	}
	
}	