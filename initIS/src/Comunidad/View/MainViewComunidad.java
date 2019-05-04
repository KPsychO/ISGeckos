package Comunidad.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import org.json.JSONObject;
import org.json.JSONTokener;

import Usuario.Control.UsuarioDAO;
import Usuario.Control.UsuarioDAOJSON;
import Usuario.Control.UsuarioDTO;

public class MainViewComunidad extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDTO _user;
	private JPanel _panel;
	private UsuarioDAO dao;
	
	public MainViewComunidad(String usuario) {
		dao = new UsuarioDAOJSON();
		_user = dao.getUser(usuario);
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
		JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
        		
            }
        });
        
        List<UsuarioDTO> users = getUsers();
		
		for (UsuarioDTO us : users) {	
			
			PerfilUsuarioDenunc pud = new PerfilUsuarioDenunc(us);
	        pud.addPropertyChangeListener(new PropertyChangeListener() {

	            @Override
	            public void propertyChange(PropertyChangeEvent e) {
	            	firePropertyChange(e.getPropertyName(), e.getOldValue(), e.getNewValue());
	            }
	        });
	        
			_panel.add(pud);
			_panel.add(new JSeparator());
			
		}
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		this.add(jsp);
	}
	
	private List<UsuarioDTO> getUsers(){
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		
		for (Object o : dao.getListUsuarios()) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			lista.add(new UsuarioDTO(user));
			
		}
		
		return lista;
		
	}

}
