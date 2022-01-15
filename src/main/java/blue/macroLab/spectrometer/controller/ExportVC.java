package blue.macroLab.spectrometer.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import com.panayotis.gnuplot.style.NamedPlotColor;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.utils.Utility;
import blue.macroLab.spectrometer.view.ExportForm;
import blue.macroLab.spectrometer.view.MyScreen;
import blue.macroLab.spectrometer.spectrum.BivariatePlot;
import blue.macroLab.spectrometer.spectrum.MyData;
import blue.macroLab.spectrometer.spectrum.MySpectrometer;

public class ExportVC extends AbstractExportVC{

	public ExportVC(Data m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public Runnable export(ExportForm view) {
		return new Runnable() {
			public void run() {
				try {
					view.getData().getLogger().resetBuffer();
					view.getLblFeedback().setText("");
					view.getData().getMydata().setDataset();
				       if (Utility.fileExists(view.getData().getMydata().getFile().getAbsolutePath()) ) {
				    	   view.getData().getLogger().addLog(view.getData().getMessageValue(Constants.KEY_DATA_RETRIEVAL));
				    	    MySpectrometer mysignals = new MySpectrometer(view.getData().getMydata());
						    mysignals.calculateSignals();
						    view.getData().getChartSettings().setPath();
						    
						    if (view.getData().getChartSettings().getColorA() == null) {
						    	view.getData().getChartSettings().setColorA("");
						    }
						    
						    if (view.getData().getChartSettings().getColorT()  == null ) {
						    	view.getData().getChartSettings().setColorT("");
						    }
						    String path = view.getData().getChartSettings().getPath();
						    String xT = view.getData().getMessageValue(Constants.X_TITLE) +" ("+ view.getData().getChartSettings().getWavelengthUnit()+")"; 
						    String yT1 = view.getData().getMessageValue(Constants.Y_TITLE_1);
						    String yT2 = view.getData().getMessageValue(Constants.Y_TITLE_2);
						    String t = view.getData().getChartSettings().getChartTitle(); 
						    NamedPlotColor colorA = view.getData().getChartSettings().getColorA();
						    NamedPlotColor colorT = view.getData().getChartSettings().getColorT();
						    BivariatePlot.paintLineChart(path,mysignals.toData(mysignals.getMyDataA()) ,mysignals.toData(mysignals.getMyDataT()),yT1,yT2,xT,t,colorA,colorT);
						    view.getData().getLogger().addLog(view.getData().getMessageValue(Constants.SUCCESS_PLOT));
						    String logPath = "---> " + path;
						    view.getData().getLogger().addLog(logPath);
				       } else {
				    	   view.getData().getLogger().addLog(view.getData().getMessageValue(Constants.KEY_NO_DATA));
				       }
						view.getLblFeedback().setText(view.getData().getLogger().getLog());
				} catch(Exception e) { 
					view.getData().getLogger().setLog(e.getMessage());
					view.getLblFeedback().setText(view.getData().getLogger().getLog());
				}
			}};
	}
	
}
