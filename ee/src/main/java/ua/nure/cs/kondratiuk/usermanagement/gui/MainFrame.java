package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	
	public MainFrame() {
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("”правление пользовател€ми");
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
		return browsePanel;
	}

}
