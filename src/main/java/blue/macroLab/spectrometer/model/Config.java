package blue.macroLab.spectrometer.model;

import java.io.File;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.utils.Utility;


public class Config {
	private static final String JSPECTROMETER_PROPERTIES = "jspectrometer.properties";
	private Configurations configs;
	private Configuration config;
	
	
	 private String dataFile;
	 private String fileName;
	 private String wavelengthUnit;
	 private String chartTitle;
	 private String language;
	 private Data data;
	 
	 public Config(Data m) {
		 this.data = m;
		 this.configs = new Configurations();
		 this.reload();
		 
	 }
	 
	 public void reload() {
		 loadDefaults();
		 if(Utility.fileExists(JSPECTROMETER_PROPERTIES)) {
			 createConfiguration();
			 readConfigFromFile();
			}
		 this.data.getChartSettings().setChartTitle(chartTitle);
		 this.data.getChartSettings().setDataFile(dataFile);
		 this.data.getChartSettings().setFileName(fileName);
		 this.data.getChartSettings().setWavelengthUnit(wavelengthUnit);
		 this.data.getChartSettings().setColorAbsorbance("ROYALBLUE");
		 this.data.getChartSettings().setColorTransmission("FOREST_GREEN");
		 this.data.setMessages(language);
		 this.data.getMydata().setFileData(dataFile);
		 
	 }
	 
	 private void createConfiguration() {
		 try
		 {
		     this.config = this.configs.properties(new File(JSPECTROMETER_PROPERTIES));
		 }
		 catch (ConfigurationException cex)
		 {
			 this.data.getLogger().addLog("Could not read in the configuration file.");
	         this.data.getLogger().addLog(cex.getMessage());
			 
		 } 
	 }
	 
	 public void loadDefaults() {
		 dataFile = "data.dat";
		 fileName = "export-chart.eps";
		 wavelengthUnit = "nm";
		 chartTitle = "Spectrum of a Compound";
		 language = "en";
	 }
	 
	 public void readConfigFromFile() {
		 dataFile =  validate(config.getString("data.file"),dataFile); 
		 fileName = validate(config.getString("chart.file"),fileName);
		 wavelengthUnit = validate(config.getString("wavelength.unit"),wavelengthUnit);
		 chartTitle = validate(config.getString("chart.title"),chartTitle);  
		 language = validate(config.getString("language"),language);
	 }
	 
	 	private String validate(String input, String defaultValue) {
	 		if (input.isBlank()) {
	 			return defaultValue;
	 		} else {
	 			return input;
	 		}
	 	}
	 	
	 	private double validate(double input, double defaultValue) {
	 		if (input <= 0) {
	 			return defaultValue;
	 		} else {
	 			return input;
	 		}
	 	}
}