package valoraciones.viewer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;
import valoraciones.controller.EventsValoraciones;
import valoraciones.factories.BuildersTextTypes;
import valoraciones.factories.SingletonBuilderTextFactory;
import valoraciones.model.TransferComentario;


public class ViewFormComentario extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JTextArea textAreaComentario;
	private JButton buttonComentar;
	
	private TransferComentario comentario;
	
	private ValoracionesControllerFacade controller;
	
	public ViewFormComentario(int valoracionID, UsuarioDTO user, JuegoDTO juego, ValoracionesControllerFacade controller) {
		comentario = new TransferComentario(valoracionID,user,juego);
		this.controller = controller;
		initComponent();
		addComponents();
	}

	public ViewFormComentario(TransferComentario comentario, ValoracionesControllerFacade controller) {
		this.comentario = comentario;
		this.controller = controller;
		initComponent();
		addComponents();
		persoComponetsModify();
		//textAreaComentario.setText(comentario.getComentario().getText());
	}

	private void persoComponetsModify() {
		this.textAreaComentario.setText(comentario.getComentario().getText());
		
	}

	private void initComponent() {
		textAreaComentario = new JTextArea();
		textAreaComentario.setLineWrap(true);
		textAreaComentario.setWrapStyleWord(true);
		
		buttonComentar = new JButton("Comentar");
		
		buttonComentar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				comentario.setComentario(SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.COMENTARIO,textAreaComentario.getText() }));
				comentario.setDate(dateFormat.format(date));
				try {
					controller.actions(EventsValoraciones.PUBLICAR_COMENTARIO, comentario);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(
							getParent(), 
							"Error controller run.\n"+ ex.getMessage(), 
							"Controller run error",
							JOptionPane.INFORMATION_MESSAGE 
							);
					ex.printStackTrace();
				}
			}
			
		});
	}

	private void addComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(10,10,10,10);

		c.weightx = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(new JLabel("Comentario"), c);

		
		JScrollPane aux = new JScrollPane(textAreaComentario);
		c.gridwidth = 2;
		c.ipady = 60;
		c.ipadx = 500;
		c.gridx = 0;
		c.gridy = 4;
		this.add(aux, c);
		c.gridwidth = 0;
		c.ipady = 0;
		c.ipadx = 0;

		c.gridx = 3;
		c.gridy = 5;
		this.add(buttonComentar, c);
		
	}

}
