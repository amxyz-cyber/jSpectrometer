package blue.macroLab.spectrometer.model;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.utils.Utility;
import javafx.beans.property.*;

public class MyMessage {
	private String _log ="";
	private SimpleStringProperty log;
	private StringBuilder buffer;
	
	public MyMessage() {
		resetBuffer();
	}
	
	public void resetBuffer() {
		this.buffer = new StringBuilder(Constants.MAX_LOG_SIZE);
	}
	
	public final String getLog() {
		if(log == null) {
			return _log;
		} else 
		return log.get();
	}
	
	public final void setLog(String value) {
		if(log == null) {
			_log = value;
		} else {
		log.set(value);
		}
	}
	
	public final StringProperty logProperty() {
		if(log == null) {
			log = new SimpleStringProperty(this,"log",_log);
		}
		return log; 
	}
	
	public void addLog(String value) {
		int lenValue = value.length();
		int lenBuffer = this.buffer.length();
		int capacityBuffer = this.buffer.capacity();
		int diff = capacityBuffer - lenBuffer;
		if (lenValue <= diff - 1) {
			Utility.addNewLine(this.buffer);
			this.buffer.append(value);
		} else {
			resetBuffer();
			this.buffer.append(value);
		}
		this.setLog(this.buffer.toString());
	}
	
}
