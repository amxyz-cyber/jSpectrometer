package blue.macroLab.spectrometer.controller;

import com.googlecode.lanterna.gui2.Window;

import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.view.MyScreen;


import com.googlecode.lanterna.gui2.Label;

public abstract class AbstractController {
	private Data data;
	
	public AbstractController(Data m) {
		this.data = m;
	}
	
	public Data getData() {
		return data;
	}
	
	public Runnable refresh(Data m) {
		return new Runnable() {
		public void run() {
			try {
				m.getScreen().refreshScreen();
			} catch(Exception e) { 
				m.getLogger().setLog(e.getMessage());
			}
		}};
	}
	
	public Runnable reset(Label lblFeedback, Data m) {
		return new Runnable() {
		public void run() {
			try {
				lblFeedback.setText("");
			} catch(Exception e) { 
				m.getLogger().setLog(e.getMessage());
			}
		}};
	}
	
	public Runnable toMain(Data m,Window w) {
		return new Runnable() {
		public void run() {
			try {
				String title = w.getTitle();
				if (!m.getTitle().equals(title)) {
				  	m.getScreen().getTextGui().removeWindow(w);
				}
			} catch(Exception e) { 
				m.getLogger().setLog(e.getMessage());
			}
		}};
	}
}
