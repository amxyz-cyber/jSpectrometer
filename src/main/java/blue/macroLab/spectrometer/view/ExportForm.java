package blue.macroLab.spectrometer.view;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.panayotis.gnuplot.style.NamedPlotColor;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.controller.AbstractExportVC;
import blue.macroLab.spectrometer.model.Data;

public class ExportForm extends AbstractWindow {
	private AbstractExportVC vc;
	private Label lblPath;
	private Label lblFeedback;
	private Label lblFileName;
	private Label lblFolder;
	private Label lblcolorA;
	private Label lblcolorT;
	private Button btnExport;
	private TextBox txt;
	private Button btnSubmit;
	private final boolean isReadOnly = false;
	private ComboBox<String> comboBoxA;
	private ComboBox<String> comboBoxT;

	public ExportForm(Data m,AbstractExportVC controller) {
		super(m);
		vc = controller;
		setTitles();
		setupWindow(2,3);
		this.comboBoxA = this.createComboBox(isReadOnly);
		this.comboBoxT = this.createComboBox(isReadOnly);
		populateCombobox(comboBoxA);
		populateCombobox(comboBoxT);
		createLabels();
		createButtons();
		setupForm();
		addProgressBar();
		this.createBottom(this.vc.refresh(getData()), this.vc.reset(lblFeedback, getData()),this.vc.toMain(getData(), this.getWindow())  );
		this.showPanel();
		this.getData().getScreen().wait(this.getWindow());
		
	}
	
	public void setupWindow() {
		this.setWindow(this.getData().getMessageValue(Constants.KEY_EXPORT_TITLE ));	
		this.setPanel();
		this.setupPanel(1);
		this.setupGrid(3);
		this.setDirection(this.getPanel(), Direction.VERTICAL);
		this.setLeftPanel("");
		this.setupPanel(3, getLeftPanel() );
		this.setupGrid(3, getLeftPanel() );
		this.setRightPanel(this.getData().getMessageValue(Constants.KEY_LOG_TITLE ));
		this.setBottomPanel("");
	}
	
	public void addProgressBar() {
		//this.progressBar = setupProgressBar();
		//getRightPanel().addComponent(progressBar);
		
		this.getRightPanel().addComponent(this.lblFeedback);
		
	}
	
	public void createLabels() {
		/*if (exportType.equals(ExportSettings.EXPORT_CALC)) {
			this.lblPath = this.addDataLabel(getData().getLocalc().getPath());
		}else if (exportType.equals(ExportSettings.EXPORT_CSV)) {
			this.lblPath = this.addDataLabel(getData().getMycsv().getPath());
		}*/
		this.lblPath = this.addDataLabel(getData().getChartSettings().getPath() );
		this.lblFolder = this.addHeadingLabel(this.getData().getMessageValue(Constants.KEY_DIR_SELECT ));
		this.lblFeedback = this.addFeedbackLabel();
		this.lblFileName = this.addHeadingLabel(this.getData().getMessageValue(Constants.KEY_FILE_NAME ));
		this.lblcolorA  = this.addHeadingLabel(this.getData().getMessageValue(Constants.KEY_COLOR_A ));
		this.lblcolorT  = this.addHeadingLabel(this.getData().getMessageValue(Constants.KEY_COLOR_T ));
	}
	
	
	public void createButtons() {
		String str = this.getData().getMessageValue(Constants.KEY_BTN_EXPORT);
		this.btnExport = this.addButton(str,this.vc.export(this));
		String strSubmit = this.getData().getMessageValue(Constants.KEY_BTN_SUBMIT);
		this.btnSubmit = this.addButton(strSubmit,this.vc.save(this));
	}
	
	public void setupForm() {
		this.getBottomPanel().addComponent(btnExport);
		//row #1
		this.getLeftPanel().addComponent(this.lblFolder);
		this.getLeftPanel().addComponent(this.lblPath);
		this.lblPath.setText(this.getData().getChartSettings().getFileName());
		this.addChooser(this.getData().getMessageValue(Constants.KEY_BTN_SELECT), this.vc.getPath(this,lblPath) , getLeftPanel());
		this.addSpace(this.getLeftPanel(),Constants.SPACE_PANEL_MEDIUM);
		this.addSeperator(this.getLeftPanel(),Constants.SPACE_PANEL_MEDIUM);
		
		//row #2
		this.txt = this.createTextBox(this.getData().getChartSettings().getFileName());
        this.addRow(lblFileName, txt, this.getLeftPanel() );
        
        this.addSpace(this.getLeftPanel(),Constants.SPACE_PANEL_SMALL);
        this.addSeperator(this.getLeftPanel(),Constants.SPACE_PANEL_MEDIUM);
       
        //row #3
        this.addRow(this.lblcolorA, this.comboBoxA, this.getLeftPanel() );
        this.addSpace(this.getLeftPanel(),Constants.SPACE_PANEL_SMALL);
        this.addSeperator(this.getLeftPanel(),Constants.SPACE_PANEL_MEDIUM); 
		//row #4
        this.addRow(this.lblcolorT, this.comboBoxT, this.getLeftPanel() );
        this.getLeftPanel().addComponent(btnSubmit);
        
}

	public TextBox getTxt() {
		return txt;
	}

	public Label getLblPath() {
		return lblPath;
	}

	public Label getLblFeedback() {
		return lblFeedback;
	}

	public void setTitles() {
		this.setTitle(this.getData().getMessageValue(Constants.KEY_EXPORT_TITLE));
		this.setLeftPanelTitle("");
		this.setRightPanelTitle(this.getData().getMessageValue(Constants.KEY_LOG_TITLE ));
		this.setBottomPanelTitle(this.getData().getMessageValue(Constants.KEY_FUNCTIONS ));
	}
	
	public void populateCombobox(ComboBox<String> combo) {
		
		for (NamedPlotColor c: NamedPlotColor.values()) {
					this.addToComboBox(combo, c.toString());
				}
		}

	public ComboBox<String> getComboBoxA() {
		return comboBoxA;
	}

	public ComboBox<String> getComboBoxT() {
		return comboBoxT;
	}

}
