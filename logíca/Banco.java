package logíca;

import baseDeDatos.MetodosDB;
import disenio.Disenio;

public class Banco {

	public static void main(String[] args) {
		
		if (MetodosDB.cargarDriverMySQL()) {
			Disenio banco=new Disenio();
			banco.setVisible(true);
		}
		
		
	}

}
