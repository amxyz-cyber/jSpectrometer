package blue.macroLab.spectrometer.model;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.settings.ChartSettings;
import blue.macroLab.spectrometer.spectrum.MyData;
import blue.macroLab.spectrometer.view.MyScreen;

public class Data {
	private String appTitle;
	private MyMessage logger;
	private Locale deLocale;
	private Locale gbLocale;
	private ResourceBundle messages;
	private MyScreen myscreen;
	private ChartSettings csettings;
	private Config config;
	private MyData mydata;
	
	public Data(String title) {
	      this.appTitle = title;
	      setLogger();
	      setMydata();
		  this.deLocale = new Locale.Builder().setLanguage("de").setRegion("DE").build();
		  this.gbLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
		  setChartSettings();
		  setConfig();
		  
		}

	public Config getConfig() {
		return this.config;
	}

	public void setConfig(){
		this.config = new Config(this);
	}
	
	public String getTitle() {
		return this.appTitle;
	}
	
	public MyMessage getLogger() {
		return logger;
	}

	public void setLogger() {
		this.logger = new MyMessage();
	}

	public Locale getLocale() {
		return deLocale;
	}

	/*public void setLocale(Locale deLocale) {
		this.deLocale = deLocale;
	}*/
	
	public ResourceBundle getMessages() {
		 return this.messages;
	}
	
	public void setMessages(String shortLanguage){
		  try {
			  String s = shortLanguage.strip().toLowerCase();
			  if (s.equals(deLocale.getLanguage() )) {
				  this.messages = ResourceBundle.getBundle(Constants.MESSAGE_PATH, deLocale);
			  } else {
				  this.messages = ResourceBundle.getBundle(Constants.MESSAGE_PATH, gbLocale);
			  }
			  
		  } catch (MissingResourceException e) {
			  this.logger.setLog("Error while setting up language. "+e.getMessage());
		  }
		}
	
    public String getMessageValue(String key){
			return this.messages.getString(key);
	}

    public void setScreen(MyScreen s) {
		this.myscreen = s;
	}
	
	public MyScreen getScreen() {
		return this.myscreen;
	}

	public Locale getGbLocale() {
		return gbLocale;
	}

	public ChartSettings getChartSettings() {
		return csettings;
	}

	public void setChartSettings() {
		this.csettings = new ChartSettings(this);
	}

	public MyData getMydata() {
		return mydata;
	}

	public void setMydata() {
		this.mydata = new MyData(this);
	}

}
