package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;

public class BrowsePanel extends JPanel implements ActionListener {
	private static final String EDIT_COMMAND = "edit";
	private static final String ADD_COMMAND = "add";
	private static final String DELETE_COMMAND = "delete";
	private static final String DETAIL_COMMAND = "detail";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String DETAIL_BUTTON_COMPONENT_NAME = "detailButton";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	
	private MainFrame parent;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailButton;

	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName(BROWSE_PANEL_COMPONENT_NAME);	
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(), null);
			buttonPanel.add(getEditButton(), null);
			buttonPanel.add(getDeleteButton(), null);
			buttonPanel.add(getDetailsButton(), null);
		}
		return buttonPanel;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Добавить");
			addButton.setName(ADD_BUTTON_COMPONENT_NAME);
			addButton.setActionCommand(ADD_COMMAND);
			addButton.addActionListener(this);
		}
		return addButton;
	}
	
	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText("Изменить");
			editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
			editButton.setActionCommand(EDIT_COMMAND);
			editButton.addActionListener(this);
		}
		return editButton;
	}
	
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Удалить");
			deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
			deleteButton.setActionCommand(DELETE_COMMAND);
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}
	
	private JButton getDetailsButton() {
		if (detailButton == null) {
			detailButton = new JButton();
			detailButton.setText("Подробнее");
			detailButton.setName(DETAIL_BUTTON_COMPONENT_NAME);
			detailButton.setActionCommand(DETAIL_COMMAND);
			detailButton.addActionListener(this);
		}
		return detailButton;
	}

	private JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}
	private JTable getUserTable() {
		if (userTable == null) {
			userTable = new JTable();
			userTable.setName(USER_TABLE_COMPONENT_NAME);
		}
		return userTable;
	}
	
	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseExeption e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Eror", 
					JOptionPane.ERROR_MESSAGE);
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		}
		else if ("edit".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showEditPanel(userTable.getSelectedRow());
		}
		else if ("details".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showDetailsPanel(userTable.getSelectedRow());
		}
		else if ("delete".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showDeletePanel(userTable.getSelectedRow());
		}		
	}

}
