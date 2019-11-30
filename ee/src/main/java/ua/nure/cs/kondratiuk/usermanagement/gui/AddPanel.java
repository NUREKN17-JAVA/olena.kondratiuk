package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;

public class AddPanel extends JPanel implements ActionListener {
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final String OK_COMMAND = "ok";
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String CANCEL_COMMAND = "cancel";
	
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel fieldPanel;
	private JTextField firstNameField;
	private JTextField dayOfBirthField;
	private JTextField lastNameField;
	private Color bgColor;

	public AddPanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName(ADD_PANEL_COMPONENT_NAME);	
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);		
	}

	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			addLabeledField(fieldPanel, "Имя", getFirstNameField());
			addLabeledField(fieldPanel, "Фамилия", getLastNameField());
			addLabeledField(fieldPanel, "Отчество", getDayOfBirthField());
		}
		return fieldPanel;
	}

	private void addLabeledField(JPanel panel, String name, JTextField field) {
		JLabel label = new JLabel(name);
		label.setLabelFor(field);
		panel.add(label);
		panel.add(field);
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName(FIRST_NAME_FIELD_COMPONENT_NAME);
		}
		return firstNameField;
	}
	
	private JTextField getDayOfBirthField() {
		if(dayOfBirthField == null) {
			dayOfBirthField = new JTextField();
			dayOfBirthField.setName(DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		}
		return dayOfBirthField;
	}

	private JTextField getLastNameField() {
		if(lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName(LAST_NAME_FIELD_COMPONENT_NAME);
		}
		return lastNameField;
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Отмена");
			cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME);
			cancelButton.setActionCommand(CANCEL_COMMAND);
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ок");
			okButton.setName(OK_BUTTON_COMPONENT_NAME);
			okButton.setActionCommand(OK_COMMAND);
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	private void clearFields() {
		getFirstNameField().setText("");
		getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText("");
		getLastNameField().setBackground(bgColor);
		
		getDayOfBirthField().setText("");
		getDayOfBirthField().setBackground(bgColor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("ok".equalsIgnoreCase(e.getActionCommand())) {
			User user = new User();
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setDateOfBirth(format.parse(getDayOfBirthField().getText()));
			} catch (ParseException e1) {
				getDayOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				parent.getDao().create(user );
			} catch (DatabaseExeption e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
		
	}

}
