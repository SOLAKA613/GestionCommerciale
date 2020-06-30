package src.com.vente.beans;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import src.com.stock.entities.Produits_Stock;
import src.com.stock.entities.Produits_Approvisionnement;
import src.com.stock.metiers.MetierProduits_Stock;
import src.com.stock.metiers.IMetier;
import src.com.stock.metiers.MetierProduits_Approvisionnement;
import src.com.stock_vente.entities.Produits_StockVente;
import src.com.vente.entities.Produits_Prix;
import src.com.vente.entities.Users;
import src.com.vente.entities.AjouterCommande;
import src.com.vente.entities.Commandes;
import src.com.vente.metiers.MetierCommandes;
import src.com.vente.metiers.MetierProduits_Prix;
import src.com.vente.metiers.MetierUsers;

@ManagedBean
@Component
@RequestScoped
public class BeanProduits {

	@Autowired
	@Qualifier(value="metierProduits_Stock")
	IMetier<Produits_Stock> metierProduits_Stock = new MetierProduits_Stock();
	
	@Autowired
	@Qualifier(value="metierProduits_Prix")
	MetierProduits_Prix metierProduits_Prix = new MetierProduits_Prix();
	
	@Autowired
	@Qualifier(value="metierCommandes")
    MetierCommandes metierCommandes = new MetierCommandes();
	
	@Autowired
	@Qualifier(value="metierUsers")
	MetierUsers metierUsers = new MetierUsers();
	
	@Autowired
	@Qualifier(value="metierProduits_Approvisionnement")
	MetierProduits_Approvisionnement metierProduits_Approvisionnement = new MetierProduits_Approvisionnement();
	
	private List<Produits_StockVente> selectedProduits;
	private List<Produits_StockVente> filteredProduits;;
	private Map<Commandes,Produits_Approvisionnement> cmdes;
	private Map<Produits_Stock,Integer> qtePdtStock;
	private int[] qte1=new int[12];
	private String username;
	private String password;
	private List<AjouterCommande> AddCommande;
	
	//Pour Afficher la tableau des produits  
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

	public List<Produits_StockVente> getSelectedProduits() {
		return selectedProduits;
	}
	public void setSelectedProduits(List<Produits_StockVente> selectedProduits) {
		this.selectedProduits = selectedProduits;
	}

	
	public List<Produits_StockVente> getFilteredProduits() {
		return filteredProduits;
	}
	public void setFilteredProduits(List<Produits_StockVente> filteredProduits) {
		this.filteredProduits = filteredProduits;
	}

	public Map<Commandes, Produits_Approvisionnement> getCmdes() {
		return cmdes;
	}
	public void setCmdes(Map<Commandes, Produits_Approvisionnement> cmdes) {
		this.cmdes = cmdes;
	}
	
	public Map<Produits_Stock, Integer> getQtePdtStock() {
		return qtePdtStock;
	}
	public void setQtePdtStock(Map<Produits_Stock, Integer> qtePdtStock) {
		this.qtePdtStock = qtePdtStock;
	}

	public int[] getQte1() {
		return qte1;
	}
	public void setQte1(int[] qte1) {
		this.qte1 = qte1;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<AjouterCommande> getAddCommande() {
		return AddCommande;
	}
	public void setAddCommande(List<AjouterCommande> addCommande) {
		AddCommande = addCommande;
	}

	public void buttonAction() {
		     if(selectedProduits.size()==0) {
	        	addMessage("Aucun produit sélectionné!!");	
	        	PrimeFaces.current().executeScript("PF('multiProduitDialog').hide()");
		     }
		     if(selectedProduits.size()>0 && selectedProduits.size()<13){
		    	PrimeFaces.current().executeScript("PF('multiProduitDialog').show()"); 
		     }  
		     if(selectedProduits.size()>13) {
	        	   addMessage("Vous pouvez commander au maximum 12 produits à la fois");	
	        	   PrimeFaces.current().executeScript("PF('multiProduitDialog').hide()");
		     }
		     
	}
	
	//Pour remplir les commandes dans la table Commandes  
	public void commander() {
		  
		  Map<Commandes,Produits_Approvisionnement> hashcmde = new  HashMap<Commandes,Produits_Approvisionnement>();
		  Map<Produits_Stock,Integer> pdtStock=new HashMap<Produits_Stock,Integer>();
		  FacesContext context2 = FacesContext.getCurrentInstance();
	      HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
	      int id =(Integer) session.getAttribute("id");
	      Users user= metierUsers.findById(id);
	      String name= user.getFirstname() +" "+ user.getLastname();
		  int i=0;
		  
		  for(Produits_StockVente ps:selectedProduits) {

			  hashcmde.put(new Commandes(0,name,qte1[i],java.sql.Date.valueOf(LocalDate.now()),ps.getProduitsPrix()),
				new Produits_Approvisionnement(0,qte1[i],java.sql.Date.valueOf(LocalDate.now().plusMonths(1)),ps.getProduitStock()));
			  pdtStock.put(ps.getProduitStock(), qte1[i]);
			  i++;
		  }
		  
		  setQtePdtStock(pdtStock);
		  setCmdes(hashcmde);
		  PrimeFaces.current().executeScript("PF('dlg').show()");
	}
	
    //Pour Enregistrer les commandes
	public void Enregistrer() throws FileNotFoundException, JRException {
		int j=0,k=0;
		List<AjouterCommande> AjComm = new ArrayList<AjouterCommande>();
		FacesContext context2 = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
        String pass=(String) session.getAttribute("pass");
        String login1=(String) session.getAttribute("login");
        String nomUser=username,passUser=password;
        
		setPassword(null);//Pour vider le password,pour ne pas le pasword afficher une autre fois dans input
		setUsername(null);//Pour vider le username,pour ne pas afficher le username une autre fois dans input
		
		for(Users us:metierUsers.findAll()) {
			
			if(nomUser.toLowerCase().equals(us.getLogin().toLowerCase()) && passUser.equals(us.getPass())) {
			   k=1;
			   break;
			}
		}	
		   if(k==1) {
			   
			  if(nomUser.toLowerCase().equals(login1.toLowerCase()) && passUser.equals(pass)) {
				
				for (Map.Entry<Commandes, Produits_Approvisionnement> pair: getCmdes().entrySet()) {
					
					if(metierCommandes.create(pair.getKey()) && metierProduits_Approvisionnement.create(pair.getValue())) {
						
						AjComm.add(new AjouterCommande(pair.getKey().getProduits_prix().getCodePdt(),pair.getKey().getProduits_prix().getNomPdt(),
						pair.getKey().getProduits_prix().getPrixPdt(),pair.getKey().getQteCmd(),pair.getKey().getQteCmd()*pair.getKey().getProduits_prix().getPrixPdt()));
						continue;
						
					}else {
						for (Map.Entry<Commandes, Produits_Approvisionnement> pair1: getCmdes().entrySet()) {
							metierCommandes.delete(pair1.getKey());
							metierProduits_Approvisionnement.delete(pair1.getValue());
						};
						j=1;
						addMessage("Erreur.L'opération n'est pas effectué avec succès.");
						break;
					}
				}
				if(j == 0) {
					for (Map.Entry<Produits_Stock, Integer> pairStock: getQtePdtStock().entrySet()) {
						
						Produits_Stock pp1=pairStock.getKey();
						Produits_Stock pp=new Produits_Stock(pp1.getCodePdt(),pp1.getQtePdt()-pairStock.getValue(),pp1.getNomPdt(),pp1.getDescPdt(),pp1.getPrixPdt());
						
						if(metierProduits_Stock.update(pp)) {
							continue;
						}else {
							for (Map.Entry<Produits_Stock, Integer> pairStock1: getQtePdtStock().entrySet()) {
								metierProduits_Stock.update(pairStock1.getKey());
							}
							addMessage("Erreur.L'opération n'est pas effectué avec succès.");
							j=1;
							break;
						}
					}
					
				}
				if(j == 0) {
					setAddCommande(AjComm);
				}
				
				addMessageSucces("Votre opération s'est terminée avec succès.Vos articles vous parviendra dans 30 jours");
				
			  }else {
				  addMessage(".Le mot de passe et le nom d'utilisateur que vous avez entrés ne correspondent pas au mot de passe et au nom d'utilisateur avec lesquels vous vous connectez.");
			  }	
			}else {
				addMessage("Votre password ou username est incorrect");
			}		
				
	}
	
	//Pour téléchrger le fichier PDF avec l'utilisation de la technologie Jasper Report 
	public void DownloadFile() throws JRException, IOException {		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(getAddCommande());
	    String jrXmlFile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/Produits.jrxml");
	    InputStream  input = new FileInputStream(new File(jrXmlFile));
	    JasperReport jasperReport = JasperCompileManager.compileReport(input);
	    Map<String,Object> params = new HashMap<String,Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params, jrDataSource);
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    response.addHeader("content-disposition","attachement; filename=commande.pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	    FacesContext.getCurrentInstance().responseComplete();
	}
	
	//Afficher message succes
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	//Afficher message erreur
	public void addMessageSucces(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
