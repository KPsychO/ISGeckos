package Biblioteca.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Biblioteca.Control.BibliotecaControllerFacade;
import Biblioteca.Control.JuegoEnPropiedadDTO;
import Usuario.Control.UsuarioDTO;

public class MainViewBiblioteca extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static BibliotecaControllerFacade _bibliotecaController;
	//BibliotecaDTO _bibliotecaDTO;
	private List<JuegoEnPropiedadDTO> _games;
	private JPanel _panel;
	
	@SuppressWarnings("static-access")
	public MainViewBiblioteca(BibliotecaControllerFacade ctrllr, UsuarioDTO user, List<JuegoEnPropiedadDTO> games) {
		this._bibliotecaController = ctrllr;
		
		this._games = games;
		
		initGUI();
		
		this.setVisible(true);
	}

	private void initGUI() {
		
		configPanel();
		addGames();
	}
	
	private void configPanel() {
		
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new GridLayout(0, 3, 10, 10));		
	}
	
	private void addGames() {
		if (_games == null)
			return;
		for (JuegoEnPropiedadDTO j : this._games) {	
			
			JuegoBiblioteca observed = new JuegoBiblioteca(j, _bibliotecaController);
	        
			_panel.add(observed);	
		}
		
		JPanel _grid = new JPanel(new FlowLayout());
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		jsp.add(_grid);
		this.add(jsp);
	}
}
