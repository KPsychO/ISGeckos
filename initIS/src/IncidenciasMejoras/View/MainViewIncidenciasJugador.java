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

import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
import Tienda.View.MainViewTienda;

public class MainViewIncidenciasJugador extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	
	MainViewTienda observed;
	private JPanel descr;
	private JPanel coment;
	private JPanel buttons;
	private JButton aceptar;
	private JButton cancelar;
	private JTextArea comenText;
	private JTextField descText;
	private IncidenciasDAOJSON imJSON;
	private String user;
	
	public MainViewIncidenciasJugador(String usuario) {
		descr = new JPanel();
		coment = new JPanel();
		buttons = new JPanel();
		user = usuario;
		imJSON = new IncidenciasDAOJSON();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	private void initGUI() {
		JLabel desc = new JLabel();
		desc.setText("Descripcion:   ");
		descText = new JTextField("Cual es el problema que tienes?");
		CreateFocusListenerForFields(descText);
		descText.setPreferredSize(new Dimension(600, 25));
		
		JLabel comen = new JLabel();
		comen.setText("Comentario:   ");
		comenText = new JTextArea("Comenta mas a fondo el motivo");
		comenText.setWrapStyleWord(true);
		comenText.setLineWrap(true);
		comenText.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CreateFocusListenerForFields(comenText);
		comenText.setPreferredSize(new Dimension(600, 200));
		
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar");
		cancelar.addActionListener(this);
		
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
			//imJSON.getListIncidencias();
			//Aqui faltan los usuarios y el denunciado
			imJSON.insertarIncidencia(new IncidenciasMejorasDTO ("IncJug", user, null, null, descText.getText(), comenText.getText()));
			JOptionPane.showMessageDialog(getParent(), "Has enviado la Denuncia/Incidencia");
			firePropertyChange("Soporte", null, null);
		}
		else if (e.getActionCommand().equals("cancelar")) {
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la Denuncia/Incidencia");
			firePropertyChange("Soporte", null, null);
			/*
			firePropertyChange("Hola", null, null);
			observed.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
		        public void propertyChange(PropertyChangeEvent e) {
					firePropertyChange(e.getPropertyName(), e.getOldValue(), e.getNewValue());
		        }
		    });
			 */
			
		}
	}

}
