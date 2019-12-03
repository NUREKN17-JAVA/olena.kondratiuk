package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7040316964493927169L;

	private static final String DETAILS_PANEL_COMPONENT_NAME = "detailsPanel"; //$NON-NLS-1$
	
	private static final String FULL_NAME_FIELD_COMPONENT_NAME = "fullNameField"; //$NON-NLS-1$
    private static final String AGE_FIELD_COMPONENT_NAME = "ageField"; //$NON-NLS-1$
    private static final String CLOSE_BUTTON_COMPONENT_NAME = "closeButton"; //$NON-NLS-1$
	
	private MainFrame parent;
	private JPanel fieldPanel;
    private JButton closeButton;
    private JTextField fullNameField;
    private JTextField ageField;

    public DetailsPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        parent.showBrowsePanel();
    }

    public void showUserDetails(User user) {
        fullNameField.setText(user.getFullName());
        ageField.setText(String.valueOf(user.getAge()));
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setName(DETAILS_PANEL_COMPONENT_NAME);
        add(getFieldPanel(), BorderLayout.NORTH);
        add(getCloseButton(), BorderLayout.SOUTH);
    }

    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new JButton();
            closeButton.setText(Messages.getString("DetailsPanel.close")); //$NON-NLS-1$
            closeButton.setName(CLOSE_BUTTON_COMPONENT_NAME);
            closeButton.addActionListener(this);
        }
        return closeButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.full_name"), getFullNameField()); //$NON-NLS-1$
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.age"), getAgeField()); //$NON-NLS-1$
        }
        return fieldPanel;
    }

    private void addLabeledImmutableField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JTextField getFullNameField() {
        if (fullNameField == null) {
            fullNameField = new JTextField();
            fullNameField.setEditable(false);
            fullNameField.setName(FULL_NAME_FIELD_COMPONENT_NAME);
        }
        return fullNameField;
    }

    private JTextField getAgeField() {
        if (ageField == null) {
            ageField = new JTextField();
            ageField.setEditable(false);
            ageField.setName(AGE_FIELD_COMPONENT_NAME);
        }
        return ageField;
    }
}
