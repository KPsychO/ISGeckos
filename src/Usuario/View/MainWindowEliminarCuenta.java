package Usuario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

//import Formulario.Control.FormularioDTO;
import Usuario.Control.UsuarioDTO;
//import IncidenciasMejoras.Control.IncidenciasDAOJSON;
//import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
//import Tienda.View.MainViewTienda;

public class MainWindowEliminarCuenta extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JLabel passwordLabel;
	private JTextArea password;
	private JLabel confirmPasswordLabel;
	private JTextArea confirmPassword;
	private JCheckBox ok;
	//private JLabel confirmar;
	private JButton eliminar;
	
	public MainWindowEliminarCuenta () {
		initGUI();
		this.setVisible(true);
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
        eliminar.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	if (ok.isSelected()) {
            		firePropertyChange("IniciarSesion", null, null);
            	}
            }  
        });
        
        generalPanel.add(passwordPanel);
        generalPanel.add(confirmPasswordPanel);
        generalPanel.add(ok);
        generalPanel.add(eliminar);
        
        this.add(generalPanel);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
