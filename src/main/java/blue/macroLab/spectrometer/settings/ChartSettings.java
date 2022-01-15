package blue.macroLab.spectrometer.settings;

import java.io.File;
import java.util.LinkedList;

import com.panayotis.gnuplot.style.NamedPlotColor;

import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.utils.Utility;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChartSettings {
	private String _dataFile = "";
	private String _fileName = "";
	private String _wavelengthUnit = "";
	private String _chartTitle = "";
	private String _folder = "";
	private String _path = "";
	private String _colorAbsorbance = "";
	private String _colorTransmission = "";
	private NamedPlotColor colorA;
	private NamedPlotColor colorT;
	
	private SimpleStringProperty dataFile;
	private SimpleStringProperty fileName;
	private SimpleStringProperty wavelengthUnit; 
	private SimpleStringProperty chartTitle; 
	private SimpleStringProperty folder;
	private SimpleStringProperty path;
	private SimpleStringProperty colorAbsorbance;
	private SimpleStringProperty colorTransmission;
	
	private Data data;
	
	
	public ChartSettings(Data m)  {
		data = m;
	}

	public final String getColorAbsorbance() {
		if(colorAbsorbance == null) {
			return _colorAbsorbance;
		} else 
		return colorAbsorbance.get();
	}
	
	public final StringProperty colorAbsorbanceProperty() {
		if(colorAbsorbance == null) {
			colorAbsorbance = new SimpleStringProperty(this,"colorAbsorbance",_colorAbsorbance);
		}
		return colorAbsorbance; 
	}
	
	public final void setColorAbsorbance(String value) {
		if(colorAbsorbance == null) {
			_colorAbsorbance = value;
		} else {
			colorAbsorbance.set(value);
		}
	}
	
	public final String getColorTransmission() {
		if(colorTransmission == null) {
			return _colorTransmission;
		} else 
		return colorTransmission.get();
	}
	
	public final StringProperty colorTransmissionProperty() {
		if(colorTransmission == null) {
			colorTransmission = new SimpleStringProperty(this,"colorTransmission",_colorTransmission);
		}
		return colorTransmission; 
	}
	
	public final void setColorTransmission(String value) {
		if(colorTransmission == null) {
			_colorTransmission = value;
		} else {
			colorTransmission.set(value);
		}
	}
	
	
	public final String getFileName() {
		if(fileName == null) {
			return _fileName;
		} else 
		return fileName.get();
	}
	
	public final StringProperty fileNameProperty() {
		if(fileName == null) {
			fileName = new SimpleStringProperty(this,"fileName",_fileName);
		}
		return fileName; 
	}
	
	public final void setFileName(String value) {
		if(fileName == null) {
			_fileName = value;
		} else {
			fileName.set(value);
		}
	}
	
	public final String getFolder() {
		if(folder == null) {
			return _folder;
		} else 
		return folder.get();
	}
	
	public final StringProperty folderProperty() {
		if(folder == null) {
			folder = new SimpleStringProperty(this,"folder",_folder);
		}
		return folder; 
	}
	
	public final void setFolder(String value) {
		if(folder == null) {
			_folder = value;
		} else {
			folder.set(value);
		}
	}
	
	public final String getPath() {
		if(path == null) {
			return _path;
		} else 
		return path.get();
	}
	
	public final StringProperty pathProperty() {
		if(path == null) {
			path = new SimpleStringProperty(this,"path",_path);
		}
		return path; 
	}
	
	public final void setPath() {
		String value = "";
		if (!getFolder().isBlank() ) {
			value += getFolder();
			value += File.separator;
			value += getFileName();
		} else {
			value = getFileName();
		}
		  
		if(path == null) {
			_path = value;
		} else {
			path.set(value);
		}
	}
	
	public final String getDataFile() {
		if(dataFile == null) {
			return _dataFile;
		} else 
		return dataFile.get();
	}
	
	public final StringProperty dataFileProperty() {
		if(dataFile == null) {
			dataFile = new SimpleStringProperty(this,"dataFile",_dataFile);
		}
		return dataFile; 
	}
	
	public final void setDataFile(String value) {
		if(dataFile == null) {
			_dataFile = value;
		} else {
			dataFile.set(value);
		}
	}
	
	public final String getChartTitle() {
		if(chartTitle == null) {
			return _chartTitle;
		} else 
		return chartTitle.get();
	}
	
	public final StringProperty chartTitleProperty() {
		if(chartTitle == null) {
			chartTitle = new SimpleStringProperty(this,"chartTitle",_chartTitle);
		}
		return chartTitle; 
	}
	
	public final void setChartTitle(String value) {
		if(chartTitle == null) {
			_chartTitle = value;
		} else {
			chartTitle.set(value);
		}
	}
	
	public final String getWavelengthUnit() {
		if(wavelengthUnit == null) {
			return _wavelengthUnit;
		} else 
		return wavelengthUnit.get();
	}
	
	public final StringProperty wavelengthUnitProperty() {
		if(wavelengthUnit == null) {
			wavelengthUnit = new SimpleStringProperty(this,"wavelengthUnit",_wavelengthUnit);
		}
		return wavelengthUnit; 
	}
	
	public final void setWavelengthUnit(String value) {
		if(wavelengthUnit == null) {
			_wavelengthUnit = value;
		} else {
			wavelengthUnit.set(value);
		}
	}

	public NamedPlotColor getColorA() {
		return colorA;
	}
	
	public void setColorA(String color) {
		this.colorA = auxSetColor(color,this.getColorAbsorbance());
	}

	private NamedPlotColor auxSetColor(String colorName,String standardColor) {
		NamedPlotColor c = null;
		try {
		if (colorName.isEmpty()) {
			c = Utility.getPlotColor(standardColor);
		} else {
			c = Utility.getPlotColor(colorName);
		}
			
		}catch(Exception e) {
			this.data.getLogger().addLog(e.getMessage());
		}
		return c;
	}

	public NamedPlotColor getColorT() {
		return colorT;
	}

	public void setColorT(String color) {
		this.colorT = auxSetColor(color,this.getColorTransmission());
	}
}
