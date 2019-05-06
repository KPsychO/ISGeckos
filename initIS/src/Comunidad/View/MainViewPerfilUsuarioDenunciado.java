package Comunidad.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.UsuarioDTO;

public class MainViewPerfilUsuarioDenunciado extends JPanel implements ActionListener{
	
		private static final long serialVersionUID = 1L;

		private UsuarioDTO user;
		private UsuarioDTO usDen;
		private JLabel avatarLabel;
		private JLabel usernameLabel;
		private JLabel paisLabel;
		private JLabel tipoCuentaLabel;
		//private JLabel _estado;
		private JLabel descripcionLabel;
		//private JLabel _descripcion;
		private JButton denunciar;
		
		public MainViewPerfilUsuarioDenunciado (UsuarioDTO user, UsuarioDTO usDen) {
			this.user = user;
			this.usDen = usDen;
			initGUI();
			this.setVisible(true);
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
			JLabel name = new JLabel("Name: " + usDen.get_username());
			
			//JLabel level = new JLabel("Level: 0");
			//JLabel estado = new JLabel("Estado: bien");
			
			JLabel pais = new JLabel ("Pais: " + usDen.get_country());
			JLabel juegos = new JLabel ("Juegos en biblioteca: 5");
			
			name_level_estado.add(name);
			name_level_estado.add(pais);
			name_level_estado.add(juegos);
			
			avatar_name_level.add(icono);
			avatar_name_level.add(name_level_estado);
			
			//Desc
			JTextArea desc = new JTextArea(usDen.get_desc());
			desc.setPreferredSize(new Dimension(300, 100));
			desc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			desc.setEditable(false);
			//Modificar perfil
			
			izq.add(avatar_name_level);
			izq.add(desc);
			
			//Panel derecho
			JPanel der = new JPanel();
			der.setLayout(new GridLayout(1, 1));
			
			//Botones
			denunciar = new JButton("Denunciar");
			denunciar.setActionCommand("aceptar");
			denunciar.addActionListener(this);			
			
			der.add(denunciar);
			
			this.add(izq);
			this.add(new JSeparator());
			this.add(der);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("denunciar")) {
				firePropertyChange("DenunciarJugador", null, usDen);
			}
		}

}
