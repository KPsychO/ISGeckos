package valoraciones.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;
import valoraciones.controller.EventsValoraciones;
import valoraciones.model.DAOValoracion;
import valoraciones.model.TransferValoracion;

public class PanelListValoraciones extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JuegoDTO juego;
	private ValoracionesControllerFacade controller;
	
	public PanelListValoraciones(JuegoDTO game, UsuarioDTO user, ValoracionesControllerFacade controller) throws IOException {
		this.juego = game;
		this.controller = controller;
		initComponent();
	}

	private void initComponent() throws IOException {
		JPanel aux = new JPanel();
		aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
		for(TransferValoracion valoracion : new DAOValoracion().getValoraciones(juego))
			aux.add(this.getPanelValoracionList(valoracion));
		JScrollPane scrollPane = new JScrollPane(aux);
		Dimension s = new Dimension(600,250);
		scrollPane.setSize(s );
		scrollPane.setPreferredSize( s );
		scrollPane.setMinimumSize(s);
		scrollPane.setMaximumSize(s);
		this.add(scrollPane);
	}
	
	public JPanel getPanelValoracionList(TransferValoracion valoracion) {
		JPanel panelValoracion = new JPanel();
		Dimension s = new Dimension(500,80);
		panelValoracion.setPreferredSize(s);
		panelValoracion.setMinimumSize(s);
		panelValoracion.setMaximumSize(s);
		panelValoracion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Valoracion", TitledBorder.LEFT, TitledBorder.TOP));
		
		JButton buttonValoracion = new JButton(valoracion.getTitulo().getText());
		buttonValoracion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.actions(EventsValoraciones.SHOW_VALORACION, valoracion);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(
							getParent(), 
							"Error controller run.\n"+ e1.getMessage(), 
							"Controller run error",
							JOptionPane.INFORMATION_MESSAGE 
							);
				}
			}
			
		});
		panelValoracion.add(buttonValoracion);
		panelValoracion.add(new JLabel("Autor: "+valoracion.getUser().get_username()));
		panelValoracion.add(new JLabel("Puntuacion "+valoracion.getPuntuacion()+"/5"));
		panelValoracion.add(new JLabel("Fecha: "+valoracion.getDate()));
		
		return panelValoracion;
	}
	
	
}
