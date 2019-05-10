package valoraciones.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;
import valoraciones.controller.EventsValoraciones;
import valoraciones.model.DAOComentario;
import valoraciones.model.TransferComentario;
import valoraciones.model.TransferValoracion;


public class PanelListComentarios extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TransferComentario> comentarios;
	//private TransferValoracion tValoracion;
	private UsuarioDTO user;
	private ValoracionesControllerFacade controller;
	
	public PanelListComentarios(TransferValoracion tValoracion, UsuarioDTO user, ValoracionesControllerFacade controller) throws IOException{
		comentarios = new DAOComentario().getComentarios(tValoracion);
		this.user = user;
		this.controller = controller;
		initComponent();
	}
	
	private void initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		for(TransferComentario comentario : comentarios) {
			panel.add(getPanelComentario(comentario));
		}
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(600,300));
		this.add(scrollPane);
	}

	public JPanel getPanelComentario(TransferComentario comentario) {
		JPanel panelComentario = new JPanel();
		panelComentario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Comentario", TitledBorder.LEFT, TitledBorder.TOP));
		
		panelComentario.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(10,10,10,10);

		c.weightx = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		
		JTextArea textArea = new JTextArea(comentario.getComentario().getText());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane paneltexto = new JScrollPane();
		paneltexto.setViewportView(textArea);
        paneltexto.setWheelScrollingEnabled(true);
        Dimension s = new Dimension(80,10);
        paneltexto.setPreferredSize(s);
		c.ipady = 70;
		c.ipadx = 450;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		panelComentario.add(paneltexto, c);
		c.ipady = 0;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;

		
		c.ipadx = 40;
		c.gridx = 0;
		c.gridy = 1;
		panelComentario.add(new JLabel("Autor: "+comentario.getUser().get_username()), c);
		
		if(user.get_user_id().equals(comentario.getUser().get_user_id())) {
			JButton buttonEditar = new JButton(new ImageIcon("src/resources/valoraciones/images/modificar.png"));
			JButton buttonEliminar = new JButton(new ImageIcon("src/resources/valoraciones/images/eliminar.png"));
			buttonEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controller.actions(EventsValoraciones.ELIMINAR_COMENTARIO, comentario);
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
			buttonEditar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controller.actions(EventsValoraciones.SHOW_FORM_MODIFICAR_COMENTARIO, comentario);
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
			JPanel aux = new JPanel();
			aux.setLayout(new BoxLayout(aux,BoxLayout.X_AXIS));
			aux.add(buttonEditar);
			aux.add(buttonEliminar);
			c.gridx = 1;
			c.gridy = 1;
			panelComentario.add(aux, c);
		}
		return panelComentario;
	}
}