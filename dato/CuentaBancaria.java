package dato;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class CuentaBancaria {

	// declaro atributos de Clase
	private String nombre;
	private String apellido;
	private long cedula;
	private Tipo_Cuenta tipoDeCuenta;
	private Date fechaApertura;
	private static double saldo;

	// declaro una CONSTANTE

	private final static int MINIMO_DEPOSITO = 500;
	private final static int MAXIMO_DEPOSITO = 50000;

	// Constructor por defecto

	public CuentaBancaria() {
		super();
	}

	// Método set del atributo nombre

	public void setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}

	// Método get del atributo nombre

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public Tipo_Cuenta getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(Tipo_Cuenta tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public double getSaldo() {
		return saldo;
	}

	public double setSaldo(double saldo) {
		return this.saldo=saldo;
		}

	// Método para Convertir un String a Date
	// CUIDADO retorno null cuando la fecha esta mal ingresada

	public static Date validarFecha(String fecha) {

		SimpleDateFormat miFecha = new SimpleDateFormat("dd/MM/yyyy");

		boolean valida = true;
		do {
			try {
				return miFecha.parse(fecha);
			} catch (ParseException e) {

				JOptionPane.showMessageDialog(null, "Error de ingreso en la fecha (dd/MM/yyyy)", "Error de ingrese",
						JOptionPane.ERROR_MESSAGE);
				valida = false;
			}
			if (!valida) {
				fecha = JOptionPane.showInputDialog("Ingrese Fecha de apertura");
			}
		} while (!valida);

		return null;
	}

	// método para depositar dinero en la cuenta
	public static void setDeposito(double monto) {
		do {
			if (monto >= MINIMO_DEPOSITO && monto <= MAXIMO_DEPOSITO) {
				saldo = saldo + monto;
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Error, debe ingresar un monto mayor a $500 o menor a $50000");
				do {
					try {

						monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Monto a Depositar"));
						break;

					} catch (java.lang.NumberFormatException papasFritas) {

						JOptionPane.showMessageDialog(null, "Error de ingreso en el MONTO, ingrese nuevamente");
					}
				} while (true);
			}
		} while (true);
	}// fin del método setDeposito

	// método para extraer dinero de la cuenta

	public static void setExtraccion(double monto) {
		do {
			if (monto >= MINIMO_DEPOSITO && monto <= MAXIMO_DEPOSITO) {
				if (saldo >= monto) {
					saldo = saldo - monto;
				} else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente, saldo actual: " + saldo);
				}
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Error, debe ingresar un monto mayor a $500 o menor a $50000");
				do {
					try {
						monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Monto a Depositar"));
						break;
					} catch (java.lang.NumberFormatException papasFritas) {
						JOptionPane.showMessageDialog(null, "Error de ingreso en el MONTO, ingrese nuevamente");
					}
				} while (true);
			}
		} while (true);
		
		/// esta mal no pide monto de nuevo cuando no tengo salgo
		
	}// fin del método setExtraccion
	
	public static void mostrarEstadoDeCuenta(CuentaBancaria miCuenta) {
		
		SimpleDateFormat miFormatoFecha=new SimpleDateFormat("dd/MM/yyyy");
		
		JOptionPane.showMessageDialog(null,
				"Nombre: "+ miCuenta.getNombre()+
				"\nApeliido: "+miCuenta.getApellido()+
				"\nCédula: "+miCuenta.getCedula()+
				"\nTipo de Cuenta: "+miCuenta.getTipoDeCuenta()+
				"\nFecha de apertura de la cuenta: "+
				miFormatoFecha.format(miCuenta.getFechaApertura())+
				"\nSaldo actual: "+miCuenta.getSaldo());
	}
	

}// fin de clase
