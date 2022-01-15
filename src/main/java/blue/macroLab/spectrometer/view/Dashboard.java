package blue.macroLab.spectrometer.view;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.controller.DashboardVC;
import blue.macroLab.spectrometer.model.Data;

public class Dashboard extends AbstractWindow {
	private MenuBar menubar;
	private DashboardVC vc;
	private Label lblFeedback;
	private Label lblHeading;
	private Label lblPath;

	public Dashboard(Data m) {
		super(m);
		this.vc = new DashboardVC(m,this);
		setTitles();
		this.menubar = new MenuBar();
		
		setupWindow(2,1);
		createMenu();
		createVisualizeMenu();
		createHelpMenu();
		addMainView();
		this.showPanel();
		vc.displayFeedback(m.getLogger().getLog(),false);
		this.getData().getScreen().wait(this.getWindow());
		
	}
	
	public void createMenu() {
		this.getLeftPanel().addComponent(this.menubar);
		this.addSpace(this.getLeftPanel(),Constants.SPACE_PANEL_MEDIUM);
	}
	
	public void createVisualizeMenu(){ 
		Menu menuVisualize = this.createMenu(this.getData().getMessageValue(Constants.KEY_MENU_GRAPH ), this.menubar);
		this.createMenuEntry(menuVisualize,this.getData().getMessageValue(Constants.KEY_MENU_GRAPH_NEW),this.vc.exportGraph(this));
	}
	
	public void createHelpMenu() {
		Menu menuHelp = this.createMenu(this.getData().getMessageValue(Constants.KEY_HELP), this.menubar);
		this.createMenuEntry(menuHelp,this.getData().getMessageValue(Constants.KEY_MENU_HOMEPAGE), this.vc.getMsgDialogue(this.getData().getScreen(), this.getData().getMessageValue(Constants.KEY_MENU_HOMEPAGE),this.getData().getMessageValue(Constants.KEY_HOMEPAGE)));
		this.createMenuEntry(menuHelp,this.getData().getMessageValue(Constants.KEY_ABOUT), this.vc.getMsgDialogue(this.getData().getScreen(), this.getData().getMessageValue(Constants.KEY_ABOUT),this.getData().getMessageValue(Constants.KEY_INFO)));
		this.createMenuEntry(menuHelp,this.getData().getMessageValue(Constants.KEY_EXIT), this.vc.exitAction());
	}

	public Label getLblFeedback() {
		return lblFeedback;
	}
	
	public void addMainView() {
		this.setDirection(this.getRightPanel(),Direction.VERTICAL);
		this.addSpace(this.getRightPanel(), Constants.SPACE_PANEL_SMALL);
		this.lblHeading = this.addHeadingLabel(this.getData().getMessageValue(Constants.KEY_PATH));
		this.lblPath = this.addDataLabel( this.getData().getChartSettings().getDataFile());
		this.addRow(lblHeading, lblPath, getRightPanel());
		this.addSpace(this.getRightPanel(),Constants.SPACE_PANEL_MEDIUM);
		addButtons();
		
		this.addSpace(this.getRightPanel(),Constants.SPACE_PANEL_MEDIUM);
		this.addSeperator(this.getRightPanel(),Constants.SPACE_PANEL_MEDIUM);
		
		this.lblFeedback = this.addFeedbackLabel();
		this.getRightPanel().addComponent(lblFeedback);
		this.createBottom(this.vc.refresh(getData()), this.vc.reset(lblFeedback, getData()),this.vc.toMain(getData(), getWindow())  );
	}
	
	public void addButtons() {
		String strCheck = this.getData().getMessageValue(Constants.KEY_BTN_CHECK);
		Button btnCheck = this.addButton(strCheck,this.vc.validate(this));
		this.getRightPanel().addComponent(btnCheck);
	}

	public void setTitles() {
		this.setTitle(this.getData().getTitle());
		this.setLeftPanelTitle(this.getData().getMessageValue(Constants.KEY_MAIN_MENU));
		this.setRightPanelTitle(this.getData().getMessageValue(Constants.KEY_FEEDBACK_HEADING));
		this.setBottomPanelTitle(this.getData().getMessageValue(Constants.KEY_FUNCTIONS ));
	}
	
}
