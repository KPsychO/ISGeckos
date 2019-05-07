package Usuario.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.UsuarioDAO;
import Usuario.Control.UsuarioDAOJSON;


public class MainWindowIniciarSesion extends JPanel{
	private static final long serialVersionUID = 1L;

	/*MainViewTienda observed;
	private JPanel descr;
	private JPanel coment;
	private JPanel buttons;
	private JButton aceptar;
	private JButton cancelar;
	private JTextArea comenText;
	private JTextField descText;
	//private IncidenciasDAOJSON imJSON;
	private String user;
	
	//private Jpanel*/
	
	private JTextArea username;
	private JTextArea password;
	//private JCheckBox empresa;
    //private JCheckBox admin;
    private JButton iniciarSesion;
    private JButton crearCuenta;
    
    UsuarioDAO dao;
    private JPanel _panel;
	
	public MainWindowIniciarSesion() {
		dao = new UsuarioDAOJSON();
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		configPanel();
		createIniciarSesion();
	}
	
	private void configPanel() {
		//this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void createIniciarSesion () {
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
		
        /*
        JPanel izquierdaPanel = new JPanel();
        BoxLayout izquierdaLayout = new BoxLayout(izquierdaPanel, BoxLayout.Y_AXIS);
        izquierdaPanel.setLayout(izquierdaLayout);
		*/
        
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
        
        password = new JTextArea();
        password.setPreferredSize(new Dimension(sizex,25));
        password.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        
        //TIPO DE CUENTA
        /*
        JPanel tipoUsuario = new JPanel();
        
        BoxLayout tipoUsuarioLayout = new BoxLayout(tipoUsuario, BoxLayout.Y_AXIS);
        tipoUsuario.setLayout(tipoUsuarioLayout);
        //JLabel genres = new JLabel("GENEROS:  ");
        //genres.setPreferredSize(new Dimension(125,20));
        
        JPanel cajas = new JPanel();
        //GridLayout cajasL = new GridLayout(4, 2);
        //cajas.setLayout(cajasL);
        cajas.setPreferredSize(new Dimension(sizex, 75));
        empresa = new JCheckBox("Empresa desarrolladora");
        admin = new JCheckBox("Permisos administrador");
        
        cajas.add(empresa);
        cajas.add(admin);
        
        tipoUsuario.add(cajas);
        */
        
        JPanel botones = new JPanel();
        
        iniciarSesion = new JButton("INICIAR SESION");
        iniciarSesion.setPreferredSize(new Dimension((125 + sizex)/2, 20));
        iniciarSesion.addActionListener(new iniciarButton());
        
        /*
        izquierdaPanel.add(userPanel);
        izquierdaPanel.add(passwordPanel);
        //izquierdaPanel.add(tipoUsuario);
        izquierdaPanel.add(iniciarSesion);
        */
        
        crearCuenta = new JButton("CREAR CUENTA");
        crearCuenta.setPreferredSize(new Dimension((125 + sizex)/2, 20));
        crearCuenta.addActionListener(new crearButton());
        
        botones.add(iniciarSesion);
        botones.add(crearCuenta);
        
        generalPanel.add(userPanel);
        generalPanel.add(passwordPanel);
        generalPanel.add(botones);
        //generalPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        this.add(generalPanel);
        
	}
	
	class iniciarButton implements ActionListener {
		 public void actionPerformed(ActionEvent e){  
         	//if (!username.getText().equals("") && !password.getText().equals("")) {	
			 if (username.getText().equals("dev") && password.getText().equals("dev"))
         		firePropertyChange("PerfilUsuario", null, dao.login(username.getText(), password.getText()));
         	//}
         }  

	}
	
	class crearButton implements ActionListener {
		public void actionPerformed(ActionEvent e){  
        	firePropertyChange("AcuerdoSuscriptor", null, null);
        }  
	}

}