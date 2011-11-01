package gui.listeners;

import gui.Validator;

import gui.panels.ContactListPanel;
import gui.panels.contactinfo.ContactInfoPanel;
import gui.panels.contactinfo.PhoneNumberPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import sql.JDBCConnection;

import contacts.Contact;

public class NewContactActionListener implements ActionListener{
	
	//Panels
	private ContactInfoPanel  contactInfo;
	private ContactListPanel contactList;
	//contact
	//private Contact contact;
	//Field Validator
	private Validator validator;
	

	public NewContactActionListener(JPanel contactInfo, JPanel contactList){
		this.contactInfo = (ContactInfoPanel) contactInfo;
		this.contactList = (ContactListPanel) contactList;
		validator = new Validator(contactInfo);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String errorMessages = validator.validateFields();
			if(errorMessages.equals("")){
				Contact contact = contactInfo.getContact();
				JDBCConnection myConnection = new JDBCConnection();
				myConnection.addContact(contact);
			} else {
				JOptionPane.showMessageDialog(contactInfo, errorMessages, "Error", JOptionPane.ERROR_MESSAGE);
			}

	}

}
