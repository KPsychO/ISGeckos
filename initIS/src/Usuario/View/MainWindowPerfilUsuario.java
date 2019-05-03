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

public class MainWindowPerfilUsuario extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JLabel avatar;
	private JLabel username;
	private JLabel pais;
	private JLabel estado;
	private JLabel _estado;
	private JLabel descripcion;
	private JLabel _descripcion;
	private JButton modificar;
	private JButton insignias;
	private JButton biblioteca;
	private JButton cerrar;
	private JButton eliminar;
	
	public MainWindowPerfilUsuario () {
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createPerfilUsuario();
	}
	
	private void createPerfilUsuario() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
