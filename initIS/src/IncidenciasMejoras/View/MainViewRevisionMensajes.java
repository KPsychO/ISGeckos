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

import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
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
	private int n = 0;

	
	public MainViewRevisionMensajes(UsuarioDTO user) {
		_user = user;
		incMejDTO = new IncidenciasMejorasDTO(user);
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
		buttons.add(siguiente);
		buttons.add(anterior);
		
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
        _id_user.setText("Usuario :  ");
        
        this._id_user_Denun = new JLabel();
        
        _id_user_Denun.setPreferredSize(new Dimension(500,20));
        _id_user_Denun.setText("Usuario Denunciado :  ");
        
        this._id_game = new JLabel();
        
        _id_game.setPreferredSize(new Dimension(500,20));
        _id_game.setText("Juego :  ");
        
        this._desc = new JLabel();
        
        _desc.setPreferredSize(new Dimension(500,20));
        _desc.setText("Descripcion :  " );
        
        this._coment = new JLabel();
        
        _coment.setPreferredSize(new Dimension(500,20));
        _coment.setText("Comentario :  " );
        
        campos.add(_type);
        campos.add(_id_user);
        campos.add(_id_user_Denun);
        campos.add(_id_game);
        campos.add(_desc);
        campos.add(_coment);
        
        campos.add(Box.createVerticalStrut(20));
        this.add(campos);
        
        visualizarIncidenciasDenuncias(n);
	}
	
	private void visualizarIncidenciasDenuncias(int n) {
		JSONArray inciMej = new JSONArray();
		
		inciMej = new IncidenciasDAOJSON().getListIncidencias();
		if (inciMej.length() != 0) {
		IncidenciasMejorasDTO incidenciasMejoras = new IncidenciasMejorasDTO(inciMej.getJSONObject(n));
		
		this._type.setText("Tipo :  " + incidenciasMejoras.get_type());
		this._id_user.setText("Usuario :  " + incidenciasMejoras.get_id_user());
		
		switch (incidenciasMejoras.get_type()) {
		case "IncJue": 
			this._id_game.setText("Juego :  " + incidenciasMejoras.get_id_game());
			break;
		case "DenJug": 
			this._id_user_Denun.setText("Usuario Denunciado :  " + incidenciasMejoras.get_id_user_Denun());
			break;
		case "DenJue": 
			this._id_game.setText("Juego :  " + incidenciasMejoras.get_id_game());
			break;
		}
		this._desc.setText("Descripcion :  " + incidenciasMejoras.get_desc());
		this._coment.setText("Comentario :  " + incidenciasMejoras.get_coment());
		}
		else {
			JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	class eliminarIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			incMejDTO.eliminarIncidenciaMejora(n);
			JSONArray im = new JSONArray();
			im = new IncidenciasDAOJSON().getListIncidencias();
			
			if(im.length() == 0) {
				JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
				firePropertyChange("PerfilUsuarioCurrent", null, _user);
			}
			else if(n < im.length() - 1){
				visualizarIncidenciasDenuncias(n);
			}
			else if (n >= im.length() - 1){
				n = 0;
				visualizarIncidenciasDenuncias(n);
			}
		}
	}
	
	class siguienteIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			 JSONArray im = new JSONArray();
				im = new IncidenciasDAOJSON().getListIncidencias();
				if(im.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n < im.length() - 1){
					n++;
					visualizarIncidenciasDenuncias(n);
				}
				else if (n == im.length() - 1){
					n = 0;
					visualizarIncidenciasDenuncias(n);
				}
		 }
	}
	
	class anteriorIncDen implements ActionListener {
		 public void actionPerformed(ActionEvent e){ 
			 JSONArray im = new JSONArray();
				im = new IncidenciasDAOJSON().getListIncidencias();
				if(im.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n > 0){
					n--;
					visualizarIncidenciasDenuncias(n);
				}
				else if (n == 0){
					n = im.length() - 1;
					visualizarIncidenciasDenuncias(n);
				}	
		}
	}
}
