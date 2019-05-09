package Usuario.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.ControllerUsuario;
import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioDTO;

public class MainWindowCrearCuenta extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel usernameLabel;
	private JTextArea username;
	private JLabel passwordLabel;
	private JTextArea password;
	private JLabel confirmPasswordLabel;
	private JTextArea confirmPassword;
	private JLabel emailLabel;
	private JTextArea email;
	private JLabel confirmEmailLabel;
	private JTextArea confirmEmail;
	private JLabel countryLabel;
	private JTextArea country;
	private JTextArea descShortField;
	private JCheckBox dev;
	private JCheckBox admin;
	private JButton ok;
	
	private ControllerUsuario _cu;
	
	public MainWindowCrearCuenta (ControllerUsuario cu) {
		_cu = cu;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createCrearCuenta();
	}
	
	private void createCrearCuenta() {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
        
        //USERNAME
        JPanel usernamePanel = new JPanel();
        BoxLayout usernameLayout = new BoxLayout(usernamePanel, BoxLayout.X_AXIS);
        usernamePanel.setLayout(usernameLayout);
        
        usernameLabel = new JLabel();
        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameLabel.setText("Username: ");
        
        username = new JTextArea();
        username.setPreferredSize(new Dimension(sizex,25));
        username.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        
        //PASSWORD
        JPanel passwordPanel = new JPanel();
        BoxLayout passwordLayout = new BoxLayout(passwordPanel, BoxLayout.X_AXIS);
        passwordPanel.setLayout(passwordLayout);
        
        passwordLabel = new JLabel();
        passwordLabel.setPreferredSize(new Dimension(200,20));
        passwordLabel.setText("Password:  ");
        
        password = new JTextArea();
        password.setPreferredSize(new Dimension(sizex,25));
        password.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        
        //CONFIRMPASSWORD
        JPanel confirmPasswordPanel = new JPanel();
        BoxLayout confirmPasswordLayout = new BoxLayout(confirmPasswordPanel, BoxLayout.X_AXIS);
        confirmPasswordPanel.setLayout(confirmPasswordLayout);
        
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setPreferredSize(new Dimension(200,20));
        confirmPasswordLabel.setText("Confirm password:  ");
        
        confirmPassword = new JTextArea();
        confirmPassword.setPreferredSize(new Dimension(sizex,25));
        confirmPassword.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPassword);
        
        //EMAIL
        JPanel emailPanel = new JPanel();
        BoxLayout emailLayout = new BoxLayout(emailPanel, BoxLayout.X_AXIS);
        emailPanel.setLayout(emailLayout);
        
        emailLabel = new JLabel();
        emailLabel.setPreferredSize(new Dimension(200,20));
        emailLabel.setText("Email:  ");
        
        email = new JTextArea();
        email.setPreferredSize(new Dimension(sizex,25));
        email.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        emailPanel.add(emailLabel);
        emailPanel.add(email);
        
        //CONFIRMEMAIL
        JPanel confirmEmailPanel = new JPanel();
        BoxLayout confirmEmailLayout = new BoxLayout(confirmEmailPanel, BoxLayout.X_AXIS);
        confirmEmailPanel.setLayout(confirmEmailLayout);
        
        confirmEmailLabel = new JLabel();
        confirmEmailLabel.setPreferredSize(new Dimension(200,20));
        confirmEmailLabel.setText("Confirm email:  ");
        
        confirmEmail = new JTextArea();
        confirmEmail.setPreferredSize(new Dimension(sizex,25));
        confirmEmail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        confirmEmailPanel.add(confirmEmailLabel);
        confirmEmailPanel.add(confirmEmail);
        
        //COUNTRY
        JPanel countryPanel = new JPanel();
        BoxLayout countryLayout = new BoxLayout(countryPanel, BoxLayout.X_AXIS);
        countryPanel.setLayout(countryLayout);
        
        countryLabel = new JLabel();
        countryLabel.setPreferredSize(new Dimension(200,20));
        countryLabel.setText("Pais:  ");
        
        country = new JTextArea();
        country.setPreferredSize(new Dimension(sizex,25));
        country.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        countryPanel.add(countryLabel);
        countryPanel.add(country);
        
        //DESC	SHORT
        JPanel descPanel = new JPanel();
        BoxLayout descLayout = new BoxLayout(descPanel, BoxLayout.X_AXIS);
        descPanel.setLayout(descLayout);
        
        JLabel descs = new JLabel();
        descs.setPreferredSize(new Dimension(200,20));
        descs.setText("Descripcion:  ");
        
        descShortField = new JTextArea();
        descShortField.setWrapStyleWord(true);
        descShortField.setLineWrap(true);
        descShortField.setPreferredSize(new Dimension(sizex,75));
        descShortField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        descPanel.add(descs);
        descPanel.add(descShortField);
        
        dev = new JCheckBox("Developer");
        admin = new JCheckBox("Admin");
        ok = new JButton ("CONTINUAR");
        
        JPanel botones = new JPanel();
        botones.add(dev);
        botones.add(admin);
        botones.add(ok);
        
       
        ok.addActionListener(new continuarButton());
        
        generalPanel.add(usernamePanel);
        generalPanel.add(passwordPanel);
        generalPanel.add(confirmPasswordPanel);
        generalPanel.add(emailPanel);
        generalPanel.add(confirmEmailPanel);
        generalPanel.add(countryPanel);
        generalPanel.add(descPanel);
        generalPanel.add(botones);
       
        this.add(generalPanel);
	}
	
	class continuarButton implements ActionListener {
		public void actionPerformed(ActionEvent e){  
			 if (!username.getText().isEmpty() && !password.getText().isEmpty() && !email.getText().isEmpty()) {

				if (password.getText().equals(confirmPassword.getText()) && email.getText().equals(confirmEmail.getText())) {
				 
					_cu.storeUser(UUID.randomUUID().toString().substring(0, 23), username.getText(), password.getText(), email.getText(),
							country.getText(), 0, "", true, dev.isSelected(), admin.isSelected());
					
					_cu.evento(EventoUsuario.IniciarSesion, null);
				}
			 }
		 }
	}
}