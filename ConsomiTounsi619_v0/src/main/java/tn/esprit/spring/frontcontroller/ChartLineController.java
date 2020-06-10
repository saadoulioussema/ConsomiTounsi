package tn.esprit.spring.frontcontroller;


import java.util.Map;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.PieChartModel;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.ItemSelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.sevice.interfece.IPanierService;


@Scope(value = "session")
@Controller(value = "ChartLineController")
@ELBeanName(value = "ChartLineController")
@Join(path = "/chart", to = "/chartLine.jsf")
public class ChartLineController {

	
	
	@Autowired
	private IPanierService panierService;
    private PieChartModel pieChartModel;
    private Map<String,Integer> mystatistic;
	
    
    
    
    public PieChartModel getPieChartModel() {
    	mystatistic = panierService.getAllProdLinesOfValidChart();
    	pieChartModel = new PieChartModel();
    	for(String key : mystatistic.keySet()) {
    		pieChartModel.addLabel(key);
        }
    	for(int x : mystatistic.values()) {
    		pieChartModel.set(x);
        }
    	pieChartModel.setAnimateAdvanced(true);
    	
		return pieChartModel;
	}
	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}
	public Map<String, Integer> getMystatistic() {
		return mystatistic;
	}
	public void setMystatistic(Map<String, Integer> mystatistic) {
		this.mystatistic = mystatistic;
	}
	
/* for(String key : mystat.keySet()) {
	        	lineChartModel.addLabel("aaaa");
	        }
	        
	       
	        for(int x : mystat.values()) {
	        	lineChartSeries1.set(12);
	        }*/
	        
	public void pieItemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
                + pieChartModel.getData().get(event.getItemIndex()));

        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    


}
