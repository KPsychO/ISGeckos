package Juego.View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class JuegoDeveloper extends JPanel{
	private static final long serialVersionUID = 1L;

	JuegoDTO _dto;
	
	JLabel _icon;
	JPanel _contents;
	JPanel _caract;
	JPanel _desc;
	
	public JuegoDeveloper(JuegoDTO dto) {
		_dto = dto;
		initGUI();
	}

	private void initGUI() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMaximumSize(new Dimension(600, 200));
		this.setMinimumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		
		_icon = new JLabel(new ImageIcon("./src/resources/game_icon.jpg"));	
			
		JLabel name = new JLabel();
		name.setText("Name: " + _dto.get_title());
		
		JLabel fecha = new JLabel();
		fecha.setText("Fecha: " + _dto.get_date());
		
		_caract = new JPanel();
		_caract.setLayout(new BoxLayout(_caract, BoxLayout.Y_AXIS));
	    _caract.setAlignmentX(Component.CENTER_ALIGNMENT);
		_caract.add(name);
		_caract.add(new JLabel(" "));
		_caract.add(fecha);
		
		_desc = new JPanel();
		_desc.setLayout(new GridLayout(3, 1));
		_desc.setMaximumSize(new Dimension(150, 100));
		_desc.setMinimumSize(new Dimension(150, 100));
		_desc.setPreferredSize(new Dimension(150, 100));

		JButton tienda = new JButton("Ver en tienda");
		JButton tienda2 = new JButton("Actualizar");
		JButton tienda3= new JButton("Eliminar");
		
		tienda.addActionListener(new VerEnTienda());
		tienda2.addActionListener(new Actualizar());
		tienda3.addActionListener(new Eliminar());
		
		_desc.add(tienda);
		_desc.add(tienda2);
		_desc.add(tienda3);
		
		this.add(_icon);
		
		JSeparator js = new JSeparator();
		JSeparator js2 = new JSeparator();
		
		this.add(js);
		this.add(_caract);
		this.add(js2);
		this.add(_desc);
		
	}
	
	class VerEnTienda implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("VerEnTienda", null, _dto);
		}
	}
	
	class Actualizar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("ActualizarJuego", null, _dto);
		}
	}
	
	class Eliminar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("EliminarJuego", null, _dto);
		}
	}

}
