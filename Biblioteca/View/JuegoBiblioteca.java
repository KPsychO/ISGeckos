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
import javax.swing.JLabel;
import javax.swing.JPanel;

import Biblioteca.Control.BibliotecaControllerFacade;
import Biblioteca.Control.EventoBiblioteca;
import Biblioteca.Control.JuegoEnPropiedadDTO;

public class JuegoBiblioteca extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JButton _icon;
	JPanel _contents;
	JuegoEnPropiedadDTO _dto;
	//JuegoEnPropiedadController _jc;
	BibliotecaControllerFacade _bc;
	
	public JuegoBiblioteca (JuegoEnPropiedadDTO juego, BibliotecaControllerFacade controller) {
		_bc = controller;
		_dto = juego;
		//_jc = new JuegoEnPropiedadController(juego.get_id());
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
		
		_icon = new JButton(new ImageIcon("./src/resources/game_icon_SMOL.jpg"));
		_icon.addActionListener(new JuegoButton());
		
	}
	
	private void createContents() {
		
		_contents = new JPanel(new GridLayout(2,2));
		
		JLabel title = new JLabel();
		title.setMaximumSize(new Dimension(100, 100));
		title.setMinimumSize(new Dimension(100,100));
		title.setPreferredSize(new Dimension(100,100));
		title.setText(" Title: " + _dto.get_title());
		
		JPanel panelValVer = new JPanel(new GridLayout(2,1));
		panelValVer.setMaximumSize(new Dimension(100,100));
		panelValVer.setMinimumSize(new Dimension(100,100));
		panelValVer.setPreferredSize(new Dimension(100,100));
		
		JButton buttonPlay = new JButton("JUGAR");
		buttonPlay.setMaximumSize(new Dimension(100, 100));
		buttonPlay.setMinimumSize(new Dimension(100,100));
		buttonPlay.setPreferredSize(new Dimension(100,100));
		
		if (_dto.is_installed() == false)
			buttonPlay.setBackground(Color.DARK_GRAY);
		else if(_dto.get_actVersion() != _dto.get_version())
			buttonPlay.setBackground(Color.GRAY);
		else
			buttonPlay.setBackground(Color.WHITE);
		
		buttonPlay.addActionListener(new JugarButton());
		
		JButton buttonVer = new JButton("Ver en Tienda");
		buttonVer.setMaximumSize(new Dimension(100,100));
		buttonVer.setMinimumSize(new Dimension(100,100));
		buttonVer.setPreferredSize(new Dimension(100,100));
		
		buttonVer.addActionListener(new VerButton());
		
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
		
		JButton buttonDen = new JButton("DEN");
		buttonDen.setMaximumSize(new Dimension(50, 100));
		buttonDen.setMinimumSize(new Dimension(50,100));
		buttonDen.setPreferredSize(new Dimension(50,100));
		
		buttonInc.addActionListener(new IncButton());
		buttonDen.addActionListener(new DenButton());
		
		_contents.add(title);
		_contents.add(buttonPlay);
		panelIncDen.add(buttonInc);
		panelIncDen.add(buttonDen);
		panelValVer.add(buttonValorar);
		panelValVer.add(buttonVer);
		_contents.add(panelIncDen);
		_contents.add(panelValVer);
	}
	
	class JuegoButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.JuegoTienda, _dto);
		}
	}
	
	class JugarButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.jugarJuego, _dto);
		}
	}
	
	class VerButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.JuegoTienda, _dto);
		}
	}
	
	class ValorarButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.valoraciones, _dto);
		}
	}
	
	class IncButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.incidencia, _dto);
		}
	}
	
	class DenButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			_bc.evento(EventoBiblioteca.denuncia, _dto);
		}
	}

}
