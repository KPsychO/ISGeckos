package Juego.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.Control.JuegoDTO;

public class JuegoDeveloper extends JPanel {

	private static final long serialVersionUID = 1L;

	public JuegoDeveloper(JuegoDTO dto) {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 200));
		
		JLabel icon = new JLabel(new ImageIcon("./src/resources/game_icon.jpg"));
		JLabel name = new JLabel(dto.get_title());
		JButton boton = new JButton("HI");
		boton.setPreferredSize(new Dimension(50, 25));
		
		panel.add(icon, BorderLayout.WEST);
		panel.add(name, BorderLayout.CENTER);
		panel.add(boton, BorderLayout.EAST);
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
}
