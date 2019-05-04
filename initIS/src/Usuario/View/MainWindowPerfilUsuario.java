package Usuario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import Usuario.Control.*;
//import IncidenciasMejoras.Control.IncidenciasDAOJSON;
//import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
//import Tienda.View.MainViewTienda;

public class MainWindowPerfilUsuario extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JLabel avatarLabel;
	private JLabel usernameLabel;
	private JLabel paisLabel;
	private JLabel tipoCuentaLabel;
	//private JLabel _estado;
	private JLabel descripcionLabel;
	//private JLabel _descripcion;
	private JButton modificar;
	private JButton insignias;
	private JButton biblioteca;
	private JButton cerrar;
	private JButton eliminar;
	
	public MainWindowPerfilUsuario (UsuarioDTO dto) {
		initGUI(dto);
		this.setVisible(true);
	}

	private void initGUI(UsuarioDTO dto) {
		//configPanel();
		createPerfilUsuario(dto);
	}
	
	private void createPerfilUsuario(UsuarioDTO dto) {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.X_AXIS);
        generalPanel.setLayout(generalLayout);
        //int sizex = 200;
        
        JPanel izquierdaPanel = new JPanel();
        BoxLayout izquierdaLayout = new BoxLayout(izquierdaPanel, BoxLayout.Y_AXIS);
        izquierdaPanel.setLayout(izquierdaLayout);
        
        JPanel avatarPanel = new JPanel();
        BoxLayout avatarLayout = new BoxLayout(avatarPanel, BoxLayout.X_AXIS);
        avatarPanel.setLayout(avatarLayout);
        
        ImageIcon img = new ImageIcon("./src/resources/usuario.png");
        avatarLabel = new JLabel(img, JLabel.CENTER);
        
        //USERNAME & PAIS
        JPanel usernamePanel = new JPanel();
        BoxLayout usernameLayout = new BoxLayout(usernamePanel, BoxLayout.Y_AXIS);
        usernamePanel.setLayout(usernameLayout);
        
        usernameLabel = new JLabel();
        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameLabel.setText("Username: " + dto.get_username());
        
        paisLabel = new JLabel();
        paisLabel.setPreferredSize(new Dimension(200,20));
        paisLabel.setText("Pais de residencia: " + dto.get_country());
        
        usernamePanel.add(usernameLabel);
        usernamePanel.add(paisLabel);
        
        avatarPanel.add(avatarLabel);
        avatarPanel.add(usernamePanel);
        
        //TIPO DE CUENTA
        tipoCuentaLabel = new JLabel();
        tipoCuentaLabel.setPreferredSize(new Dimension(200,20));
        tipoCuentaLabel.setText("Tipo de cuenta: " + dto.get_types());
        
        //DESCRIPCION
        descripcionLabel = new JLabel();
        descripcionLabel.setPreferredSize(new Dimension(200,200));
        descripcionLabel.setText("Descripcion: " + dto.get_desc());
        
        modificar = new JButton ("MODIFICAR PERFIL");
        
        izquierdaPanel.add(avatarPanel);
        izquierdaPanel.add(tipoCuentaLabel);
        izquierdaPanel.add(descripcionLabel);
        izquierdaPanel.add(modificar);
        
        JPanel derechaPanel = new JPanel();
        BoxLayout derechaLayout = new BoxLayout(derechaPanel, BoxLayout.Y_AXIS);
        derechaPanel.setLayout(derechaLayout);
        
        insignias = new JButton ("INSIGNIAS");
        biblioteca = new JButton ("BIBLIOTECA");
        cerrar = new JButton ("CERRAR SESION");
        eliminar = new JButton ("ELIMINAR CUENTA");
        
        derechaPanel.add(insignias);
        derechaPanel.add(biblioteca);
        derechaPanel.add(cerrar);
        derechaPanel.add(eliminar);
        
        generalPanel.add(izquierdaPanel);
        generalPanel.add(derechaPanel);
        
        this.add(generalPanel);
     
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
