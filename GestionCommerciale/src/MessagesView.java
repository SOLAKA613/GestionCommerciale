
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import src.com.vente.entities.Produits_Prix;
import src.com.vente.metiers.IMetier;
import src.com.vente.metiers.MetierProduits_Prix;

@ManagedBean
@Component
@RequestScoped
public class MessagesView implements Serializable{
     
	private String text;
	
	@Autowired
	@Qualifier(value="metierProduits_Prix")
	MetierProduits_Prix metierProduits_Prix=new MetierProduits_Prix();
	
	private BarChartModel barModel;
	
	 @PostConstruct
	    public void init() {
	        createBarModel();   
	    }
	 
	 public List<Produits_Prix> getListProduits_Prix() {
			return metierProduits_Prix.findAll();
		}
	 
	 
	  public HashMap<String, Integer> dataChart() {
			
			List<Produits_Prix> prds = getListProduits_Prix();
			HashMap<String, Integer> data = new HashMap<String, Integer>();
			
			// Initialisation
			for(Produits_Prix s : prds)
				data.put(s.getNomPdt(), 0);
			
			// Remplissage
			for(Produits_Prix p : prds)
				data.put(p.getNomPdt(), data.get(p.getNomPdt()) + 1);
			
			return data;
		}
	 
	 public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();
	         
	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel("My First Dataset");
	         
	        List<Number> values = new ArrayList<Number>();
	        List<String> labels = new ArrayList<String>();
	        
	        for(Entry<String, Integer> val:dataChart().entrySet()) {
	        	
	        values.add(val.getValue());
	        labels.add(val.getKey());
	        }
	        barDataSet.setData(values);
	         
	        List<String> bgColor = new ArrayList<String>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(255, 159, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        bgColor.add("rgba(54, 162, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(201, 203, 207, 0.2)");
	        barDataSet.setBackgroundColor(bgColor);
	         
	        List<String> borderColor = new ArrayList<String>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");
	        barDataSet.setBorderColor(borderColor);
	        barDataSet.setBorderWidth(1);
	         
	        data.addChartDataSet(barDataSet);
	         
	       
	        data.setLabels(labels);
	        barModel.setData(data);
	         
	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addYAxesData(linearAxes);
	        options.setScales(cScales);
	         
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Bar Chart");
	        options.setTitle(title);
	 
	        Legend legend = new Legend();
	        legend.setDisplay(true);
	        legend.setPosition("top");
	        LegendLabel legendLabels = new LegendLabel();
	        legendLabels.setFontStyle("bold");
	        legendLabels.setFontColor("#2980B9");
	        legendLabels.setFontSize(24);
	        legend.setLabels(legendLabels);
	        options.setLegend(legend);
	 
	        barModel.setOptions(options);
	    }
	
    public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String save() {
        addMessage("Data saved");
        return null;
    }
 
    public void update() {
        addMessage("Data updated");
    }
 
    public void delete() {
        addMessage("Data deleted");
    }
 
    public void buttonAction() {
        addMessage("Welcome to PrimeFaces!!");
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
    public void viewCarsCustomized() {
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
        
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
	
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }
     
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }
     
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }
    
}