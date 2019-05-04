package Juego.View;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Juego.Control.JuegoDTO;
import Tienda.View.JuegoTienda;
//import Formulario.Control.FormularioDTO;
import Usuario.Control.*;
//import IncidenciasMejoras.Control.IncidenciasDAOJSON;
//import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
//import Tienda.View.MainViewTienda;

public class MainViewDeveloper extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private UsuarioDTO _dto;
	private UsuarioDAO _dao;
	private List<JuegoDTO> _listaJuegos;
	
	private JPanel _panel;
	
	public MainViewDeveloper (UsuarioDTO dto) {
		
		_dto = dto;
		_dao = new UsuarioDAOJSON();
		_listaJuegos = new ArrayList<JuegoDTO>();
		cargarJuegos();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		initGUI();
		this.setVisible(true);
		
	}

	private void initGUI() {
		_panel = new JPanel(new BorderLayout());
		
		for (JuegoDTO juego : _listaJuegos) {
			
			JuegoDeveloper newPanel = new JuegoDeveloper(juego);
			
			_panel.add(newPanel);
			//_panel.add(new JSeparator());
			
		}
		
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		this.add(jsp);
		
	}
	
	private void cargarJuegos() {
		
		try {
			
			InputStream input = new FileInputStream("./src/resources/DeveloperGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {

				JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
				_listaJuegos.add(new JuegoDTO(juego));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
