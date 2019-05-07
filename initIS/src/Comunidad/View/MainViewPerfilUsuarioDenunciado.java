package Comunidad.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Usuario.Control.UsuarioDTO;

public class MainViewPerfilUsuarioDenunciado extends JPanel implements ActionListener{
	
		private static final long serialVersionUID = 1L;

		private JLabel avatarLabel;
		private JLabel usernameLabel;
		private JLabel paisLabel;
		private JLabel descripcionLabel;
		private JTextField descripcion;
		private JButton denunciar;
		private UsuarioDTO usDen;
		
		public MainViewPerfilUsuarioDenunciado (UsuarioDTO usDen) {
			this.usDen = usDen;
			initGUI();
			this.setVisible(true);
		}

		private void initGUI() {
			createPerfilUsuario();
		}
		
		private void createPerfilUsuario() {
			
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
	        usernameLabel.setText("Username: " + usDen.get_username());
	        
	        paisLabel = new JLabel();
	        paisLabel.setPreferredSize(new Dimension(200,100));
	        paisLabel.setText("Pais de residencia: " + usDen.get_country());
	        
	        usernamePanel.add(usernameLabel);
	        usernamePanel.add(paisLabel);
	        
	        avatarPanel.add(avatarLabel);
	        avatarPanel.add(usernamePanel);
	        
	        //DESCRIPCION
	        descripcionLabel = new JLabel();
	        descripcionLabel.setPreferredSize(new Dimension(200,20));
	        descripcionLabel.setText("Descripcion: ");
	        
	        descripcion = new JTextField();
	        descripcion.setPreferredSize(new Dimension(150,150));
	        descripcion.setEditable(false);
	        descripcion.setText(usDen.get_desc());
	        	        
	        izquierdaPanel.add(avatarPanel);
	        JPanel derechaPanel = new JPanel();
	        BoxLayout derechaLayout = new BoxLayout(derechaPanel, BoxLayout.Y_AXIS);
	        derechaPanel.setLayout(derechaLayout);
	        
	        denunciar = new JButton("Denunciar");
			denunciar.setActionCommand("denunciar");
			denunciar.addActionListener(this);
	        derechaPanel.add(denunciar);
	        izquierdaPanel.add(descripcionLabel);
	        izquierdaPanel.add(descripcion);
	        
	        generalPanel.add(izquierdaPanel);
	        generalPanel.add(derechaPanel);
	        
	        this.add(generalPanel);
	     
	        
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("denunciar")) {
				firePropertyChange("DenunciarJugador", null, usDen);
			}
		}

}
