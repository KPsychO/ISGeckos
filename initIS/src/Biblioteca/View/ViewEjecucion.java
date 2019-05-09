package Biblioteca.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Biblioteca.Control.BibliotecaController;
import Biblioteca.Control.JuegoEnPropiedadDTO;

public class ViewEjecucion extends JPanel{	
	
	private static final long serialVersionUID = 1L;
	
	BibliotecaController _controller;
	JuegoEnPropiedadDTO _juego;
	
	public ViewEjecucion(JuegoEnPropiedadDTO juego, BibliotecaController controller) {
		_controller = controller;
		_juego = juego;
		
		initGUI();
		this.setVisible(true);
	}

	private void initGUI() {
		JFrame frame = new JFrame(_juego.get_title());
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(1000,800));
		frame.setPreferredSize(new Dimension(1000,800));
		
		JLabel label = new JLabel("El juego \"" + _juego.get_title() + "\" se esta ejecutando", SwingConstants.CENTER);
		
		frame.add(label, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
