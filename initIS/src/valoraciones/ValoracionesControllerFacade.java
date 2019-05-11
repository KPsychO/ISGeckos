package valoraciones;

import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;
import valoraciones.controller.EventsValoraciones;
import valoraciones.model.FacadeMedia;
import valoraciones.model.ServiceApplicationValoraciones;
import valoraciones.model.TransferComentario;
import valoraciones.model.TransferValoracion;
import valoraciones.viewer.PanelListValoraciones;
import valoraciones.viewer.ViewFormComentario;
import valoraciones.viewer.ViewFromValoracion;
import valoraciones.viewer.ViewValoracion;

public class ValoracionesControllerFacade {
	private Controller controller;
	private ServiceApplicationValoraciones sa;
	
	public ValoracionesControllerFacade(Controller controller) {
		this.controller = controller;
		sa = new ServiceApplicationValoraciones();
	}
	
	public JPanel getPanelListValoracionesJuego(JuegoDTO game, UsuarioDTO user) throws IOException {
		return new PanelListValoraciones(game,user,this);
	}
	
	public float getMediaValoracion(JuegoDTO game) throws IOException {
		return new FacadeMedia().getMediaJuego(game);
	}
	
	public void actions(int event, Object data) throws Exception {
		TransferComentario comentario;
		TransferValoracion valoracion;
		switch(event) {
		case EventsValoraciones.PUBLICAR_COMENTARIO:
			comentario = (TransferComentario) data;
			sa.publicarComentario(comentario);
			valoracion = sa.getValoracionForComentario(comentario);
			controller.setPrincipalPanel(new ViewValoracion(valoracion,this, controller.getCurrentUser() ));
			break;
		case EventsValoraciones.SHOW_FORM_COMENTAR:
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(new ViewFormComentario(valoracion.getID(), controller.getCurrentUser(), valoracion.getGame(),this) );
			break;
		case EventsValoraciones.ELIMINAR_COMENTARIO:
			comentario = (TransferComentario) data;
			sa.eliminarComentario(comentario);
			valoracion = sa.getValoracionForComentario(comentario);
			controller.setPrincipalPanel(new ViewValoracion(valoracion, this, controller.getCurrentUser()));
			break;
		case EventsValoraciones.SHOW_FORM_MODIFICAR_COMENTARIO:
			comentario = (TransferComentario) data;
			controller.setPrincipalPanel(new ViewFormComentario(comentario,this));
			break;
		case EventsValoraciones.PUBLICAR_VALORACION:
			valoracion = (TransferValoracion) data;
			sa.publicarValoracion(valoracion);
			controller.setPrincipalPanel(new ViewValoracion(valoracion,this,controller.getCurrentUser()));
			break;
		case EventsValoraciones.ELIMINAR_VALORACION:
			valoracion = (TransferValoracion) data;
			sa.eliminarValoracion(valoracion);
			controller.setPrincipalPanel(controller.getBiblioteca());
			break;
		case EventsValoraciones.SHOW_FORM_MODIFICAR_VALORACION:
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(new ViewFromValoracion(valoracion,this));
			break;
		case EventsValoraciones.SHOW_VALORACION:
			valoracion = (TransferValoracion) data;
			controller.setPrincipalPanel(new ViewValoracion(valoracion, this,controller.getCurrentUser()));
			break;
		}
	}

	public JPanel getFormValoraciones(UsuarioDTO user, JuegoDTO game) {
		return new ViewFromValoracion(user,game,this);
	}

	public void eliminarJuego(String id) {
		sa.releaseValoracionesJuego(id);
	}
	
	public void deleteUserDocuments(String idUser) {
		try {
			sa.deleteUserDocuments(idUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TransferComentario> getComentarios(TransferValoracion tValoracion) throws IOException {
		return sa.getComentarios(tValoracion);
	}
}
