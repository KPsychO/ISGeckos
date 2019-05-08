package Usuario.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.UsuarioDTO;

public class MainWindowEliminarCuenta extends JPanel{
	private static final long serialVersionUID = 1L;

	private UsuarioDTO _dto;
	
	private JLabel passwordLabel;
	private JTextArea password;
	private JLabel confirmPasswordLabel;
	private JTextArea confirmPassword;
	private JCheckBox ok;
	//private JLabel confirmar;
	private JButton eliminar;
	
	public MainWindowEliminarCuenta (UsuarioDTO dto) {
		_dto = dto;
		initGUI();
		//this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createEliminarCuenta();
	}
	
	private void createEliminarCuenta() {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
        
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
        
        ok = new JCheckBox("Proceder a la eliminacion de mi cuenta");
        eliminar = new JButton ("ELIMINAR CUENTA");
        eliminar.addActionListener(new continuarButton());
        
        generalPanel.add(passwordPanel);
        generalPanel.add(confirmPasswordPanel);
        generalPanel.add(ok);
        generalPanel.add(eliminar);
        
        this.add(generalPanel);
        
	}
	
	class continuarButton implements ActionListener {
		 public void actionPerformed(ActionEvent e){
			 if (ok.isSelected()) {
				 if (!password.getText().isEmpty() && password.getText().equals(confirmPassword.getText())) {
					 if (password.getText().equals(_dto.get_password())) {
						 
						 
						 //ELIMINAR USUARIO DE LA LISTA _dto.eliminarUsuario();
						 firePropertyChange("EliminarCuenta", null, null);
					 }
					 else {
							String tipoError = "La contrasena introducida no es correcta";
							JOptionPane.showMessageDialog(MainWindowEliminarCuenta.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
						}
				 }
				 else {
						String tipoError = "Las contrasenas introducidas no coinciden";
						JOptionPane.showMessageDialog(MainWindowEliminarCuenta.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
					}
			 }
			 else {
				String tipoError = "Debes marcar la casilla para continuar";
				JOptionPane.showMessageDialog(MainWindowEliminarCuenta.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
			}
		 }
	}
}
