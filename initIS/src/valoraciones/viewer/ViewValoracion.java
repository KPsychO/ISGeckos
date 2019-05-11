package valoraciones.viewer;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;
import valoraciones.controller.EventsValoraciones;
import valoraciones.model.TransferValoracion;

public class ViewValoracion extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransferValoracion valoracion;
	private UsuarioDTO user;
	private JPanel panelComentario;
	private JPanel panelValoracion;
	private ValoracionesControllerFacade controller;
	
	public ViewValoracion(TransferValoracion valoracion,ValoracionesControllerFacade controller, UsuarioDTO user) {
		this.user = user;
		this.controller = controller;
		this.valoracion = valoracion;
		initComponent();
	}

	private void initComponent() {
		initValoracion();
		initComentarios();
		this.setLayout(new BorderLayout());
		this.add(panelValoracion,BorderLayout.NORTH);
		this.add(panelComentario,BorderLayout.CENTER);
	}

	private void initComentarios() {
		try {
			panelComentario = new PanelListComentarios(valoracion,user, controller);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					getParent(), 
					"Error controller run.\n"+ e.getMessage(), 
					"Controller run error",
					JOptionPane.INFORMATION_MESSAGE 
					);
			e.printStackTrace();
		}
	}

	private void initValoracion() {
		panelValoracion = new JPanel(); 
		panelValoracion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		c.insets = new Insets(10,10,10,10);

		c.weightx = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		

		c.ipadx = 40;
		c.gridx = 0;
		c.gridy = 0;
		panelValoracion.add(new JLabel("Titulo: "), c);
		

		c.gridx = 1;
		c.gridy = 0;
		panelValoracion.add(new JLabel(valoracion.getTitulo().getText()), c);

		JTextArea textArea = new JTextArea(valoracion.getValoracion().getText());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane paneltexto = new JScrollPane(textArea);
		c.ipady = 120;
		c.ipadx = 500;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 1;
		panelValoracion.add(paneltexto, c);
		c.ipady = 0;
		c.ipadx = 0;
		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 2;
		panelValoracion.add( new JLabel("Puntuacion: "+valoracion.getPuntuacion()+"/5"), c);

		c.gridx = 1;
		c.gridy = 2;
		if (valoracion.getMultimedia() != null)
			panelValoracion.add(new JLabel(valoracion.getMultimedia().getPresentation()), c);
		else
			panelValoracion.add(new JLabel("no multimedia asociated"), c);

		JButton buttonComentar = new JButton("Comentar");
		buttonComentar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.actions(EventsValoraciones.SHOW_FORM_COMENTAR, valoracion);
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

		c.gridx = 2;
		c.gridy = 2;
		
		if(!user.isUnregistered()) {
			panelValoracion.add(buttonComentar, c);
		}
		else {
			panelValoracion.add(new JLabel(""), c);
		}
		
		//panelValoracion.add(buttonComentar, c);
		
		
		if(user.get_user_id().equals( valoracion.getUser().get_user_id()) ){
			//JButton buttonEditar = new JButton(new ImageIcon("src/resources/valoraciones/images/modificar.png"));
			JButton buttonEditar = new JButton(new ImageIcon("resources/valoraciones/images/modificar.png"));
			//JButton buttonEliminar = new JButton(new ImageIcon("src/resources/valoraciones/images/eliminar.png"));
			JButton buttonEliminar = new JButton(new ImageIcon("resources/valoraciones/images/eliminar.png"));
			buttonEditar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controller.actions(EventsValoraciones.SHOW_FORM_MODIFICAR_VALORACION, valoracion);
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
			buttonEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controller.actions(EventsValoraciones.ELIMINAR_VALORACION, valoracion);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(
								getParent(), 
								"Error controller run.\n"+ e1.getMessage(), 
								"Controller run error",
								JOptionPane.INFORMATION_MESSAGE 
								);;
					}
				}
				
			});
			JPanel aux = new JPanel();
			aux.setLayout(new BoxLayout(aux,BoxLayout.X_AXIS));
			aux.add(buttonEditar);
			aux.add(buttonEliminar);
			c.anchor = GridBagConstraints.LINE_END;
			c.gridx = 3;
			c.gridy = 2;
			panelValoracion.add(aux, c);
			
		}

		c.gridx = 0;
		c.gridy = 3;
		panelValoracion.add(new JLabel("Autor: "+user.get_username()), c);
		
		
	}
	
	
	
}
