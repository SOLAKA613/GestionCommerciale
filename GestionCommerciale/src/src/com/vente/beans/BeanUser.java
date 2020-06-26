package src.com.vente.beans;

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
	 
	    Users newUser = new Users();
	    
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
           if(login != null && login.equals(p.getLogin()) && login != null && pass.equals(p.getPass())) {
           loggedIn = true;
           int id=p.getCodeUser();
           ec.redirect(ec.getRequestContextPath()
   	            + "/faces/listProduits.xhtml");
           FacesContext context2 = FacesContext.getCurrentInstance();
           HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
           session.setAttribute("login", login);
           session.setAttribute("pass", pass);
           session.setAttribute("id", id);
           
       } 
       else {
           loggedIn = false;
           message = new FacesMessage(FacesMessage.SEVERITY_WARN, " Erreur : username ou password incorrect !", "");
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
		 List<Users> listUsers = metierUsers.findAll();
		 
		 FacesMessage message = null;
      for(Users p:listUsers) {
        	
       if(pass != null && pass.equals(p.getPass()) && pass2 != null && pass2.equals(pass3)) {
           
           p.setPass(pass2);
           metierUsers.update(p);
           
           message = new FacesMessage( " Mot de pass est modifier avec succes");
       } 
       else {
           
           
           message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Mot de passe incorrect", "");
       }
        
        }
      FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	
	
	public void RecupererPass() {
		 List<Users> listUsers = metierUsers.findAll();
		 
		 FacesMessage message = null;
     for(Users p:listUsers) {
       	
      if(email != null && email.equals(p.getEmail())) {
          
         String pssword = p.getPass();
         String firstmane = p.getFirstname();
         
          message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bonjour` "+firstmane+" Votre mot de passe est "+ pssword,"");
      } 
      else {
          
          
          message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error : Votre email incorrect","");
      }
       
       }
     FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
    public void save() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		    metierUsers.create(newUser);
			context.addMessage(null, new FacesMessage("Successful Client ajoute avec succes.") );
				
			
	}
    
    public String logout(){
	   FacesContext context = FacesContext.getCurrentInstance();
	   ExternalContext ec = context.getExternalContext();
	   final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	   request.getSession(false).invalidate();
            return "Login";
	}

}