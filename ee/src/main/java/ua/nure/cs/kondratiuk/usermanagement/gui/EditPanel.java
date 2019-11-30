package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.Dao;
import ua.nure.cs.kondratiuk.usermanagement.db.DaoFactory;
import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;
import ua.nure.cs.kondratiuk.usermanagement.util.Messages;

public class EditPanel extends JPanel implements ActionListener {
	private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"; //$NON-NLS-1$
	private static final String ERROR_MESSAGE = "Error"; //$NON-NLS-1$
	private static final String EDIT_PANEL_COMPONENT_NAME = "editPanel"; //$NON-NLS-1$
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton"; //$NON-NLS-1$
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton"; //$NON-NLS-1$
	private static final String CANCEL_COMMAND = "cancel"; //$NON-NLS-1$	
	private static final String OK_COMMAND = "ok"; //$NON-NLS-1$
	
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField"; //$NON-NLS-1$
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField"; //$NON-NLS-1$
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField"; //$NON-NLS-1$
	
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dayOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	private User user;
	private Dao userDao;
	
	public EditPanel(MainFrame parent, User usr) {
		this.parent = parent;
		initialize(usr);
	}
	
	private void initialize(User usr) {
		this.setName(EDIT_PANEL_COMPONENT_NAME);
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		userDao = DaoFactory.getInstance().getDao();
		this.user = usr;
		setFields();
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
			cancelButton.setText(Messages.getString("EditPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME);
			cancelButton.setActionCommand(CANCEL_COMMAND);
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("EditPanel.ok")); //$NON-NLS-1$
			okButton.setName(OK_BUTTON_COMPONENT_NAME);
			okButton.setActionCommand(OK_COMMAND);
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(2, 3));
			addLabeledField(fieldPanel, Messages.getString("EditPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("EditPanel.last_name"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("EditPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if (dayOfBirthField == null) {
			dayOfBirthField = new JTextField();
			dayOfBirthField.setName(DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		}
		return dayOfBirthField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName(LAST_NAME_FIELD_COMPONENT_NAME);
		}
		return lastNameField;
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName(FIRST_NAME_FIELD_COMPONENT_NAME);
		}
		return firstNameField;
	}
	
	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (OK_COMMAND.equalsIgnoreCase(e.getActionCommand())) {
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				parent.getDao().update(user);
			} catch (DatabaseExeption e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
			}
		}
		this.setVisible(false);
		parent.showBrowsePanel();
	}

	private void setFields() {
		getFirstNameField().setText(this.user.getFirstName());
		getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText(this.user.getLastName());
		getLastNameField().setBackground(bgColor);
		
		Format formatter = new SimpleDateFormat(DATE_FORMAT);
		getDateOfBirthField().setText(formatter.format(this.user.getDateOfBirth()));
		getDateOfBirthField().setBackground(bgColor);
	}

}
