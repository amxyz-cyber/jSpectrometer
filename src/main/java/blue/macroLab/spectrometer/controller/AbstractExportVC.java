package blue.macroLab.spectrometer.controller;

import java.io.File;
import java.util.Map;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import com.panayotis.gnuplot.style.NamedPlotColor;

import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.view.ExportForm;
import blue.macroLab.spectrometer.view.MyScreen;

public abstract class AbstractExportVC extends AbstractController {
	
	public AbstractExportVC(Data m) {
		super(m);
	}
	
	public abstract Runnable export(ExportForm view);
	
	public Runnable getPath(ExportForm view,Label lbl) {
        return new Runnable() {
        public void run() {
                try {
                        File file = view.setDirChooser();
                        view.getData().getChartSettings().setFolder(file.getAbsolutePath());
                        view.getData().getChartSettings().setPath();
                        String s = file.getAbsolutePath();
                        view.getData().getLogger().setLog(s);
                        lbl.setText(view.getData().getChartSettings().getPath());
                } catch(Exception e) {
                	view.getData().getLogger().setLog(e.getMessage());
                }
        }};
	}
	
	public Runnable save(ExportForm view) {
		return new Runnable() {
		public void run() {
			try {
				String txt = view.getTxt().getText();
				if (!txt.isBlank()) {
					view.getData().getChartSettings().setFileName(txt);
					view.getData().getChartSettings().setPath();
					view.getLblPath().setText(view.getData().getChartSettings().getPath());
				}
				String cName = auxGetComboItem(view.getComboBoxA());
				view.getData().getChartSettings().setColorA(cName);
				String cName2 = auxGetComboItem(view.getComboBoxT());
				view.getData().getChartSettings().setColorT(cName2);
				
			} catch(Exception e) { 
				view.getData().getLogger().setLog(e.getMessage());
				view.getLblFeedback().setText(view.getData().getLogger().getLog());
			}
		}};
	}
	
	private String auxGetComboItem(ComboBox<String> comboBox) {
		String colorName = "";		
		try {
				int i = comboBox.getSelectedIndex();
				colorName = comboBox.getItem(i);
				}catch(Exception e) {
					
				}
		return colorName;
	}
}