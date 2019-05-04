package Biblioteca.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import Biblioteca.Control.BibliotecaDTO;
import Biblioteca.Control.JuegoEnPropiedadDTO;

public class MainViewBiblioteca extends JPanel{

	BibliotecaDTO _bibliotecaDTO;
	JPanel _panel;
	
	public MainViewBiblioteca(String user_Id) {
		this._bibliotecaDTO = new BibliotecaDTO(user_Id);
		
		initGUI();
		this.setVisible(true);
	}
	
	public MainViewBiblioteca(BibliotecaDTO dto) {
		this._bibliotecaDTO = new BibliotecaDTO(dto);
		
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
		
		List<JuegoEnPropiedadDTO> games = _bibliotecaDTO.get_juegosEnBiblioteca();
		
		for (JuegoEnPropiedadDTO j : games) {	
			
			JuegoBiblioteca observed = new JuegoBiblioteca(j);
	        observed.addPropertyChangeListener(new PropertyChangeListener() {

	            @Override
	            public void propertyChange(PropertyChangeEvent e) {
	            	_bibliotecaDTO.BibltoJSON(_bibliotecaDTO.get_juegosEnBiblioteca(), "");
	            	firePropertyChange(e.getPropertyName(), e.getOldValue(), _bibliotecaDTO);
	            }
	        });
	        
			_panel.add(observed);
			
		}
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		this.add(jsp);
		
	}
}
