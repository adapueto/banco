package baseDeDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dato.CuentaBancaria;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection; // crear la conexiòn y habrir la base de datos
import java.sql.Statement; // crear y ejecutar sentencias SQL
//import java.sql.PreparedStatement; // crear y ejecutar sentencias SQL usando pasaje de paramentros
import java.sql.ResultSet; // obtiene los resultados de las sentencias SQL
import java.sql.DriverManager; // uso del driver de MySQL
import java.sql.SQLException; // mensajes de error sean en SQL

public class MetodosDB {
	// Datos para realizar la conexión con la base de datos
	private static String usuario = "root";
	private static String pass = "123456789";
	private static String baseDeDatos = "banco2mn";
	private static String url = "jdbc:mysql://localhost:3306/" + baseDeDatos + "?useSSL=false&serverTimezone=UTC";

	// objetos que manipulan/actualizan la base de datos
	private static Connection conectar;
	private static Statement sentenciaSQL;
	private static ResultSet resultado;

	// Cargar el driver de MySQL para trabajar con JAVA

	public static boolean cargarDriverMySQL() {

		try {
			// Carga del driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			return true;

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: No se encuentra el driver de MySQL", "Error fatal...",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

	} // fin del método cargarDriverMySQL

	// conectar con la base de datos

	public static Connection conectarDB() {

		try {
			conectar = DriverManager.getConnection(url, usuario, pass);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error...",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return conectar;
	}// fin del método ConectarDB
	
	// Insertar una nueva TUPLA en la tabla

	public static void insertarNuevaCuenta(CuentaBancaria miDatos, String sql) {

		try {
			conectar = conectarDB(); // me conecto

			sentenciaSQL = conectar.createStatement(); // crear el objeto para la sentencia SQL

			int filasInsertadas = sentenciaSQL.executeUpdate(sql); // executeUpdate se usa con INSERT, UPDATE y DELETE

			if (filasInsertadas > 0) {
				JOptionPane.showMessageDialog(null, "La cuenta fue registrada", "Registro...",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error no se pudo realizar el guardado o ya fue guardado", "Error...",
					JOptionPane.ERROR_MESSAGE);
			// e.printStackTrace(); // usar para verificar errores en la consola
		} finally {
			try {
				if (sentenciaSQL != null)
					sentenciaSQL.close();
				if (conectar != null)
					conectar.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}// fin del método insertarNuevaCuenta

	// método para buscar por PK, retorna true si lo encuentra sino false
	
	public static boolean buscarPorPK(String sql) {
		  
		 boolean encontre=false;
		  
	         try {
	             conectar = conectarDB();
	             sentenciaSQL = conectar.createStatement();
	             resultado = sentenciaSQL.executeQuery(sql);

	             if (resultado.next()) {
	            	 
	            	 encontre=true;
	            	 
	            	 // muestra en la consula la tupla si necesito verificar algo
//	            	 System.out.println("✅ Lacedula fue encontrada:");
//	                 System.out.println("Cédula: " + resultado.getDouble("bancoCuentaCedula"));
//	                 System.out.println("Nombre: " + resultado.getString("bancoCuentaNombre"));
//	                 System.out.println("Apellido: " + resultado.getString("bancoCuentaApellido"));
	                 
	             }

	         } catch (SQLException e) {
	             System.out.println("❌ Error al realizar la consulta.");
	             e.printStackTrace();
	         } finally {
	             try {
	                 if (resultado != null) resultado.close();
	                 if (sentenciaSQL != null) sentenciaSQL.close();
	                 if (conectar != null) conectar.close();
	             } catch (SQLException ex) {
	                 ex.printStackTrace();
	             }
	         }
	         return encontre;
	     }// fin de método buscarporPK
	
	
	
public static JTable cargarJTable(JTable x,String sql, String columnas[]) {
		
		
		//DefaultTableModel modeloMiTabla;
		
		// cantidad de columnas de la JTABLE las columnas fijas
		// las paso como parámetro porque cambian segun la consultas
		//String columnas[]= {"Monto","Fecha","Tipo de Movimiento "};
		
		// filas en las columnas, puede ser un parámetro más del método
		// si la cantidad de columnas depende de los atributos de la tabla
		Object filas[]= new Object[3];
		
		   // 🔒 Modelo editable de la tabla
	    DefaultTableModel modeloMiTabla = new DefaultTableModel(null, columnas);
		
		// IMPORTANTE PARA QUE SEA NO EDITABLE HAY QUE DEFINIR LA TABLE
	    // DE LA SIGUIENTE MANERA
	    // tabla no editable
//	 		table = new JTable() { 
//	 		    @Override
//	 		    public boolean isCellEditable(int row, int column) {
//	 		        return false; // 🔒 Bloquea edición en toda la tabla
//	 		    }
//	 		};
		
	    
		modeloMiTabla= new DefaultTableModel(null, columnas);
		
		try {
		
			conectar= DriverManager.getConnection(url,usuario,pass);
	
			sentenciaSQL = conectar.createStatement();
	
    		resultado = sentenciaSQL.executeQuery(sql);

    		while (resultado.next()) {
    			
    			for ( int i=0;i<filas.length;i++){
    				
    				filas[i] = resultado.getObject(i+1); 

    			} 
    			
    		
					modeloMiTabla.addRow(filas);
				 			
    			
     		}
		    		
			x.setModel(modeloMiTabla);
			
		    // 🔒 Bloquear edición a nivel JTable (clave si algún editor queda activo)
//	        x.setDefaultEditor(Object.class, null);
//	        x.setDefaultEditor(String.class, null);
//	        x.setDefaultEditor(Number.class, null);
//	        x.setDefaultEditor(Integer.class, null);
//	        x.setDefaultEditor(Double.class, null);
//	        x.setDefaultEditor(Float.class, null);
//	        x.setDefaultEditor(Boolean.class, null);
//	        x.setCellSelectionEnabled(false);
			
			
			//IMPORTANTE PARA QUE SE VISUALICEN LOS TÍTULOS
			// LA JTable DEBE TENER scrollPane
			
			   // 🎨 Personalización del encabezado
	        JTableHeader header = x.getTableHeader();
	        header.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // Cambia "Tahoma" por tu fuente preferida
	        header.setBackground(new Color(230, 230, 250));   // Suave tono lavanda (opcional)
	        header.setForeground(new Color(50, 50, 50));      // Texto gris oscuro
	        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

	        // Fuente de las celdas (opcional)
	        x.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
	        x.setRowHeight(25);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("este msg "+e.getMessage());
			 e.printStackTrace();
		} finally {
             try {
                 if (resultado != null) resultado.close();
                 if (sentenciaSQL != null) sentenciaSQL.close();
                 if (conectar != null) conectar.close();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
		}
		return x;
	}
  
	
	
	
}
