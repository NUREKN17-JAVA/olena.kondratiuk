package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;
import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;

public class EditPanel extends AddPanel {
	private static final long serialVersionUID = -3839976196695276719L;
	
	private static final String EDIT_PANEL_COMPONENT_NAME = "editPanel"; //$NON-NLS-1$
	private static final String OK_COMMAND = "ok"; //$NON-NLS-1$
	private static final String ERROR_MESSAGE = "Error"; //$NON-NLS-1$
	
	private User user;

    public EditPanel(MainFrame parent) {
        super(parent);
        setName(EDIT_PANEL_COMPONENT_NAME);
    }   

    protected void doAction(ActionEvent e) throws ParseException {
        if (OK_COMMAND.equalsIgnoreCase(e.getActionCommand())) {
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                Date date = format.parse(getDateOfBirthField().getText());
                user.setDateOfBirth(date);
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                throw e1;
            }
            try {
                parent.getUserDao().update(user);
            } catch (DatabaseExeption e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), ERROR_MESSAGE,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setUser(User user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLastName());
        getDateOfBirthField().setText(format.format(user.getDateOfBirth()));
    }    
}
