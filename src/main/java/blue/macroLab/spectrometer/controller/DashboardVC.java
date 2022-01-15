package blue.macroLab.spectrometer.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.view.Dashboard;
import blue.macroLab.spectrometer.view.ExportForm;
import blue.macroLab.spectrometer.view.MyScreen;

public class DashboardVC extends AbstractController {
	private Dashboard view;
	
	public DashboardVC(Data m, Dashboard v) {
		super(m);
		this.view = v;
		
		
	}
	
	public void displayFeedback(String msg,boolean isOk) {
		if (isOk) {
			this.view.getLblFeedback().setText("");
		} else {
			this.view.getLblFeedback().setText(msg);
		}
	}
	
	public Runnable exitAction() {
		return new Runnable() {
			public void run() {
					System.exit(0);
				}};
			}
	
	public Runnable getMsgDialogue(MyScreen s, String entry,String msg) {		
		return new Runnable() {	 
			 public void run() {
                MessageDialog.showMessageDialog(
                        s.getTextGui(), entry, msg, MessageDialogButton.OK);
            }};
      }
	
	public Runnable resetLabels(Dashboard view) {		
		return new Runnable() {	 
			 public void run() {
				 view.getLblFeedback().setText("");
				 view.getData().getLogger().resetBuffer();
				 view.getData().getLogger().setLog("");
				 displayFeedback(view.getData().getLogger().getLog(),false);
            }};
      }
	
	
	
	public Runnable exportGraph(Dashboard view) {
		return new Runnable() {
		public void run() {
			try {
				ExportVC vc = new ExportVC(view.getData());
				ExportForm myform = new ExportForm(view.getData(),vc); 
				
			} catch(Exception e) { 
				view.getData().getLogger().setLog(e.getMessage());
			}
		}};
	}

	
	public Runnable validate(Dashboard view) {
		return new Runnable() {
		public void run() {
			try {
				view.getData().getLogger().resetBuffer();
				displayFeedback(view.getData().getLogger().getLog(),true);
				view.getData().getMydata().setDataset();
				if (view.getData().getMydata().getDataset() == null) {
					view.getData().getLogger().addLog(view.getData().getMessageValue( Constants.ERROR_DATA));
				} else {
						view.getData().getLogger().addLog(view.getData().getMessageValue( Constants.SUCCESS_DATA));
				}
				displayFeedback(view.getData().getLogger().getLog(),false);
			} catch(Exception e) { 
				view.getData().getLogger().addLog(e.getMessage());
			}
			
		}};
	}
}
