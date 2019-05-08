package Biblioteca.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Biblioteca.Control.JuegoEnPropiedadController;
import Biblioteca.Control.JuegoEnPropiedadDTO;

public class JuegoBiblioteca extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JButton _icon;
	JPanel _contents;
	JuegoEnPropiedadDTO _juegoEnPropiedadDTO;
	JuegoEnPropiedadController _controller;
	
	public JuegoBiblioteca (JuegoEnPropiedadDTO juego) {
		JuegoEnPropiedadDTO game = new JuegoEnPropiedadDTO(juego);
		_controller = new JuegoEnPropiedadController(game.get_id());
		_juegoEnPropiedadDTO = juego;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		
		configPanel();
		createIcon();
		createContents();
		
		this.add(_icon, BorderLayout.NORTH);
		this.add(_contents);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setVisible(true);
		
	}

	private void configPanel() {
		
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(200, 200));
		this.setMinimumSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(200, 200));
		
	}
	
	private void createIcon() {
		
		_icon = new JButton(new ImageIcon("./src/resources/game_icon.jpg"));
		_icon.addActionListener(new JuegoButton());
		
	}
	
	private void createContents() {
		
		_contents = new JPanel(new GridLayout(2,2));
		
		JLabel title = new JLabel();
		title.setMaximumSize(new Dimension(100, 100));
		title.setMinimumSize(new Dimension(100,100));
		title.setPreferredSize(new Dimension(100,100));
		title.setText(" Title: " + _controller.get_title());
		
		
		JButton buttonPlay = new JButton("JUGAR");
		buttonPlay.setMaximumSize(new Dimension(100, 100));
		buttonPlay.setMinimumSize(new Dimension(100,100));
		buttonPlay.setPreferredSize(new Dimension(100,100));
		
		if (_controller.is_installed() == false)
			buttonPlay.setBackground(Color.DARK_GRAY);
		else if(_controller.get_actVersion() != _controller.get_version())
			buttonPlay.setBackground(Color.GRAY);
		else
			buttonPlay.setBackground(Color.WHITE);
		
		buttonPlay.addActionListener(new JugarButton());
		
		
		JButton buttonValorar = new JButton("VALORAR");
		buttonValorar.setMaximumSize(new Dimension(100, 100));
		buttonValorar.setMinimumSize(new Dimension(100,100));
		buttonValorar.setPreferredSize(new Dimension(100,100));
		
		buttonValorar.addActionListener(new ValorarButton());
		
		
		JPanel panelIncDen = new JPanel(new GridLayout(2,1));
		panelIncDen.setMaximumSize(new Dimension(100,100));
		panelIncDen.setMinimumSize(new Dimension(100,100));
		panelIncDen.setPreferredSize(new Dimension(100,100));
		
		JButton buttonInc = new JButton("INC");
		buttonInc.setMaximumSize(new Dimension(50, 100));
		buttonInc.setMinimumSize(new Dimension(50,100));
		buttonInc.setPreferredSize(new Dimension(50,100));
		
		buttonInc.addActionListener(new IncidenciasButton());
		
		JButton buttonDen = new JButton("DEN");
		buttonDen.setMaximumSize(new Dimension(50, 100));
		buttonDen.setMinimumSize(new Dimension(50,100));
		buttonDen.setPreferredSize(new Dimension(50,100));
		
		buttonDen.addActionListener(new DenunciarButton());
		
		_contents.add(title);
		_contents.add(buttonPlay);
		panelIncDen.add(buttonInc);
		panelIncDen.add(buttonDen);
		_contents.add(panelIncDen);
		_contents.add(buttonValorar);
	}
	
	class JuegoButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("JuegoBiblioteca", null, _controller);
		}
	}
	
	class DenunciarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("DenunciarJuego", null, _juegoEnPropiedadDTO);
		}
	}
	
	class IncidenciasButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("IncidenciaJuego", null, _juegoEnPropiedadDTO);
		}
	}
	
	class JugarButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if (_controller.is_installed() == false) {
				//System.out.println("Error. Instala primero");
				errorInstall();
			}
			else if(_controller.get_actVersion() != _controller.get_version()) {
				//System.out.println("Error. Actualiza primero");
				errorUpdate();
			}
			else {
				//System.out.println("Estas jugando");
				executeGame();
			}
		}
	}
	
	class ValorarButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
		
		}
	}
	
	public void errorUpdate() {
		JFrame frame = new JFrame("Error");
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(300,100));
		frame.setPreferredSize(new Dimension(300,100));
		
		JLabel label = new JLabel("Error. Actualice primero", SwingConstants.CENTER);
		
		JButton button = new JButton("ACTUALIZAR");
		button.setPreferredSize(new Dimension(100, 30));
		button.setMaximumSize(new Dimension(100, 30));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				_controller.set_actVersion(_controller.get_version());
				//_juegoEnPropiedadDTO.JuegoEnPropiedadToJSON();
				firePropertyChange("Biblioteca",null ,_controller);
				frame.dispose();
			}
			
		});
		
		frame.add(label, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	public void errorInstall() {
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
				_controller.set_installed(true);
				_controller.set_actVersion(_controller.get_version());
				firePropertyChange("Biblioteca",null ,null);
				frame.dispose();
			}	
		});

		frame.add(label, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	public void executeGame() {
		JFrame frame = new JFrame(_controller.get_title());
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(1000,800));
		frame.setPreferredSize(new Dimension(1000,800));
		
		JLabel label = new JLabel("El juego \"" + _controller.get_title() + "\" se est� ejecutando", SwingConstants.CENTER);
		
		frame.add(label, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
