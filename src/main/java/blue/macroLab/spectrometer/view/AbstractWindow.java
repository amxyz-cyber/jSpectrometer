package blue.macroLab.spectrometer.view;

import java.io.File;
import java.util.Arrays;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.ProgressBar;
import com.googlecode.lanterna.gui2.ScrollBar;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;

import blue.macroLab.spectrometer.cons.Constants;
import blue.macroLab.spectrometer.model.Data;
import blue.macroLab.spectrometer.view.MyScreen;

public abstract class AbstractWindow {
	private Window window;
	private Panel contentPanel;
	private Panel leftPanel;
	private Panel rightPanel;
	private Panel bottomPanel;
	private Panel feedbackPanel;
	private GridLayout gridLayout;
	private Button btnRefresh;
	private Button btnReset;
	private Button btnMain;
	private String title;
	private String leftPanelTitle;
	private String rightPanelTitle;
	private String bottomPanelTitle;
	
	private Data model;
	
	public AbstractWindow(Data m) {
		this.model = m;
	}
	
	public abstract void setTitles();
	
	
	public void setWindow(String title) {
		 this.window = new BasicWindow(title);
		 this.window.setHints(Arrays.asList(Hint.MODAL ,Hint.FULL_SCREEN,Hint.FIXED_POSITION,Hint.FIT_TERMINAL_WINDOW));
	}
	
	public ComboBox createComboBox(boolean isReadable) {
		ComboBox cb = new ComboBox<String>().setReadOnly(isReadable);
		return cb;
	}
	
	public void addToComboBox(ComboBox combo,String item) {
		combo.addItem(item);
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}


	public void setLeftPanelTitle(String leftPanelTitle) {
		this.leftPanelTitle = leftPanelTitle;
	}


	public void setRightPanelTitle(String rightPanelTitle) {
		this.rightPanelTitle = rightPanelTitle;
	}


	public void setBottomPanelTitle(String bottomPanelTitle) {
		this.bottomPanelTitle = bottomPanelTitle;
	}


	public Window getWindow() {
		return this.window;
	}
	
	public Data getData() {
		return this.model;
	}
	public void setPanel() {
		this.contentPanel = new Panel();
	}
	
	public Panel getPanel() {
		return this.contentPanel;
	}
	
	public Panel getLeftPanel() {
		return this.leftPanel;
	}
	
	public Panel getRightPanel() {
		return this.rightPanel;
	}
	
	public Panel getFeedbackPanel() {
		return this.feedbackPanel;
	}
	
	public Panel getBottomPanel() {
		return this.bottomPanel;
	}
	
	 public void setupPanel(int column) {
			this.contentPanel.setLayoutManager(new GridLayout(column));
		}
		
		public void showPanel() {
			this.window.setComponent(this.contentPanel);
		}
		
		public void addSpace(Panel p, int space) {
			p.addComponent(
	                    new EmptySpace()
	                            .setLayoutData(
	                                    GridLayout.createHorizontallyFilledLayoutData(space)));
		}
		
		public void addSeperator(Panel p, int space) {
			p.addComponent(
	                    new Separator(Direction.HORIZONTAL)
	                            .setLayoutData(
	                                    GridLayout.createHorizontallyFilledLayoutData(space)));
		}
		
		public void setupGrid(int space) {
			this.gridLayout = (GridLayout)this.contentPanel.getLayoutManager();
	        this.gridLayout.setHorizontalSpacing(space);
		}
		
		public void showWindow() {
	        showPanel();
	        this.model.getScreen().wait(this.getWindow());
		}
		
		public Button addButton(String btn, Runnable action) {
			Button button = new Button(btn,action);
			return button;
		}
		
		public void addButton(Button btn1) {
	        this.contentPanel.addComponent(btn1);
		}
		
		public void setLeftPanel(String title) {
			this.leftPanel = new Panel();
			setBorder(this.leftPanel, title);
		}
		
		public void setRightPanel(String title) {
			this.rightPanel = new Panel();
			setBorder(this.rightPanel, title);
		}
		
		public void setBottomPanel(String title) {
			this.bottomPanel = new Panel();
			setBorder(this.bottomPanel, title);
		}
		
		public void setFeedbackPanel(String title) {
			this.feedbackPanel = new Panel();
			setBorder(this.feedbackPanel, title);
		}
		
		public void setBorder(Panel p, String title) {
			this.contentPanel.addComponent(p.withBorder(Borders.singleLine(title)));
		}
		
		
		public void addToleftPanel(Button b) {
			this.leftPanel.addComponent(b);
		}
		
		/*public void addBox() {
			this.contentPanel.addComponent(this.box.withBorder(Borders.singleLine()));
		}*/
		
		/*public void setListBox(int terminal, int numRows) {
			this.size = new TerminalSize(terminal, numRows);
			this.box = new ActionListBox(this.size);
		}*/
		
		public void addRow(String label,TextBox t,Panel p) {
	        p.addComponent(new Label(label));
	        p.addComponent(t);
		}
		
		public void addRow(Label key,Label value,Panel p) {
	        p.addComponent(key);
	        p.addComponent(value);
		}
		
		public void addRow(Label key,TextBox value,Panel p) {
	        p.addComponent(key);
	        p.addComponent(value);
		}
		
		public void addRow(Label key,ComboBox value,Panel p) {
	        p.addComponent(key);
	        p.addComponent(value);
		}
		
		public Label addFeedbackLabel() {
			Label lbl = new Label("");
			lbl.setForegroundColor(TextColor.ANSI.GREEN);
			lbl.setBackgroundColor(TextColor.ANSI.WHITE_BRIGHT);
			lbl.setLabelWidth(50);
			return lbl;
		}
		
		public Label addDataLabel(String t) {
			Label lbl = new Label(t);
			lbl.setForegroundColor(TextColor.ANSI.MAGENTA);
			lbl.setBackgroundColor(TextColor.ANSI.WHITE_BRIGHT);
			lbl.setLabelWidth(30);
			
			return lbl;
		}
		
		public Label addHeadingLabel(String t) {
			Label lbl = new Label(t);
			lbl.setForegroundColor(TextColor.ANSI.BLUE);
			lbl.setBackgroundColor(TextColor.ANSI.GREEN_BRIGHT);
			lbl.setLabelWidth(0);
			return lbl;
		}
		
						
		public void setDirection(Panel p, Direction d) {
			p.setLayoutManager(new LinearLayout(d)); 
		}
		
		public Label getLabel(String s) {
			Label label = new Label(s);
			return label;
		}
		
		public Menu createMenu(String entry, MenuBar bar) {
			Menu menuEntry = new Menu(entry);
	        bar.add(menuEntry);
	        return menuEntry;
		}
		
		public void createMenuEntry(Menu menu, String entry, Runnable r) {
			MenuItem item = new MenuItem(entry,r);
			menu.add(item);
		}
		
		public void createBottom(Runnable action1,Runnable action2,Runnable action3) {
			this.setDirection(bottomPanel,Direction.HORIZONTAL);
			this.btnRefresh = new Button(this.model.getMessageValue(Constants.KEY_BTN_REFRESH),action1);
			this.btnReset = new Button(this.model.getMessageValue(Constants.KEY_BTN_RESET),action2);
			this.btnMain = new Button(this.model.getMessageValue(Constants.KEY_BTN_MAIN),action3);
			this.bottomPanel.addComponent(btnMain);
			addSpace(bottomPanel,Constants.SPACE_PANEL_MEDIUM);
			this.bottomPanel.addComponent(btnRefresh);
			addSpace(bottomPanel,Constants.SPACE_PANEL_MEDIUM);
			this.bottomPanel.addComponent(btnReset);
			
		}
		
	public File setDirChooser() {
            File input = new DirectoryDialogBuilder()
                            .setTitle(this.model.getMessageValue(Constants.KEY_DIR_SELECT ))
                            .setDescription(this.model.getMessageValue(Constants.KEY_DIR_CHOOSE ))
                            .setActionLabel(this.model.getMessageValue(Constants.KEY_BTN_SELECT))
                            .build()
                            .showDialog(model.getScreen().getTextGui());
            return input;
    }
		
		public Button addChooser(String btnName, Runnable r, Panel p) {
            Button btn = addButton(btnName,r);
            alignButtonToRight(btn);
            p.addComponent(btn);
            return btn;
		}
		
		public GridLayout setupGrid(int space, Panel p) {
            GridLayout g = (GridLayout)p.getLayoutManager();
            g.setHorizontalSpacing(space);
            return g;
		}
		
		public void setupPanel(int column, Panel p) {
            p.setLayoutManager(new GridLayout(column));
		}
		
		public ProgressBar setupProgressBar() {
			ProgressBar progress = new ProgressBar();
			progress.setRenderer(new ProgressBar.LargeProgressBarRenderer());
			progress.setLabelFormat("%2.0f%%");
			return progress;
		}
		
		public void setupScrollBar(Panel p) {
			ScrollBar scroll = new ScrollBar(Direction.VERTICAL);
			scroll.setVisible(true);
			scroll.setScrollMaximum(30);
			scroll.setViewSize(30);
			scroll.addTo(p);
		}
		
		public void updateProgress(int min, int max,ProgressBar pBar) {
			pBar.setMin(min);
			pBar.setMax(max);
		}
		
		public TextBox createTextBox(String s) {
			TextBox txt = new TextBox(s);
			return txt;
		}
		
		 public void alignButtonToRight(Button b) {
             b.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.END));
		 }
		 
		 public void alignButtonToLeft(Button b) {
             b.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER));
		 }

		 public void setupWindow(int column,int columnLeftPanel) {
				this.setWindow(this.title);	
				this.setPanel();
				this.setupPanel(column);
				this.setupGrid(3);
				this.setDirection(this.getPanel(), Direction.VERTICAL);
				this.setLeftPanel(leftPanelTitle); 
				//next 2 needed for ExportForm
				this.setupPanel(columnLeftPanel, getLeftPanel() );
				this.setupGrid(columnLeftPanel, getLeftPanel() );
				
				this.addSpace(this.getLeftPanel(),Constants.SPACE_PANEL_SMALL);
				this.setRightPanel(rightPanelTitle);
				this.setBottomPanel(bottomPanelTitle);
				
			}

}
