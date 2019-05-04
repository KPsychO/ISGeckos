package Comunidad.View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.UsuarioDTO;

public class PerfilUsuarioDenunc extends JPanel{
	private static final long serialVersionUID = 1L;

	UsuarioDTO _user;
	
	JButton _icon;
	JPanel _contents;
	JPanel _caract;
	JPanel _desc;
	
	public PerfilUsuarioDenunc(UsuarioDTO us) {
		_user = us;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMaximumSize(new Dimension(600, 200));
		this.setMinimumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		
		_icon = new JButton(new ImageIcon("./src/resources/usuario.png"));
		_icon.addActionListener(new CargarButton());		
			
		JLabel name = new JLabel();
		name.setText("Name: " + _user.get_username());
		
		JLabel country = new JLabel();
		country.setText("Country: " +_user.get_country());

		JLabel id = new JLabel();
		id.setText("ID: " + _user.get_user_id());
		
		_caract = new JPanel();
		_caract.setLayout(new BoxLayout(_caract, BoxLayout.Y_AXIS));
	    _caract.setAlignmentX(Component.CENTER_ALIGNMENT);
		_caract.add(name);
		_caract.add(country);
		_caract.add(id);
		
		_desc = new JPanel(new BorderLayout());
		_desc.setMaximumSize(new Dimension(200, 100));
		_desc.setMinimumSize(new Dimension(200, 100));
		_desc.setPreferredSize(new Dimension(200, 100));
		
		JTextArea ta = new JTextArea();
		ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
		ta.setText(_user.get_desc());
		ta.setEditable(false);
		
		JScrollPane sPane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		_desc.add(sPane, BorderLayout.CENTER);
		
		this.add(_icon);
		this.add(new JSeparator());
		this.add(_caract);
		this.add(new JSeparator());
		this.add(_desc);		
	}
	
	class CargarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			firePropertyChange("PerfilUsuarioDenunciado", null, _user);
		}
	}

}
