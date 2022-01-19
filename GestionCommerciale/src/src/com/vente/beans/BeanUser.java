package src.com.vente.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import src.com.vente.entities.Users;

import src.com.vente.metiers.MetierUsers;



@ManagedBean
@Component
public class BeanUser {
	
	 @Autowired		
	 MetierUsers metierUsers = new MetierUsers();
	 
	 private Users newUser = new Users();    
	 private String firstname;
	 private String lastname ;
	 private String login;
	 private String email;
	 private String phone ; 
     private String pass;
     private String pass2;
     private String pass3;
    
    public String getPass3() {
		return pass3;
	}
	public void setPass3(String pass3) {
		this.pass3 = pass3;
	}

	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public MetierUsers getMetierUsers() {
		return metierUsers;
	}
	public void setMetierUsers(MetierUsers metierUsers) {
		this.metierUsers = metierUsers;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}



	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Users getNewUser() {
		return newUser;
	}
	public void setNewUser(Users newUser) {
		this.newUser = newUser;
	}
	
	public void login() {
		 
	   List<Users> listUsers = metierUsers.findAll();
       FacesMessage message = null;
       boolean loggedIn = false;
       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
       
        for(Users p:listUsers) {
        	try {
       if(login != null && login.toLowerCase().equals(p.getLogin().toLowerCase()) && login != null && pass.equals(p.getPass())) {
           loggedIn = true;
           int id=p.getCodeUser();
           ec.redirect(ec.getRequestContextPath()+ "/faces/listProduits.xhtml");
           FacesContext context2 = FacesContext.getCurrentInstance();
           HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
           session.setAttribute("login", login);
           session.setAttribute("pass", pass);
           session.setAttribute("id", id);
           
       } 
       else {
           loggedIn = false;
           message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erreur : username ou password incorrect !", "");
       }
        } catch (Exception e) {
    	    //TODO Auto-generated catch block
    	    e.printStackTrace();
    	} 
      
   }   
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);   
	}
	
	public void changePass() {
		 FacesContext context2 = FacesContext.getCurrentInstance();
         HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
		 List<Users> listUsers = metierUsers.findAll();
		 int j=0;
		 Users user=new Users();
		 String password=(String) session.getAttribute("pass");
      for(Users p:listUsers) {
        	
       if(pass != null && pass.equals(p.getPass()) && pass.equals(password)) {
          j=1;
          user=p;
          
          break;
       } 
         
      }
      if(j==1) {
        if(pass2 != null && pass3 != null && pass2.equals(pass3)) {
    	  user.setPass(pass2);
    	  session.setAttribute("pass", pass2);
          metierUsers.update(user);
          
          addMessageSucces( " Mot de passe est modifier avec succes");
   	    }else {         
    	  addMessage("Erreur: Les deux mots de passe ne sont pas compatible.");
        }
      }else{
    	  addMessage("Erreur: Mot de passe incorrect");
	   }
      
	}
	
	
	
	
	public void RecupererPass() {
		 List<Users> listUsers = metierUsers.findAll();
		 
		 FacesMessage message = null;
     for(Users p:listUsers) {
       	
      if(email != null && email.toLowerCase().equals(p.getEmail().toLowerCase())) {
          
         String pssword = p.getPass();
         String firstmane = p.getFirstname();
         
          message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bonjour "+firstmane+" Votre mot de passe est "+ pssword,"");
      } 
      else {
          
          
          message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : Votre email incorrect","");
      }
       
       }
     FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
    public void save() throws IOException {
    	List<Users> listUsers = metierUsers.findAll();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesContext context2 = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
		int id=0,j=0;
		
		for(Users p:listUsers) {
			if(p.getEmail().toLowerCase().equals(getNewUser().getEmail().toLowerCase())) {
				addMessage( "Error : Ce mail déjà existe .Entrer un nouveau émail différent");
			    j=1;
			    break;
			}
		}
		
		if(j==0 && metierUsers.create(newUser)) {			      
          for(Users user: metierUsers.findAll()) {
        	if(user.getLogin().equals(newUser.getLogin()) && user.getPass().equals(newUser.getPass())) {
        		id=user.getCodeUser();
        		break;
        	}
          }
          session.setAttribute("login", newUser.getLogin());
          session.setAttribute("pass", newUser.getPass());
          session.setAttribute("id", id);
          ec.redirect(ec.getRequestContextPath()+ "/faces/listProduits.xhtml");
		}
			
	}
    
    public void logout() throws IOException{
	   FacesContext context = FacesContext.getCurrentInstance();
	   HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	   ExternalContext ec = context.getExternalContext();
	   final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	   session.setAttribute("login", null);
	   ec.redirect(ec.getRequestContextPath()+ "/faces/Accueil.xhtml");
	}
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addMessageSucces(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}