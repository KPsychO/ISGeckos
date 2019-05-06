package Usuario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Biblioteca.Control.BibliotecaDTO;
import Usuario.Control.UsuarioDTO;

public class MainWindowPerfilUsuario extends JPanel{
	private static final long serialVersionUID = 1L;

	private UsuarioDTO _dto;
	
	private JPanel _icon;
	private JPanel _buttons;
	private JPanel _desc;
	
	//Botones
	private JButton insignias;
	private JButton biblioteca;
	private JButton cerrarSesion;
	private JButton elimCuenta;
	private JButton modPerfil; 
	private JButton formulario;
	private JButton publicacion;
	
	public MainWindowPerfilUsuario(UsuarioDTO dto) {
		_dto = dto;
		initGUI();
	}

	private void initGUI() {
		
		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		
		//Avatar, nombre y nivel?
		JPanel avatar_name_level = new JPanel();
		avatar_name_level.setPreferredSize(new Dimension(300, 120));
		JLabel icono = new JLabel(new ImageIcon("./src/resources/usuario.png"));
		icono.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		icono.setPreferredSize(new Dimension(120, 120));
		icono.setAlignmentX(LEFT_ALIGNMENT);
		
		//Nombre y nivel
		JPanel name_level_estado = new JPanel();
		name_level_estado.setLayout(new BoxLayout(name_level_estado, BoxLayout.Y_AXIS));
		JLabel name = new JLabel("Name: " + _dto.get_username());
		
		//JLabel level = new JLabel("Level: 0");
		//JLabel estado = new JLabel("Estado: bien");
		
		JLabel pais = new JLabel ("Pais: " + _dto.get_country());
		JLabel juegos = new JLabel ("Juegos en biblioteca: 5");
		
		name_level_estado.add(name);
		name_level_estado.add(pais);
		name_level_estado.add(juegos);
		
		avatar_name_level.add(icono);
		avatar_name_level.add(name_level_estado);
		
		//Desc
		JTextArea desc = new JTextArea(_dto.get_desc());
		desc.setPreferredSize(new Dimension(300, 100));
		desc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//Modificar perfil
		
		izq.add(avatar_name_level);
		izq.add(desc);
		
		//Panel derecho
		JPanel der = new JPanel();
		der.setLayout(new GridLayout(7, 1));
		
		//Botones
		insignias = new JButton("INSIGNIAS");
		insignias.addActionListener(new insigniasButton());
		
		biblioteca = new JButton("BIBLIOTECA");
		biblioteca.addActionListener(new bibliotecaButton());
		
		cerrarSesion = new JButton("CERRAR SESION");
		cerrarSesion.addActionListener(new cerrarSesionButton());
		
		elimCuenta = new JButton("ELIMINAR CUENTA");
		elimCuenta.addActionListener(new elimCuentaButton());
		
		modPerfil = new JButton("MODIFICAR PERFIL");
		modPerfil.addActionListener(new modPerfilButton());
		
		formulario = new JButton("FORMULARIO");
		formulario.addActionListener(new formularioButton());
		
		publicacion = new JButton("PUBLICACION");
		publicacion.addActionListener(new publicacionButton());
		
		setButtons();
		
		der.add(insignias);
		der.add(biblioteca);
		
		//Aqui iran publicacion y formulario
		der.add(formulario);
		der.add(publicacion);
		
		der.add(cerrarSesion);
		der.add(modPerfil);
		der.add(elimCuenta);
		
		this.add(izq);
		this.add(new JSeparator());
		this.add(der);
		
	}
	
	private void setButtons() {
		formulario.setEnabled(_dto.isDev());
		publicacion.setEnabled(_dto.isAdmin());
	}

	class insigniasButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("Insignias", null, _dto);
		}
	}
	class bibliotecaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("Biblioteca", null, new BibliotecaDTO(_dto));
		}
	}
	class cerrarSesionButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("CerrarSesion", null, _dto);
		}
	}
	class elimCuentaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("ElimCuenta", null, _dto);
		}
	}
	class modPerfilButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("ModPerfil", null, _dto);
		}
	}
	class formularioButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("Formulario", null, _dto);
		}
	}
	class publicacionButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("Publicacion", null, _dto);
		}
	}

}
