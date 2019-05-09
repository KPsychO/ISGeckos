package IncidenciasMejoras.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import IncidenciasMejoras.Control.IncidenciasMejorasControllerFacade;
import IncidenciasMejoras.Control.EventoIncidenciasMejoras;
import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class MainViewDenunciasJuego extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel descr;
	private JPanel coment;
	private JPanel buttons;
	private JButton aceptar;
	private JTextField comenText;
	private JTextField descText;
	private IncidenciasDAOJSON imJSON;
	private UsuarioDTO user;
	private JuegoDTO game;
	private IncidenciasMejorasControllerFacade _controller;
	
	public MainViewDenunciasJuego(UsuarioDTO usuario, JuegoDTO juego, IncidenciasMejorasControllerFacade controller) {
		game = juego;
		user = usuario;
		imJSON = new IncidenciasDAOJSON();
		initGUI();
	}
	
	private void initGUI() {
		//Paneles
		paneles();
		
		//Descripcion
		JLabel desc = new JLabel();
		desc.setText("Descripcion: ");
		descText = new JTextField("Motivo por el que denuncias el juego");
		CreateFocusListenerForFields(descText);
		descText.setPreferredSize(new Dimension(600, 25));
		
		//Comentario
		JLabel comen = new JLabel();
		comen.setText("Comentario: ");
		comenText = new JTextField("Comenta mas a fondo el motivo");
		CreateFocusListenerForFields(comenText);
		comenText.setPreferredSize(new Dimension(600, 200));
		
		//Botones
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		JButton cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar");
		cancelar.addActionListener(this);
		
		//Añadir cosas al JPanel
		descr.add(desc);
		descr.add(descText);
		coment.add(comen);
		coment.add(comenText);
		buttons.add(aceptar);
		buttons.add(cancelar);
		this.add(descr);
		this.add(coment);
		this.add(buttons);
	}
	
	private void paneles() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		descr = new JPanel();
		coment = new JPanel();
		buttons = new JPanel();		
	}

	public void CreateFocusListenerForFields(JTextField txt)
	{
	    txt.addFocusListener(new FocusListener() 
	    {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	txt.setText("");
	        }

			@Override
			public void focusLost(FocusEvent e) {

			}
	    });
	}
	
	public void CreateFocusListenerForFields(JTextArea txt)
	{
	    txt.addFocusListener(new FocusListener() 
	    {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	txt.setText("");
	        }

			@Override
			public void focusLost(FocusEvent e) {

			}
	    });
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			if (!user.get_user_id().equals("0000000000")) {
				imJSON.insertarIncidencia(new IncidenciasMejorasDTO ("DenJue", user.get_user_id(), null, game.get_id(), descText.getText(), comenText.getText()));
				JOptionPane.showMessageDialog(getParent(), "Has enviado la Denuncia/Incidencia");
			}
			else {
				JOptionPane.showMessageDialog(getParent(), "No puedes hacer una Denuncia/Incidencia porque no estas registrado");
			}
			_controller.evento(EventoIncidenciasMejoras.IncMejATienda, null, null);
		}
		else if (e.getActionCommand().equals("cancelar")) {
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la Denuncia/Incidencia");
			_controller.evento(EventoIncidenciasMejoras.IncMejATienda, null, null);
		}
	}

}

