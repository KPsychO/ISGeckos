package valoraciones.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FacadeDeleteUserDocuments {
	Storage almacen;
	String idUser;

	//String path = pathComentarios + "/" + valoracion.getGame().get_id() + "/"+valoracion.getID()+"/";
	//path = pathValoraciones + "/"+ valoracion.getGame().get_id()+"/"+valoracion.getID()+".txt";
	
	/*
	 * 
		s[0] = id+"";
		s[1] = valoracionID+"";
		s[2] = comentario.getText();
		s[3] = user.get_user_id();
		s[4] = game.get_id();
		s[5] = date;
	 */
	
	public FacadeDeleteUserDocuments() {
		
	}
	
	public void deleteDocuments(String idUser) throws FileNotFoundException, IOException {
		almacen = new Storage();
		this.idUser = idUser;
		deleteValoracionesAssociates();
		deleteComentariosAssociates();
		System.out.println(  );
	}

	private void deleteComentariosAssociates() throws FileNotFoundException, IOException {
		String pathComentarios = almacen.pathComentarios + "/";
		File folderJuegosComentados = new File(pathComentarios);
		
		for(File folderJuego : folderJuegosComentados.listFiles()) {
			
			for(File folderComentarios : folderJuego.listFiles()) {
				
				for(File comentario : folderComentarios.listFiles()) {
					
					Object[] data = almacen.loadArchiveData(comentario.getPath());
					
					if( ((String)data[3]).equals(idUser) ) {
						almacen.deleteFolder(comentario.getPath());
					}
				}
			}
		}
		
	}

	private void deleteValoracionesAssociates() throws FileNotFoundException, IOException {
		String pathJuegosValorados = almacen.pathValoraciones;
		
		File folder = new File(pathJuegosValorados+"/");
		
		for(File folderJuego : folder.listFiles() ) {
			
			for(File file : folderJuego.listFiles()) {
				
				Object[] data = almacen.loadArchiveData(file.getPath());
				
				if( ((String)data[1]).equals(idUser)) {
					String pathComentarios = almacen.pathComentarios + "/" + (String)data[2] + "/" + (String)data[0] + "/";
					almacen.deleteFolder(pathComentarios);
					String pathValoracion = almacen.pathValoraciones + "/" + (String)data[2] + "/" + (String)data[0] + ".txt";
					almacen.deleteFolder(pathValoracion);
				}
				
			}
		}
		
	}

}
