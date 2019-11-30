package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.Dao;
import ua.nure.cs.kondratiuk.usermanagement.db.DaoFactory;
import ua.nure.cs.kondratiuk.usermanagement.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {
	private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"; //$NON-NLS-1$
	private static final String DETAIL_PANEL_COMPONENT_NAME = "detailsPanel"; //$NON-NLS-1$
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
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	private User user;
	private Dao userDao;
	
	public DetailsPanel(MainFrame parent, User usr) {
		this.parent = parent;
		initialize(usr);
	}
	
	private void initialize(User usr) {
		this.setName(DETAIL_PANEL_COMPONENT_NAME);
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
			cancelButton.setText(Messages.getString("DetailsPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME);
			cancelButton.setActionCommand(CANCEL_COMMAND);
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("DetailsPanel.ok")); //$NON-NLS-1$
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
			addLabeledField(fieldPanel, Messages.getString("DetailsPanel.firs_name"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("DetailsPanel.last_name"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("DetailsPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName(DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		}
		return dateOfBirthField;
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
