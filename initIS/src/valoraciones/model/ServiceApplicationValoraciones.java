package valoraciones.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ServiceApplicationValoraciones {
	
	public void publicarComentario(TransferComentario comentario) throws IOException {
		new DAOComentario().createComentario(comentario);
	}
	
	public TransferValoracion getValoracionForComentario(TransferComentario comentario) throws IOException {
		return new DAOValoracion().getValoracion(comentario.getValoracionID(),comentario.getGame());
	}
	
	public void eliminarComentario(TransferComentario comentario) {
		new DAOComentario().deleteComentario(comentario);
	}
	
	public void publicarValoracion(TransferValoracion valoracion) throws IOException {
		new DAOValoracion().createValoracion(valoracion);
	}

	public void eliminarValoracion(TransferValoracion valoracion) {
		new DAOValoracion().deleteValoracion(valoracion);
	}
	
	public void releaseValoracionesJuego(String idJuego) {
		new Storage().releaseValoracionesJuego(idJuego);
	}
	
	public void deleteUserDocuments(String idUser) throws FileNotFoundException, IOException {
		new FacadeDeleteUserDocuments().deleteDocuments(idUser);
	}
}
