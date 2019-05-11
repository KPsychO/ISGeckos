package valoraciones.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Juego.Control.JuegoDTO;

public class Storage {
	protected String pathValoraciones = "resources/valoraciones/valoraciones";
	protected String pathComentarios = "resources/valoraciones/comentarios";
	
	public Storage() {}
	
	public void storeValoracion(TransferValoracion valoracion) throws IOException {
		String path = pathValoraciones + "/" + valoracion.getGame().get_id()+"/"+valoracion.getID()+".txt";
		this.storeArchiveData(path, valoracion.getToStore());
	}
	
	public void storeComentario(TransferComentario comentario) throws IOException {
		String path = pathComentarios + "/" + comentario.getGame().get_id() + "/" + comentario.getValoracionID() + "/" + comentario.getID() + ".txt";
		this.storeArchiveData(path, comentario.getToStore());
	}

	public TransferValoracion loadValoracion(JuegoDTO game,int valoracionID) throws IOException{
		String path = pathValoraciones + "/" + game.get_id()+"/"+valoracionID+".txt";
		Object[] data = this.loadArchiveData(path);
		return new TransferValoracion(data);
	}
	
	public TransferComentario loadComentario(TransferValoracion valoracion, int comentarioID) throws IOException{
		String path = pathValoraciones + "/" + valoracion.getGame().get_id() +"/"+valoracion.getID()+"/"+comentarioID+".txt";
		Object[] data = this.loadArchiveData(path);
		return new TransferComentario(data);
	}

	public ArrayList<TransferValoracion> loadValoraciones(JuegoDTO juego) throws IOException{
		String path = pathValoraciones + "/" + juego.get_id() +"/";
		ArrayList<TransferValoracion> valoraciones = new ArrayList<TransferValoracion>();
		File folder = new File(path);

		if(folder.exists())
			for ( File fileEntry : folder.listFiles()) {
				valoraciones.add(new TransferValoracion(this.loadArchiveData(fileEntry.getPath())));
			}
		return valoraciones;
	}
	
	public ArrayList<TransferComentario> loadComentarios(TransferValoracion valoracion) throws IOException{
		String path = pathComentarios + "/" + valoracion.getGame().get_id()+"/"+valoracion.getID()+"/";
		ArrayList<TransferComentario> comentarios = new ArrayList<TransferComentario>();
		
		File folder = new File(path);
		this.prepareDirectory(folder);
		for ( File fileEntry : folder.listFiles()) {
			comentarios.add(new TransferComentario(this.loadArchiveData(fileEntry.getPath())));
		}
		return comentarios;
	}
	
	public int getLastValoracion(JuegoDTO juego) {
		String path = pathValoraciones + "/" + juego.get_id() +"/";
		File folder = new File(path);
		
		if(!folder.exists())
			return 0;
		
		if(folder.listFiles().length==0)
			return 0;
		
		String name = folder.listFiles()[folder.listFiles().length-1].getName();
		
		return Integer.valueOf(name.substring(0, name.indexOf(".")));
	}
	

	public int getLastComentario(TransferComentario comentario) {
		String path = pathComentarios + "/" + comentario.getGame().get_id() +"/"+ comentario.getValoracionID() + "/";
		
		File folder = new File(path);
		
		if(!folder.exists())
			return 0;
		if(folder.listFiles().length==0)
			return 0;
		
		String name = folder.listFiles()[folder.listFiles().length-1].getName();
		
		return Integer.valueOf(name.substring(0, name.indexOf(".")));
	}

	public void releaseValoracion(TransferValoracion valoracion) {
		String path = pathComentarios + "/" + valoracion.getGame().get_id() + "/"+valoracion.getID()+"/";
		deleteFolder(path);
		path = pathValoraciones + "/"+ valoracion.getGame().get_id()+"/"+valoracion.getID()+".txt";
		deleteFolder(path);
	}

	public void releaseComentario(TransferComentario comentario) {
		String path = pathComentarios + "/" + comentario.getGame().get_id() + "/"+comentario.getValoracionID()+"/"+comentario.getID()+".txt";
		deleteFolder(path);
	}
	
	public void releaseValoracionesJuego(String gameID) {
		String path = pathValoraciones + "/" + gameID +"/";
		deleteFolder(path);
		path = pathComentarios + "/" + gameID +"/";
		deleteFolder(path);
	}
	
	protected Object[] loadArchiveData(String path) throws FileNotFoundException,IOException {
		File file = new File(path);
		this.prepareDirectory(file.getParentFile());
		InputStream is = new FileInputStream(file); 
		BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
		String line = buf.readLine();  
		List<Object> object = new ArrayList<Object>();
		while(line != null){ 
			object.add(line);
			line = buf.readLine(); 
		}
		is.close();
		buf.close();
		return object.toArray(new Object[object.size()]);
	}

	private void storeArchiveData(String path,String[] data) throws IOException {
		File file = new File(path);
		this.prepareDirectory(file.getParentFile());
        FileWriter fr = new FileWriter(file);
        BufferedWriter br = new BufferedWriter(fr);
        
        for(String line : data)
        	br.write(line+"\n");
        
        br.close();
        fr.close();
	}
	
	private void prepareDirectory(File folder) {
		if(!folder.getParentFile().exists())
			prepareDirectory(folder.getParentFile());
		if(!folder.exists())
			folder.mkdir();
	}
	
	protected void deleteFolder(String path) {
		File file = new File(path);
		if(!file.exists())
			return;
		
        if(file.isDirectory()){
            if(file.list().length == 0)
            	file.delete();
            else{
                
               for (File fileEntry : file.listFiles()) {
                   deleteFolder(fileEntry.getPath());
               }
               if(file.list().length==0)
            	   file.delete();
            }
        }
        else
        	file.delete();            
    }
}
