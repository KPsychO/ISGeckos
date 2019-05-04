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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.UsuarioDTO;

public class MainViewComunidad extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int i = 0;
	private static int j = 0;
	private boolean encontrado = false;
	private UsuarioDTO _user;
	private JPanel _panel;
	private List<UsuarioDTO> users;
	private PerfilUsuarioDenunc pud;
	private List<PerfilUsuarioDenunc> perfil;

	
	public MainViewComunidad(String usuario) {
		_user = new UsuarioDTO(usuario);
		perfil = new ArrayList<PerfilUsuarioDenunc>();
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
        users = _user.getUsers();
		_panel = new JPanel();
		JTextArea buscarNombre = new JTextArea(""); 
		buscarNombre.setPreferredSize(new Dimension(200, 20));
		JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	j = 0;
            	if (!buscarNombre.getText().equals("")) {
	            	for (UsuarioDTO us : users) {	
            			perfil.get(j).setVisible(false);
	            		if (buscarNombre.getText().equals(us.get_username())) {
	            			perfil.get(j).setVisible(true);
	            			encontrado = true;
	            		}
            			j++;
	            	}
	            	if (!encontrado) {
	        			JOptionPane.showMessageDialog(getParent(), "No hay ningun usuario con ese nombre");
	            	}
	            	}
            	}
        });
		_panel.add(buscarNombre);
        _panel.add(buscar);
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		for (UsuarioDTO us : users) {	
			pud = new PerfilUsuarioDenunc(us);
			perfil.add(pud);
	        pud.addPropertyChangeListener(new PropertyChangeListener() {

	            @Override
	            public void propertyChange(PropertyChangeEvent e) {
	            	firePropertyChange(e.getPropertyName(), e.getOldValue(), e.getNewValue());
	            }
	        });
			_panel.add(perfil.get(i));
			perfil.get(i).setVisible(false);
			_panel.add(new JSeparator());
			i++;
		}
		i = 0;
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		this.add(jsp);
	}

}
