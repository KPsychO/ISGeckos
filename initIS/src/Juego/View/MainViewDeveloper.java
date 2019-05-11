package Juego.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import Juego.Control.JuegoControllerFacade;
import Juego.Control.JuegoDAO;
import Juego.Control.JuegoDAOJSON;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class MainViewDeveloper extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDTO _user;
	private JuegoControllerFacade _cj;

	private JuegoDAO _dao_juego;
	
	private JPanel _panel;
	
	public MainViewDeveloper(UsuarioDTO user, JuegoControllerFacade cj) {
		
		_cj = cj;
		_user = user;
		_dao_juego = new JuegoDAOJSON();

		initGUI();
		
		this.setVisible(true);
		
	}

	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
		JButton buscar = new JButton("Buscar");
        buscar.setPreferredSize(new Dimension(100, 25));
        buscar.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
        		
            }
        });
		
		for (JuegoDTO juego : _dao_juego.getJuegosDeveloper(_user)) {
			
			JuegoDeveloper dev = new JuegoDeveloper(juego, _cj, _user);
	        
			_panel.add(dev);
			JSeparator js = new JSeparator();
			//js.setVisible(false);
			_panel.add(js);
		}
		
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		this.add(jsp);
		
	}

}
