package Comunidad.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.json.JSONObject;
import org.json.JSONTokener;

import Usuario.Control.UsuarioDAO;
import Usuario.Control.UsuarioDAOJSON;
import Usuario.Control.UsuarioDTO;

public class MainViewComunidad extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel _panel;
	private JPanel _buscar;
	private JPanel _total;
	private UsuarioDAO dao;
	private ComunidadControllerFacade _controller;
	
	private int i = 0;
	private int j = 0;
	private boolean encontrado = false;

	private PerfilUsuarioDenunc pud;
	private List<PerfilUsuarioDenunc> perfil;
	private UsuarioDTO _user;

	
	public MainViewComunidad(UsuarioDTO usuario, ComunidadControllerFacade controller) {
		perfil = new ArrayList<PerfilUsuarioDenunc>();
		dao = new UsuarioDAOJSON();
		_controller = controller;
		_user = usuario;
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));

		_total = new JPanel();
		_total.setLayout(new BoxLayout(_total, BoxLayout.Y_AXIS));

		_buscar = new JPanel();

		 List<UsuarioDTO> users = getUsers();
		JTextField buscarNombre = new JTextField(""); 
		buscarNombre.setPreferredSize(new Dimension(200, 20));
		JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener(){  
            @SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e){  
            	j = 0;
            	if (!buscarNombre.getText().equals("")) {
	            	for (UsuarioDTO us : users) {	
            			perfil.get(j).setVisible(false);
	            		if (us.get_username().toLowerCase().contains(buscarNombre.getText().toLowerCase())) {
	            			if (!(us.get_user_id().equals(_user.get_user_id())) && !(us.get_user_id().equals("0000000000"))) {
	            				perfil.get(j).setVisible(true);
	            				encontrado = true;
	            			}
	            		}
            			j++;
	            	}
	            	if (!encontrado) {
	        			JOptionPane.showMessageDialog(getParent(), "No hay ningun usuario con ese nombre");
	            	}
	            }
            	else {
            		for (UsuarioDTO us : users) {	
            			if (!(us.get_user_id().equals(_user.get_user_id())) && !(us.get_user_id().equals("0000000000"))) {
            				perfil.get(j).setVisible(true);
            			}
            			j++;
            		}
            	}
            }
        });

		_buscar.add(buscarNombre);
        _buscar.add(buscar);
		for (UsuarioDTO us : users) {	
			if (!(us.get_user_id().equals(_user.get_user_id())) && !(us.get_user_id().equals("0000000000"))) {
			pud = new PerfilUsuarioDenunc(us, _controller);
			perfil.add(pud);
			_panel.add(perfil.get(i));
			perfil.get(i).setVisible(true);
			_panel.add(new JSeparator());
			}
			else {
				pud = new PerfilUsuarioDenunc(us, _controller);
				perfil.add(pud);
				_panel.add(perfil.get(i));
				perfil.get(i).setVisible(false);
				_panel.add(new JSeparator());
			}
			i++;
		}
		i = 0;
		
		_total.add(_buscar);
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		_total.add(jsp);
		this.add(_total);
	}
	
	private List<UsuarioDTO> getUsers(){
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		
		for (Object o : dao.getListUsuariosJson()) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			lista.add(new UsuarioDTO(user));
			
		}
		
		return lista;
		
	}

}
