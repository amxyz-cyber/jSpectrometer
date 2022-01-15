/*
 * MyScreen.java
 */
package blue.macroLab.spectrometer.view;
import java.io.IOException;
import java.util.regex.Pattern;
import java.io.*; 

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.cons.Constants;

import com.googlecode.lanterna.gui2.*;

public class MyScreen{
	private DefaultTerminalFactory terminalFactory;
	private Screen screen;
	private WindowBasedTextGUI textGUI;
	private Data data;
	
	public MyScreen(Data m) {
		this.data = m;
		setupTerminal();
		setupScreen();
		setupGui();
	}
	
	public void wait(Window w) {
		this.textGUI.addWindowAndWait(w);
	}
	
	public void closeTerminal() {
		try {
			this.screen.stopScreen();
		 }
        catch (IOException e) {
        	this.data.getLogger().addLog(this.data.getMessageValue(Constants.ERROR_CLOSE_TERMINAL ));
        	this.data.getLogger().addLog(e.getMessage());
        }
    }
    
    public void refreshScreen() {
		 try {
			this.screen.clear();
			this.screen.refresh();
		  
		 } catch(IOException e) {
				//throw new RuntimeException(e);
			 	this.data.getLogger().addLog(this.data.getMessageValue(Constants.ERROR_REFRESH_SCREEN ));
	        	this.data.getLogger().addLog(e.getMessage());
			}
	}
	
	public void setupTerminal() {
		this.terminalFactory = new DefaultTerminalFactory();
		this.data.getLogger().addLog(this.data.getMessageValue(Constants.SUCCESS_CREATE_TERMINAL ));
	}
	 
	public void setupScreen() {
		try {
		  this.screen = this.terminalFactory.createScreen();
          this.screen.startScreen();
          this.data.getLogger().addLog(this.data.getMessageValue(Constants.SUCCESS_CREATE_SCREEN));
		 
		 } catch(IOException e) {
			 this.data.getLogger().addLog(this.data.getMessageValue(Constants.ERROR_CREATE_SCREEN ));
	         this.data.getLogger().addLog(e.getMessage());
			}
	}

	public void setupGui() {
		 this.textGUI = new MultiWindowTextGUI(screen);
		 this.data.getLogger().addLog(this.data.getMessageValue(Constants.SUCCESS_CREATE_GUI));
     }
     
     public WindowBasedTextGUI getTextGui() {
		return this.textGUI;
	}
}