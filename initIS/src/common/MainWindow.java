package common;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Usuario.Control.UsuarioDTO;
import Usuario.Control.tipoCuenta;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel menuPanel;
	private JPanel headerPanel;
	private JPanel principalPanel;
	
	private Boolean state_unregistered = true;
	private Boolean state_user = false;
	private Boolean state_developer = false;
	private Boolean state_admin = false;
	
	Controller _controller;
	
	public MainWindow(Controller c) {
		
		super("Geckos");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_controller = c;
		
		//Hace que el jframe se coloque en mitad de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		principalPanel = new JPanel();
		this.setVisible(true);
		
	}
	
	
	private void ponCosas() {
		
		this.getContentPane().removeAll();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(headerPanel, BorderLayout.NORTH);
		this.getContentPane().add(menuPanel, BorderLayout.WEST);
		this.getContentPane().add(principalPanel, BorderLayout.CENTER);
		
	}
		
	public void changeBoxes(UsuarioDTO user) {
		List<tipoCuenta> types = user.get_types();
	
		if (types == null) {
			state_unregistered = true;
			return;
		}
		state_unregistered = types.contains(tipoCuenta.unregistered) ?  true : false;
		state_user = types.contains(tipoCuenta.user) ?  true : false;
		state_developer = types.contains(tipoCuenta.developer) ?  true : false;
		state_admin = types.contains(tipoCuenta.admin) ?  true : false;
		
	}
	
	@SuppressWarnings("exports")
	public void reinicia(JPanel newPanel, UsuarioDTO user) {
		menuPanel = initMenuPanel();
		headerPanel = initHeaderPanel();
		if (newPanel != null)
			principalPanel = newPanel;
		
		changeBoxes(user);
		
		ponCosas();
		
		this.validate();
	}

	private JPanel initMenuPanel() {
		JPanel panel = new JPanel();

		GridLayout la = new GridLayout(18, 0);
		panel.setLayout(la);
		
		JButton buttonTienda = new JButton("Tienda");
		buttonTienda.addActionListener(new TiendaButton());
		panel.add(buttonTienda);
		buttonTienda.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton buttonBiblio = new JButton("Biblioteca");
		buttonBiblio.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonBiblio.addActionListener(new BibliotecaButton());
		panel.add(buttonBiblio);

		JButton buttonCom = new JButton("Comunidad");
		buttonCom.addActionListener(new ComunidadButton());
		buttonCom.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonCom);
		
		JButton buttonSoporte = new JButton("Soporte");
		buttonSoporte.addActionListener(new SoporteButton());
		buttonSoporte.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonSoporte);
		
		return panel;
	}

	@SuppressWarnings("serial")
	private JPanel initHeaderPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel west = new JPanel();
		
		//JButton buttonIcon = new JButton(new ImageIcon("./resources//usuario.png"));
		JButton buttonIcon = new JButton(new ImageIcon("./resources/usuario.png"));
		buttonIcon.addActionListener(new UserButton());
		west.add(buttonIcon);
		 
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		JLabel tipo = new JLabel("  ");
		JCheckBox unregistered = new JCheckBox("Unregistered  ");
		unregistered.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_unregistered; }});
		JCheckBox user = new JCheckBox("User");
		user.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_user; }});
		JCheckBox developer = new JCheckBox("Developer");
		developer.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_developer; }});
		JCheckBox admin = new JCheckBox("Admin");
		admin.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_admin; }});
		
		east.add(tipo);
		east.add(unregistered);
		east.add(user);
		east.add(developer);
		east.add(admin);
		
		panel.add(west, BorderLayout.WEST);
		panel.add(east, BorderLayout.EAST);
		//panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		return panel;
	}
	
	class TiendaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_controller.evento(EventoCommon.Tienda, null, null);
		}
	}

	class ComunidadButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			_controller.evento(EventoCommon.Comunidad, null, null);
		}
	}
	
	class SoporteButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			_controller.evento(EventoCommon.Soporte, null, null);
		}
	}
	
	class UserButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			_controller.evento(EventoCommon.Usuario, null, null);

		}
	}
	
	class BibliotecaButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_controller.evento(EventoCommon.Biblioteca, null, null);
		}
	}
	
}
