package Juego.Control;

public class JuegoDTO {
	
	String _id;
	String _title;
	String _desc;
	int _pegi;
	int _price;
	// la imagen "thumb"
	
	//todo el resto de mierda, a completar

	public JuegoDTO(String id, String title, int price, int pegi, String desc){
		
		_id = id;
		_title = title;
		_desc = desc;
		_pegi = pegi;
		_price = price;
		
	}
	
	public String getId() {
		
		return _id;
		
	}
	
	public String getTitle() {
		
		return _title;
		
	}
	
	public String getDesc() {
		
		return _desc;
		
	}
	
	public int getPrice() {
		
		return _price;
		
	}
	
	public int getPegi() {
		
		return _pegi;
		
	}
	
}
