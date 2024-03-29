package Usuario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioControllerFacade;
import Usuario.Control.UsuarioDAO;
import Usuario.Control.UsuarioDAOJSON;
import Usuario.Control.UsuarioDTO;


public class MainWindowIniciarSesion extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTextArea username;
	private JPasswordField password;
    private JButton iniciarSesion;
    private JButton crearCuenta;
    
    private UsuarioDAO _dao;
    private UsuarioControllerFacade _cu;
    private JPanel _panel;
	
	public MainWindowIniciarSesion(UsuarioControllerFacade cu) {
		_dao = new UsuarioDAOJSON();
		_cu = cu;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		configPanel();
		createIniciarSesion();
	}
	
	private void configPanel() {

		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void createIniciarSesion () {
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
        
        //USERNAME
        JPanel userPanel = new JPanel();
        BoxLayout userLayout = new BoxLayout(userPanel, BoxLayout.X_AXIS);
        userPanel.setLayout(userLayout);
        
        JLabel userLabel = new JLabel();
        userLabel.setPreferredSize(new Dimension(125,20));
        userLabel.setText("Username:  ");
        
        username = new JTextArea();
        username.setPreferredSize(new Dimension(sizex,25));
        username.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        userPanel.add(userLabel);
        userPanel.add(username);
        
        //PASSWORD
        JPanel passwordPanel = new JPanel();
        BoxLayout passwordLayout = new BoxLayout(passwordPanel, BoxLayout.X_AXIS);
        passwordPanel.setLayout(passwordLayout);
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setPreferredSize(new Dimension(125,20));
        passwordLabel.setText("Password:  ");
        
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(sizex,25));
        password.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        
        JPanel botones = new JPanel();
        
        iniciarSesion = new JButton("INICIAR SESION");
        iniciarSesion.setPreferredSize(new Dimension((125 + sizex)/2, 20));
        iniciarSesion.addActionListener(new iniciarButton());
        
        crearCuenta = new JButton("CREAR CUENTA");
        crearCuenta.setPreferredSize(new Dimension((125 + sizex)/2, 20));
        crearCuenta.addActionListener(new crearButton());
        
        botones.add(iniciarSesion);
        botones.add(crearCuenta);
        
        generalPanel.add(userPanel);
        generalPanel.add(passwordPanel);
        generalPanel.add(botones);

        this.add(generalPanel);
        
	}
	
	class iniciarButton implements ActionListener {
		 @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){  
			 if (!username.getText().equals("") && !password.getText().equals("")) {
				
				 UsuarioDTO user = _dao.login(username.getText(), password.getText());
				 
				 if (user != null)
					 _cu.evento(EventoUsuario.PerfilUsuario, user);
				 else {
					 
					JFrame frame = new JFrame("ERROR");
					frame.setLayout(new BorderLayout());
					frame.setMinimumSize(new Dimension(800,200));
					frame.setPreferredSize(new Dimension(800,200));
					JOptionPane.showMessageDialog(frame, "El usuario introducido no se encuentra registrado en el aplicacion, o la contraseña no es correcta"); 
					 
				 }
				 
			 } else {
				 
				JFrame frame = new JFrame("ERROR");
				frame.setLayout(new BorderLayout());
				frame.setMinimumSize(new Dimension(800,200));
				frame.setPreferredSize(new Dimension(800,200));
				JOptionPane.showMessageDialog(frame, "Porfavor, introduzca un nombre de usuario y contraseña"); 
				 
			 }
         }  

	}
	
	class crearButton implements ActionListener {
		public void actionPerformed(ActionEvent e){  
			_cu.evento(EventoUsuario.BotonCrearCuenta, null);
        }  
	}

}