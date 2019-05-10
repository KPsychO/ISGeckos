package valoraciones;

import java.io.IOException;

import javax.swing.JPanel;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;
import valoraciones.controller.EventsValoraciones;
import valoraciones.controller.FachadaMedia;
import valoraciones.model.DAOComentario;
import valoraciones.model.DAOValoracion;
import valoraciones.model.TransferComentario;
import valoraciones.model.TransferValoracion;
import valoraciones.viewer.PanelListValoraciones;
import valoraciones.viewer.ViewFormComentario;
import valoraciones.viewer.ViewFromValoracion;
import valoraciones.viewer.ViewValoracion;

public class ValoracionesControllerFacade {
	private Controller controller;
	
	public ValoracionesControllerFacade(Controller controller) {
		this.controller = controller;
	}
	
	public JPanel getPanelListValoracionesJuego(JuegoDTO game, UsuarioDTO user) throws IOException {
		return new PanelListValoraciones(game,user,this);
	}
	
	public float getMediaValoracion(JuegoDTO game) throws IOException {
		return new FachadaMedia().getMediaJuego(game);
	}
	
	public void showPanel(JPanel panel) {
		controller.setPrincipalPanel(panel);
	}
	
	public void actions(int event, Object data) throws Exception {
		TransferComentario comentario;
		TransferValoracion valoracion;
		switch(event) {
		case EventsValoraciones.PUBLICAR_COMENTARIO:
			comentario = (TransferComentario) data;
			DAOComentario daoComentario = new DAOComentario();
			daoComentario.createComentario(comentario);
			valoracion = new DAOValoracion().getValoracion(comentario.getValoracionID(),comentario.getGame());
			controller.setPrincipalPanel(new ViewValoracion(valoracion,this, controller.getCurrentUser() ));
			break;
		case EventsValoraciones.SHOW_FORM_COMENTAR:
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(new ViewFormComentario(valoracion.getID(), controller.getCurrentUser(), valoracion.getGame(),this) );
			break;
		case EventsValoraciones.ELIMINAR_COMENTARIO:
			comentario = (TransferComentario) data;
			new DAOComentario().deleteComentario(comentario);
			valoracion = new DAOValoracion().getValoracion(comentario.getValoracionID(),comentario.getGame());
			controller.setPrincipalPanel(new ViewValoracion(valoracion, this, controller.getCurrentUser()));
			break;
		case EventsValoraciones.MODIFICAR_COMENTARIO:
			
			break;
		case EventsValoraciones.PUBLICAR_VALORACION:
			valoracion = (TransferValoracion) data;
			DAOValoracion daoValoracion = new DAOValoracion();
			daoValoracion.createValoracion(valoracion);
			controller.setPrincipalPanel(controller.getBiblioteca());
			break;
		case EventsValoraciones.ELIMINAR_VALORACION:
			System.out.println("control controller valoraciones");
			valoracion = (TransferValoracion) data;
			new DAOValoracion().deleteValoracion(valoracion);
			controller.setPrincipalPanel(controller.getBiblioteca());
			break;
		case EventsValoraciones.MODIFICAR_VALORACION:
			/*
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(controller.getBiblioteca());
			*/
			break;
		case EventsValoraciones.SHOW_VALORACION:
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(new ViewValoracion(valoracion, this,controller.getCurrentUser()));
			break;
		}
	}

	public JPanel getFormValoraciones(UsuarioDTO user, JuegoDTO game) {
		// TODO Auto-generated method stub
		return new ViewFromValoracion(user,game,this);
	}
}
