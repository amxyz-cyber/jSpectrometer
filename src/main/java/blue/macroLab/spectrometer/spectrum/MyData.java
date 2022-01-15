package blue.macroLab.spectrometer.spectrum;

import java.io.File;
import java.io.IOException;

import com.panayotis.gnuplot.dataset.FileDataSet;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.model.Data;

public class MyData {
	private String fileData;
	private FileDataSet dataset;
	private File file;
	private Data data;
	
	public MyData(String d,Data m) {
		this.fileData = d;
		this.data = m;
	    
	}
	
	public MyData(Data m) {
		this.data = m;    
	}
	
	public void setDataset() {
		try{
		file = new File(fileData);
		this.dataset = new FileDataSet(file);
        } catch (IOException ex) {
            this.data.getLogger().addLog("Could not read in data file.");
        	this.data.getLogger().addLog(ex.getMessage());
        }
	}

	public FileDataSet getDataset() {
		return dataset;
	}
	
	public Double getDataPoint(int iCol, int index ) {
		String sDataPoint = this.dataset.getPointValue(index, iCol);
		Double dVal = Double.parseDouble(sDataPoint);
		return dVal;
	}

	public File getFile() {
		return file;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

}
