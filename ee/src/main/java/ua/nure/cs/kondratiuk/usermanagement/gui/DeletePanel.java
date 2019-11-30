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

public class DeletePanel  extends JPanel implements ActionListener {
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
	    this.setName("deletePanel");
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
	      cancelButton.setText("Cancel");
	      cancelButton.setName("cancelButton");
	      cancelButton.setActionCommand("cancel");
	      cancelButton.addActionListener(this);
	    }
	    return cancelButton;
	  }

	  private JButton getOkButton() {
	    if (okButton == null) {
	      okButton = new JButton();
	      okButton.setText("Ok");
	      okButton.setName("okButton");
	      okButton.setActionCommand("ok");
	      okButton.addActionListener(this);
	    }
	    return okButton;
	  }
	  
	  private JLabel getSureText() {
	    if (sureText == null) {
	      sureText = new JLabel();
	      sureText.setText("Are you sure to delete this user?");
	      sureText.setName("sureText");
	    }
	    return sureText;
	  }
	
	  @Override
	  public void actionPerformed(ActionEvent e) {
	    if ("ok".equalsIgnoreCase(e.getActionCommand())) {
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
