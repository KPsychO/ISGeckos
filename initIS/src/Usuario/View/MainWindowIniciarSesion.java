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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

//import Formulario.Control.FormularioDTO;
import Usuario.Control.UsuarioDTO;
//import IncidenciasMejoras.Control.IncidenciasDAOJSON;
//import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
//import Tienda.View.MainViewTienda;

public class MainWindowIniciarSesion extends JPanel implements ActionListener{
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
	private JCheckBox empresa;
    private JCheckBox admin;
    private JButton iniciarSesion;
    private JButton crearCuenta;
    
    UsuarioDTO _usuarioDTO;
	JPanel _panel;
	
	public MainWindowIniciarSesion() {
		//_usuarioDTO = new UsuarioDTO();
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		configPanel();
		createIniciarSesion();
	}
	/*
	public void CreateFocusListenerForFields(JTextField txt)
	{
	    txt.addFocusListener(new FocusListener() 
	    {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	txt.setText("");
	        }

			@Override
			public void focusLost(FocusEvent e) {

			}
	    });
	}
	
	public void CreateFocusListenerForFields(JTextArea txt)
	{
	    txt.addFocusListener(new FocusListener() 
	    {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	txt.setText("");
	        }

			@Override
			public void focusLost(FocusEvent e) {

			}
	    });
	}
	*/
	
	private void configPanel() {
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void createIniciarSesion () {
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.X_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 500;
		
        JPanel izquierdaPanel = new JPanel();
        BoxLayout izquierdaLayout = new BoxLayout(izquierdaPanel, BoxLayout.Y_AXIS);
        izquierdaPanel.setLayout(izquierdaLayout);
 
        
        //USERNAME
        JPanel userPanel = new JPanel();
        BoxLayout userLayout = new BoxLayout(userPanel, BoxLayout.X_AXIS);
        userPanel.setLayout(userLayout);
        
        JLabel userLabel = new JLabel();
        userLabel.setPreferredSize(new Dimension(125,20));
        userLabel.setText("USERNAME:  ");
        
        username = new JTextArea();
        username.setPreferredSize(new Dimension(sizex,250));
        username.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        userPanel.add(userLabel);
        userPanel.add(username);
        
        //PASSWORD
        JPanel passwordPanel = new JPanel();
        BoxLayout passwordLayout = new BoxLayout(passwordPanel, BoxLayout.X_AXIS);
        passwordPanel.setLayout(passwordLayout);
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setPreferredSize(new Dimension(125,20));
        passwordLabel.setText("PASSWORD:  ");
        
        password = new JTextArea();
        password.setPreferredSize(new Dimension(sizex,250));
        password.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        
        //TIPO DE CUENTA
        JPanel tipoUsuario = new JPanel();
        
        BoxLayout tipoUsuarioLayout = new BoxLayout(tipoUsuario, BoxLayout.Y_AXIS);
        tipoUsuario.setLayout(tipoUsuarioLayout);
        //JLabel genres = new JLabel("GENEROS:  ");
        //genres.setPreferredSize(new Dimension(125,20));
        
        JPanel cajas = new JPanel();
        GridLayout cajasL = new GridLayout(4, 2);
        cajas.setLayout(cajasL);
        cajas.setPreferredSize(new Dimension(sizex, 75));
        empresa = new JCheckBox("Empresa desarrolladora");
        admin = new JCheckBox("Permisos administrador");
        
        cajas.add(empresa);
        cajas.add(admin);
        
        //generos.add(genres);
        tipoUsuario.add(cajas);
        
        //BOTONES
        //JPanel botones = new JPanel();
        //BoxLayout botonesL = new BoxLayout(botones, BoxLayout.Y_AXIS);
        //botones.setLayout(botonesL);
        
        //JPanel logro_name = new JPanel();
        //JLabel logro_title = new JLabel("LOGROS:  ");
        //logro_title.setPreferredSize(new Dimension(125, 20));
        
        iniciarSesion = new JButton("INICIAR SESION");
        iniciarSesion.setPreferredSize(new Dimension(sizex/2, 20));
        iniciarSesion.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	/*if (!name_logro.getText().equals("") && !obt_logro.getText().equals("")) {
            		DefaultTableModel model = (DefaultTableModel) tabla_logros.getModel();
            		model.addRow(new Object[]{name_logro.getText(), obt_logro.getText()});
            	}
            	*/
        }  
        });
        
        izquierdaPanel.add(userPanel);
        izquierdaPanel.add(passwordPanel);
        izquierdaPanel.add(tipoUsuario);
        izquierdaPanel.add(iniciarSesion);
        
        crearCuenta = new JButton("CREAR CUENTA");
        crearCuenta.setPreferredSize(new Dimension(sizex / 2, 20));
        crearCuenta.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	//removeSelectedRows();
        }  
        });
        
        //botones.add(iniciarSesion);
        //botones.add(crearCuenta);
        
        generalPanel.add(izquierdaPanel);
        generalPanel.add(crearCuenta);
        
        //campos.add(passwordPanel);
        //campos.add(tipoUsuario);
        //generalPanel.add(botones);
        
        this.add(generalPanel);
        
	}
	public void actionPerformed(ActionEvent e) {
		/*if (e.getActionCommand().equals("aceptar")) {
			//imJSON.getListIncidencias();
			//Aqui faltan los usuarios y el denunciado
			//imJSON.insertarIncidencia(new IncidenciasMejorasDTO ("IncJug", user, null, null, descText.getText(), comenText.getText()));
			JOptionPane.showMessageDialog(getParent(), "Has enviado la Denuncia/Incidencia");
			firePropertyChange("Soporte", null, null);
		}
		else if (e.getActionCommand().equals("cancelar")) {
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la Denuncia/Incidencia");
			firePropertyChange("Soporte", null, null);
			firePropertyChange("Hola", null, null);
			observed.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
		        public void propertyChange(PropertyChangeEvent e) {
					firePropertyChange(e.getPropertyName(), e.getOldValue(), e.getNewValue());
		        }
		    });*/
		}
}