package valoraciones.model;

import java.io.IOException;
import java.util.ArrayList;

public class DAOComentario {
	private Storage almacen;
	
	public DAOComentario() {
		almacen = new Storage();
	}
	
	public void createComentario(TransferComentario comentario) throws IOException {
		if(comentario.getID()==0)
			comentario.setID(almacen.getLastComentario(comentario)+1);
		
		almacen.storeComentario(comentario);
	}
	
	public void deleteComentario(TransferComentario comentario) {
		almacen.releaseComentario(comentario);
	}
	
	public void updateComentario(TransferComentario comentario) throws IOException {
		almacen.storeComentario(comentario);
	}
	
	public ArrayList<TransferComentario> getComentarios(TransferValoracion valoracion) throws IOException {
		return almacen.loadComentarios(valoracion);
	}
}
