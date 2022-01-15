package blue.macroLab.spectrometer;

import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.view.Dashboard;
import blue.macroLab.spectrometer.view.MyScreen;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        	 String appTitle = "JSpectrometer";
        	 Data data = new Data(appTitle);
        	 MyScreen screen = new MyScreen(data);
             data.setScreen(screen);
     	     Dashboard dash = new Dashboard(data);
     	    
     	     
     	     
    }
}
