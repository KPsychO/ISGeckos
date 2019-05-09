package Biblioteca.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Biblioteca.Control.BibliotecaControllerFacade;
import Biblioteca.Control.JuegoEnPropiedadDTO;

public class ViewInstalar extends JPanel{

	BibliotecaControllerFacade _bc;
	JuegoEnPropiedadDTO _juego;
	
	public ViewInstalar(JuegoEnPropiedadDTO juego, BibliotecaControllerFacade controller) {
		_bc = controller;
		_juego = juego;
		
		initGUI();
		this.setVisible(true);
	}
	
	public void initGUI() {
		JFrame frame = new JFrame("Error");
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(300,100));
		frame.setPreferredSize(new Dimension(300,100));
		
		JLabel label = new JLabel("Error. Instale primero", SwingConstants.CENTER);
		
		JButton button = new JButton("INSTALAR");
		button.setPreferredSize(new Dimension(100, 30));
		button.setMaximumSize(new Dimension(100, 30));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				_bc.instalarJuego(_juego);
				firePropertyChange("Biblioteca",null ,null);
				frame.dispose();
			}	
		});

		frame.add(label, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
