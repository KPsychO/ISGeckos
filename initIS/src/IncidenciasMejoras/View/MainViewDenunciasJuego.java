package IncidenciasMejoras.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;

public class MainViewDenunciasJuego extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	
	//extends 
	private JPanel descr;
	private JPanel coment;
	private JPanel buttons;
	private JButton aceptar;
	private JTextField comenText;
	private JTextField descText;
	private IncidenciasDAOJSON imJSON;
	
	public MainViewDenunciasJuego(String usuario) {
		descr = new JPanel();
		coment = new JPanel();
		buttons = new JPanel();
		imJSON = new IncidenciasDAOJSON();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	private void initGUI() {
		JLabel desc = new JLabel();
		desc.setText("Descripcion: ");
		descText = new JTextField("Motivo por el que denuncias el juego");
		descText.setPreferredSize(new Dimension(500, 200));
		JLabel comen = new JLabel();
		comen.setText("Comentario: ");
		comenText = new JTextField("Comenta mas a fondo el motivo");
		comenText.setPreferredSize(new Dimension(500, 200));
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		JButton cancelar = new JButton("Cancelar");
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			//imJSON.getListIncidencias();
			//Aqui faltan los usuarios y el denunciado
			imJSON.insertarIncidencia(new IncidenciasMejorasDTO ("DenJue", "Jose", null, "fasjknfa13w", descText.getText(), comenText.getText()));
			JOptionPane.showMessageDialog(getParent(), "Has enviado la Denuncia/Incidencia");
		}
		else if (e.getActionCommand().equals("cancelar")) {
			
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la Denuncia/Incidencia");
		}
	}

}

