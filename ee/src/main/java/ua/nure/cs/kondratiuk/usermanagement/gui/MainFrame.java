package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.cs.kondratiuk.usermanagement.db.Dao;
import ua.nure.cs.kondratiuk.usermanagement.db.DaoFactory;
import ua.nure.cs.kondratiuk.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private EditPanel editPanel;
	private DeletePanel deletePanel;
	private DetailsPanel detailsPanel;
	private Dao dao;
	
	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getDao();
		initialize();
	}
	
	public Dao getDao() {
		return dao;
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	
	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		
		return addPanel;
	}
	
	private EditPanel getEditPanel() {
		if (editPanel == null) {
			editPanel = new EditPanel(this, null);
		}
		return editPanel;
	}
	
	private DeletePanel getDeletePanel() {
		if (deletePanel == null) {
			deletePanel = new DeletePanel(this, null);
		}
		return deletePanel;
	}
	
	private DetailsPanel getDetailsPanel() {
		if (detailsPanel == null) {
			detailsPanel = new DetailsPanel(this, null);
		}
		return detailsPanel;
	}
	
	public void showEditPanel(int selectedRow) {
		showPanel(getEditPanel());
	}
	
	public void showDeletePanel(int selectedRow) {
		showPanel(getDeletePanel());
	}
	
	public void showDetailsPanel(int selectedRow) {
		showPanel(getDetailsPanel());
	}
	
	public void showBrowsePanel() { 
		showPanel((JPanel) getBrowsePanel());
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);			
		}
		return contentPanel;
	}
	private Component getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);		
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

}
