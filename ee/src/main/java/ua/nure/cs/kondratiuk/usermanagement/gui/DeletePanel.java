package ua.nure.cs.kondratiuk.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.Dao;
import ua.nure.cs.kondratiuk.usermanagement.db.DaoFactory;
import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;
import ua.nure.cs.kondratiuk.usermanagement.util.Messages;

public class DeletePanel  extends JPanel implements ActionListener {
	private static final String SURE_TEXT_COMPONENT_NAME = "sureText"; //$NON-NLS-1$
	private static final String DELETE_PANEL_COMPONENT_NAME = "deletePanel"; //$NON-NLS-1$
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton"; //$NON-NLS-1$
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton"; //$NON-NLS-1$
	private static final String CANCEL_COMMAND = "cancel"; //$NON-NLS-1$	
	private static final String OK_COMMAND = "ok"; //$NON-NLS-1$
	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton okButton;
	private User user;
	private Dao userDao;
	private JLabel sureText;
	
	public DeletePanel(MainFrame parent, User usr) {
	    this.parent = parent;
	    initialize(usr);
	}
	
	private void initialize(User usr) {
	    this.setName(DELETE_PANEL_COMPONENT_NAME);
	    this.setLayout(new BorderLayout());
	    this.add(getTextPanel(), BorderLayout.NORTH);
	    this.add(getButtonPanel(), BorderLayout.SOUTH);
	    userDao = DaoFactory.getInstance().getDao();
	    this.user = usr;
	}
	
	private JPanel getTextPanel() {
	    if (buttonPanel == null) {
	      buttonPanel = new JPanel();
	      buttonPanel.add(getSureText(), null);
	    }
	    return buttonPanel;
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
	      cancelButton.setText(Messages.getString("DeletePanel.cancel")); //$NON-NLS-1$
	      cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME);
	      cancelButton.setActionCommand(CANCEL_COMMAND);
	      cancelButton.addActionListener(this);
	    }
	    return cancelButton;
	  }

	  private JButton getOkButton() {
	    if (okButton == null) {
	      okButton = new JButton();
	      okButton.setText(Messages.getString("DeletePanel.ok")); //$NON-NLS-1$
	      okButton.setName(OK_BUTTON_COMPONENT_NAME);
	      okButton.setActionCommand(OK_COMMAND);
	      okButton.addActionListener(this);
	    }
	    return okButton;
	  }
	  
	  private JLabel getSureText() {
	    if (sureText == null) {
	      sureText = new JLabel();
	      sureText.setText(Messages.getString("DeletePanel.sure_text")); //$NON-NLS-1$
	      sureText.setName(SURE_TEXT_COMPONENT_NAME);
	    }
	    return sureText;
	  }
	
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    if (OK_COMMAND.equalsIgnoreCase(e.getActionCommand())) {
	      try {
	        userDao.delete(this.user);
	      } catch (DatabaseExeption e1) {
	        e1.printStackTrace();
	      }
	    }
	    this.setVisible(false);
	    parent.showBrowsePanel();
	  }
	
}
