package IncidenciasMejoras.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;

import IncidenciasMejoras.Control.EventoIncidenciasMejoras;
import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasControllerFacade;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
import IncidenciasMejoras.Control.IteratorInciMej;
import Usuario.Control.UsuarioDTO;

public class MainViewRevisionMensajes extends JPanel {
	private static final long serialVersionUID = 1L;

	private UsuarioDTO _user;
	private JPanel buttons;
	private JButton eliminar;
	private JButton siguiente;
	private JButton anterior;
	private JLabel _type;
	private JLabel _desc;
	private JLabel _coment;
	private JLabel _id_user;
	private JLabel _id_user_Denun;
	private JLabel _id_game;
	private IncidenciasMejorasDTO incMejDTO;
	private IteratorInciMej itr;
	private IncidenciasMejorasControllerFacade _controller;
	private JLabel _username;
	private JLabel _game;
	
	public MainViewRevisionMensajes(UsuarioDTO user, IncidenciasMejorasControllerFacade controller) {
		_user = user;
		_controller = controller;
		incMejDTO = new IncidenciasMejorasDTO(user);
		itr = new IteratorInciMej();
		initGUI();
	}

	private void initGUI() {
		paneles();
		incidenciaDenuncia();
		botones();
	}

	private void paneles() {
		buttons = new JPanel();
	}

	private void botones() {
		
		eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new eliminarIncDen());
		
		siguiente = new JButton("Siguiente");
		siguiente.addActionListener(new siguienteIncDen());

		anterior = new JButton("Anterior");
		anterior.addActionListener(new anteriorIncDen());

		buttons.add(eliminar);
		buttons.add(anterior);
		buttons.add(siguiente);
		
		this.add(buttons);
	}

	private void incidenciaDenuncia() {
		JPanel campos = new JPanel();
		
		
		BoxLayout experimentLayout = new BoxLayout(campos, BoxLayout.Y_AXIS);
        campos.setLayout(experimentLayout);
        
        this._type = new JLabel();
        
        _type.setPreferredSize(new Dimension(500,20));
        _type.setText("Tipo :  ");
        
        this._id_user = new JLabel();
        
        _id_user.setPreferredSize(new Dimension(500,20));
        _id_user.setText("ID_Usuario :  ");
        
        this._username = new JLabel();
        
        _id_user.setPreferredSize(new Dimension(500,20));
        _id_user.setText("Usuario :  ");
        
        this._id_user_Denun = new JLabel();
        
        _id_user_Denun.setPreferredSize(new Dimension(500,20));
        _id_user_Denun.setText("Usuario Denunciado :  ");
        
        this._id_game = new JLabel();
        
        _id_game.setPreferredSize(new Dimension(500,20));
        _id_game.setText("ID_Juego :  ");
        
        this._game = new JLabel();
        
        _id_user.setPreferredSize(new Dimension(500,20));
        _id_user.setText("Juego :  ");
        
        this._desc = new JLabel();
        
        _desc.setPreferredSize(new Dimension(500,20));
        _desc.setText("Descripcion :  " );
        
        this._coment = new JLabel();
        
        _coment.setPreferredSize(new Dimension(500,20));
        _coment.setText("Comentario :  " );
        
        campos.add(_type);
        campos.add(_username);
        campos.add(_id_user);
        campos.add(_id_user_Denun);
        campos.add(_game);
        campos.add(_id_game);
        campos.add(_desc);
        campos.add(_coment);
        
        campos.add(Box.createVerticalStrut(20));
        this.add(campos);
        
        visualizarIncidenciasDenuncias(itr.getN());
	}
	
	private void visualizarIncidenciasDenuncias(int n) {
		JSONArray inciMej = new JSONArray();
		
		inciMej = new IncidenciasDAOJSON().getListIncidencias();
		IncidenciasMejorasDTO incidenciasMejoras = new IncidenciasMejorasDTO(inciMej.getJSONObject(n));
		
		this._type.setText("Tipo :  " + incidenciasMejoras.get_type());
		this._username.setText("Usuario :  " + incidenciasMejoras.get_username());
		this._id_user.setText("ID_Usuario :  " + incidenciasMejoras.get_id_user());
		this._id_user_Denun.setText("Usuario Denunciado :  " + incidenciasMejoras.get_id_user_Denun());
		this._game.setText("Juego :  " + incidenciasMejoras.get_game());
		this._id_game.setText("ID_Juego :  " + incidenciasMejoras.get_id_game());
		this._desc.setText("Descripcion :  " + incidenciasMejoras.get_desc());
		this._coment.setText("Comentario :  " + incidenciasMejoras.get_coment());
	}
	
	class eliminarIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			incMejDTO.eliminarIncidenciaMejora(itr.getN());
			JSONArray im = new JSONArray();
			im = new IncidenciasDAOJSON().getListIncidencias();
			
			if(im.length() == 0) {
				JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
				_controller.evento(EventoIncidenciasMejoras.PerfilUsuario, null, _user);
			}
			else if(itr.getN() < im.length() - 1){
				visualizarIncidenciasDenuncias(itr.getN());
			}
			else if (itr.getN() >= im.length() - 1){
				visualizarIncidenciasDenuncias(itr.primero());
			}
		}
	}
	
	class siguienteIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			 JSONArray im = new JSONArray();
				im = new IncidenciasDAOJSON().getListIncidencias();
				if(im.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
					_controller.evento(EventoIncidenciasMejoras.PerfilUsuario, null, _user);
				}
				else if(itr.getN() < im.length() - 1){
					itr.sumarUno();
					visualizarIncidenciasDenuncias(itr.getN());
				}
				else if (itr.getN() == im.length() - 1){
					visualizarIncidenciasDenuncias(itr.primero());
				}
		 }
	}
	
	class anteriorIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			 JSONArray im = new JSONArray();
				im = new IncidenciasDAOJSON().getListIncidencias();
				if(im.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
					_controller.evento(EventoIncidenciasMejoras.PerfilUsuario, null, _user);
				}
				else if(itr.getN() > 0){
					itr.restarUno();
					visualizarIncidenciasDenuncias(itr.getN());
				}
				else if (itr.getN() == 0){
					itr.setN(im.length() - 1);
					visualizarIncidenciasDenuncias(itr.getN());
				}	
		}
	}
}
