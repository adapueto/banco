package disenio;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import baseDeDatos.MetodosDB;
import dato.CuentaBancaria;
import dato.Tipo_Cuenta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelNuevaCuenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtSaldo;
	private JComboBox<Tipo_Cuenta> comboBox;
	private Date fechaActual;
	private static SimpleDateFormat miFormatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	private JLabel lblError;
	private JLabel lblNewLabel;
	private JPanel panelNombre;
	private JPanel panelTipoCuenta;
	private JPanel panelFecha;
	private JPanel panelSaldo;
	private JButton btnGuardar;
	private JButton btnNueva;
	/**
	 * Create the panel.
	 */
	public PanelNuevaCuenta() {

		setBorder(new LineBorder(new Color(119, 136, 153), 1, true));
		setBounds(10, 72, 833, 392);
		setLayout(null);
		
		panelNombre = new JPanel();
		panelNombre.setVisible(false);
		panelNombre.setDoubleBuffered(false);
		panelNombre.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelNombre.setBackground(new Color(192, 192, 192));
		panelNombre.setBounds(31, 43, 288, 102);
		add(panelNombre);
		panelNombre.setLayout(null);
		
		lblNewLabel = new JLabel("Ingrese NOMBRE y APELLIDO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 265, 34);
		panelNombre.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombre.setBounds(10, 56, 126, 34);
		panelNombre.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtApellido.setColumns(10);
		txtApellido.setBounds(149, 56, 126, 34);
		panelNombre.add(txtApellido);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(392, 43, 412, 102);
		add(panel_1);
		
		JLabel lblIngreseCdulaDe = new JLabel("Ingrese CÉDULA de IDENTIDAD");
		lblIngreseCdulaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseCdulaDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngreseCdulaDe.setBounds(10, 11, 235, 34);
		panel_1.add(lblIngreseCdulaDe);
		
		txtCedula = new JTextField();
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				lblError.setVisible(false);
				
				
				
			}
		});
		txtCedula.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCedula.setColumns(10);
		txtCedula.setBounds(272, 12, 126, 34);
		panel_1.add(txtCedula);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="SELECT * FROM banco2mn.bancocuenta where bancoCuentaCedula='"+txtCedula.getText()+"';";
				if(MetodosDB.buscarPorPK(sql)) {
					lblError.setVisible(true);
					txtCedula.setText("");
					txtCedula.requestFocus();
				}else {
					panelNombre.setVisible(true);
					panelFecha.setVisible(true);
					panelTipoCuenta.setVisible(true);
					btnGuardar.setVisible(true);
					btnNueva.setVisible(true);
					panelSaldo.setVisible(true);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(293, 57, 89, 23);
		panel_1.add(btnNewButton_1);
		
		lblError = new JLabel("La CÉDULA ya tiene una cuenta registrada");
		lblError.setVisible(false);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(10, 56, 273, 35);
		panel_1.add(lblError);
		
		panelTipoCuenta = new JPanel();
		panelTipoCuenta.setVisible(false);
		panelTipoCuenta.setLayout(null);
		panelTipoCuenta.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelTipoCuenta.setBackground(Color.LIGHT_GRAY);
		panelTipoCuenta.setBounds(31, 167, 288, 113);
		add(panelTipoCuenta);
		
		JLabel lblSeleccioneElTipo = new JLabel("Seleccione el TIPO DE CUENTA");
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeleccioneElTipo.setBounds(10, 11, 265, 34);
		panelTipoCuenta.add(lblSeleccioneElTipo);
		
		comboBox = new JComboBox<Tipo_Cuenta>();
		comboBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		// cargar el combo con los datos del enumerado
		comboBox.setModel(new DefaultComboBoxModel<Tipo_Cuenta>(Tipo_Cuenta.values()));
		comboBox.setBounds(20, 57, 244, 34);
		panelTipoCuenta.add(comboBox);
		
		panelFecha = new JPanel();
		panelFecha.setVisible(false);
		panelFecha.setLayout(null);
		panelFecha.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelFecha.setBackground(Color.LIGHT_GRAY);
		panelFecha.setBounds(392, 188, 412, 58);
		add(panelFecha);
		
		fechaActual=new Date(); // el objeto tambien es global
		
		
		
		JLabel lblFechaAperturaDe = new JLabel("Fecha APERTURA DE CUENTA: "+miFormatoFecha.format(fechaActual));
		lblFechaAperturaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAperturaDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaAperturaDe.setBounds(10, 11, 392, 34);
		panelFecha.add(lblFechaAperturaDe);
		
		panelSaldo = new JPanel();
		panelSaldo.setVisible(false);
		panelSaldo.setLayout(null);
		panelSaldo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelSaldo.setBackground(Color.LIGHT_GRAY);
		panelSaldo.setBounds(30, 306, 473, 58);
		add(panelSaldo);
		
		JLabel lblIngreseSaldoInicial = new JLabel("Ingrese SALDO INICIAL DE LA CUENTA");
		lblIngreseSaldoInicial.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngreseSaldoInicial.setBounds(10, 11, 291, 34);
		panelSaldo.add(lblIngreseSaldoInicial);
		
		txtSaldo = new JTextField();
		txtSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(331, 12, 126, 34);
		panelSaldo.add(txtSaldo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CuentaBancaria misDatos=new CuentaBancaria();
				
				misDatos.setNombre(txtNombre.getText());
				misDatos.setApellido(txtApellido.getText());
				misDatos.setCedula(Long.parseLong(txtCedula.getText()));
				misDatos.setTipoDeCuenta((Tipo_Cuenta) comboBox.getSelectedItem());
				misDatos.setFechaApertura(fechaActual);
				double s=Double.parseDouble(txtSaldo.getText());
				misDatos.setSaldo(s);
				
				String sql="INSERT INTO banco2mn.bancocuenta (bancoCuentaCedula,"
						+ "bancoCuentaNombre,bancoCuentaApellido,"
						+ "bancoCuentaTipo_Cuenta,bancoCuentaFechaApertura,"
						+ "bancoCuentaSaldo) VALUES ('"+misDatos.getCedula()+
						"','"+misDatos.getNombre()+"','"+misDatos.getApellido()+
						"','"+misDatos.getTipoDeCuenta()+
						"','"+miFormatoFecha.format(misDatos.getFechaApertura())+
						"','"+misDatos.getSaldo()+"')";
				
				
				MetodosDB.insertarNuevaCuenta(misDatos, sql);
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGuardar.setBounds(602, 281, 121, 31);
		add(btnGuardar);
		
		btnNueva = new JButton("Nueva");
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				panelNombre.setVisible(false);
				panelFecha.setVisible(false);
				panelTipoCuenta.setVisible(false);
				btnGuardar.setVisible(false);
				btnNueva.setVisible(false);
				panelSaldo.setVisible(false);
				txtNombre.setText("");
				txtCedula.setText("");
				txtApellido.setText("");
				comboBox.setSelectedIndex(0); // primer item de la lista
				txtSaldo.setText("");
				txtCedula.requestFocus();
				
				
				
				
				
				
			}
		});
		btnNueva.setVisible(false);
		btnNueva.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNueva.setBounds(602, 330, 121, 31);
		add(btnNueva);
		
		JLabel lblNewLabel_1 = new JLabel("Ejemplo: 2.222.222-2 debe ingresar 22222222");
		lblNewLabel_1.setBounds(402, 127, 253, 20);
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		
	}
}
