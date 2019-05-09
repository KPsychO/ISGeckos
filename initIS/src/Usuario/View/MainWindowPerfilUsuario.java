package Usuario.View;

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
import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioControllerFacade;
import Usuario.Control.UsuarioDTO;

public class MainWindowPerfilUsuario extends JPanel{
	private static final long serialVersionUID = 1L;

	private UsuarioDTO _dto;
	
	//Botones
	@SuppressWarnings("unused")
	private JButton insignias;
	private JButton biblioteca;
	private JButton cerrarSesion;
	private JButton elimCuenta;
	private JButton modPerfil; 
	private JButton formulario;
	private JButton publicacion;
	private JButton developer;
	private JButton revisionMensajes;
	
	private UsuarioControllerFacade _cu;
	
	public MainWindowPerfilUsuario(UsuarioDTO dto, UsuarioControllerFacade cu) {
		_cu = cu;
		_dto = dto;
		initGUI();
	}

	private void initGUI() {
		
		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		
		//Avatar, nombre y nivel?
		JPanel avatar_name_level = new JPanel();
		avatar_name_level.setPreferredSize(new Dimension(300, 120));
		
		String path = _dto.getAvatarPath();
		ImageIcon img;
		
		if (path == null)
			img = new ImageIcon("./src/resources/usuario.png");
		else 
			img = new ImageIcon(path);
		
        JLabel avatarLabel = new JLabel(img, JLabel.CENTER);
        avatarLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        avatarLabel.setPreferredSize(new Dimension(120, 120));
        avatarLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		//Nombre y nivel
		JPanel name_level_estado = new JPanel();
		name_level_estado.setLayout(new BoxLayout(name_level_estado, BoxLayout.Y_AXIS));
		JLabel name = new JLabel("Name: " + _dto.get_username());
				
		JLabel pais = new JLabel ("Pais: " + _dto.get_country());
		JLabel juegos = new JLabel ("Juegos en biblioteca: 5");
		
		name_level_estado.add(name);
		name_level_estado.add(pais);
		name_level_estado.add(juegos);
		
		avatar_name_level.add(avatarLabel);
		avatar_name_level.add(name_level_estado);
		
		//Desc
				
		JTextArea desc = new JTextArea(_dto.get_desc());
		desc.setPreferredSize(new Dimension(300, 100));
		desc.setEditable(false);
		desc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		desc.setWrapStyleWord(true);
		desc.setLineWrap(true);
		
		//Modificar perfil
		izq.add(avatar_name_level);
		izq.add(desc);
		
		//Panel derecho
		JPanel der = new JPanel();
		der.setLayout(new GridLayout(8, 1));
		
		//Botones
		
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
		
		developer = new JButton("DESARROLLADORA");
		developer.addActionListener(new desarrolladoraButton());
		
		revisionMensajes = new JButton("INCIDENCIAS/DENUNCIAS");
		revisionMensajes.addActionListener(new inciMejButton());
		
		setButtons();
		
		der.add(biblioteca);
		
		//Aqui iran publicacion y formulario
		der.add(formulario);
		der.add(publicacion);
		
		der.add(cerrarSesion);
		der.add(modPerfil);
		der.add(elimCuenta);
		der.add(developer);
		der.add(revisionMensajes);
		
		this.add(izq);
		this.add(new JSeparator());
		this.add(der);		
	}
	
	private void setButtons() {
		formulario.setEnabled(_dto.isDev());
		developer.setEnabled(_dto.isDev());
		publicacion.setEnabled(_dto.isAdmin());
		revisionMensajes.setEnabled(_dto.isAdmin());
	}

	class bibliotecaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.Biblioteca, _dto);
		}
	}
	class cerrarSesionButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.CerrarSesion, _dto);
		}
	}
	class elimCuentaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.EliminarCuenta, _dto);
		}
	}
	class modPerfilButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.ModificarCuenta, _dto);
		}
	}
	class formularioButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.Formulario, _dto);
		}
	}
	class publicacionButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.Publicacion, _dto);
		}
	}
	class desarrolladoraButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.Desarrolladora, _dto);
		}
	}
	class inciMejButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_cu.evento(EventoUsuario.RevIncMej, _dto);
		}
	}

}
