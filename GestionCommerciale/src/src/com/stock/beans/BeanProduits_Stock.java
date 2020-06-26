package src.com.stock.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import src.com.stock.entities.Produits_Stock;
import src.com.stock.metiers.IMetier;


@ManagedBean
@Component
public class BeanProduits_Stock {

	@Autowired
	IMetier<Produits_Stock> metierProduits_Stock;
	
	private BarChartModel barModel;
	private DonutChartModel donutModel;
	
	 @PostConstruct
	    public void init() {
	        createBarModel(); 
	        createDonutModel();
	    }
	 
	 

	  public HashMap<String, Integer> dataChart() {
			
			List<Produits_Stock> prds = getListProduits_Stock();
			HashMap<String, Integer> data = new HashMap<String, Integer>();
			
			
			// Remplissage
			for(Produits_Stock p : prds)
				data.put(p.getNomPdt(), p.getQtePdt());
			
			return data;
		}
	 
	 public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();
	        Random rand = new Random();
	        int i=0,j=0,k=0;
	        
	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel("My First Dataset");
	         
	        List<Number> values = new ArrayList<Number>();
	        List<String> labels = new ArrayList<String>();
	        List<String> bgColor = new ArrayList<String>();
	        List<String> borderColor = new ArrayList<String>();
	        
	        for(Entry<String, Integer> val:dataChart().entrySet()) {	
	        values.add(val.getValue());
	        labels.add(val.getKey());
	        
	        i=rand.nextInt(256);
	        j=rand.nextInt(256);
	        k=rand.nextInt(256);
	        
	        bgColor.add("rgba("+i+",+"+j+","+k+",0.2)");
	        borderColor.add("rgb("+i+","+j+","+k+")");
	     
	        }
	        barDataSet.setData(values);
	         
	        
	        barDataSet.setBackgroundColor(bgColor);
	         	        
	     
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
	        legendLabels.setFontSize(34);
	        legend.setLabels(legendLabels);
	        options.setLegend(legend);
	 
	        barModel.setOptions(options);
	    }
	 
	 public void createDonutModel() {
	        donutModel = new DonutChartModel();
	        ChartData data = new ChartData();
	        Random rand = new Random();
	        int i=0,j=0,k=0;
	         
	        DonutChartDataSet dataSet = new DonutChartDataSet();
	        List<Number> values = new ArrayList<Number>();
	        List<String> labels = new ArrayList<String>();
	        List<String> bgColors = new ArrayList<String>();
	        
	        for(Entry<String, Integer> val:dataChart().entrySet()) {
	        	
	        	i=rand.nextInt(256);
	 	        j=rand.nextInt(256);
	 	        k=rand.nextInt(256);
	        	
	 	        bgColors.add("rgb("+i+","+j+","+k+")");
	        	values.add(val.getValue());
	        	labels.add(val.getKey());
	        	
	        }	
	        dataSet.setData(values);
	        data.setLabels(labels);
	        donutModel.setData(data);
	        dataSet.setBackgroundColor(bgColors);
	         
	        data.addChartDataSet(dataSet);
	       
	        
	         
	       
	    }
	
    public BarChartModel getBarModel() {
		return barModel;
	}
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	
	public DonutChartModel getDonutModel() {
		return donutModel;
	}
	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}



	public List<Produits_Stock> getListProduits_Stock() {
		return metierProduits_Stock.findAll();
	}


}
