package disenio;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

import baseDeDatos.MetodosDB;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JLabel lblError;
	private JComboBox<String> comboBox;
	private JButton btnAceptar;
	private JPanel panelCedula;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public PanelConsulta() {
		setLayout(null);
		setBounds(10, 72, 833, 392);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(10, 11, 633, 77);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciones LA CONSULTA que desee");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 24, 270, 29);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelCedula.setVisible(true);
				
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Consultar el SALDO", "Consultar Estado de Cuenta", "Consultar DEPÓSITOS", "Consultar EXTRACCIONES"}));
		comboBox.setBounds(290, 24, 252, 29);
		panel.add(comboBox);
		
		panelCedula = new JPanel();
		panelCedula.setVisible(false);
		panelCedula.setLayout(null);
		panelCedula.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelCedula.setBackground(Color.LIGHT_GRAY);
		panelCedula.setBounds(10, 99, 633, 58);
		add(panelCedula);
		
		JLabel lblIngreseCdulaDe = new JLabel("Ingrese CÉDULA de IDENTIDAD");
		lblIngreseCdulaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseCdulaDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngreseCdulaDe.setBounds(10, 11, 235, 34);
		panelCedula.add(lblIngreseCdulaDe);
		
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
		panelCedula.add(txtCedula);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="SELECT * FROM banco2mn.bancocuenta where bancoCuentaCedula='"+txtCedula.getText()+"';";
				if(!MetodosDB.buscarPorPK(sql)) {
					lblError.setVisible(true);
					txtCedula.setText("");
					txtCedula.requestFocus();
				}else {
					
					if (comboBox.getSelectedIndex()==0) {
						sql="SELECT bancoCuentaNombre,bancoCuentaApellido,bancoCuentaSaldo FROM banco2mn.bancocuenta where bancoCuentaCedula='"+txtCedula.getText()+"';";
						table=MetodosDB.cargarJTable(table, sql, new String [] {"Nombre","Apellido","Saldo"} );
					}else{
						if (comboBox.getSelectedIndex()==1) {
							sql="SELECT bancomovimientosMonto,bancomovimientosTipo,bancomovimientosFecha FROM banco2mn.bancomovimientos where bancomovimientosCedula='"+txtCedula.getText()+"';";
					
						}else {
							if (comboBox.getSelectedIndex()==2) {
								sql="SELECT bancomovimientosMonto,bancomovimientosTipo,bancomovimientosFecha FROM banco2mn.bancomovimientos where bancomovimientosCedula='"+txtCedula.getText()+"' and bancomovimientosTipo='D';";
							}else {
								sql="SELECT bancomovimientosMonto,bancomovimientosTipo,bancomovimientosFecha FROM banco2mn.bancomovimientos where bancomovimientosCedula='"+txtCedula.getText()+"' and bancomovimientosTipo='E';";
							}
						}
						table=MetodosDB.cargarJTable(table, sql, new String [] {"Monto","Tipo Deposito","Fecha"} );
					}
					
					scrollPane.setVisible(true);
					table.setVisible(true);
					
					
				}
				
				
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setBounds(465, 18, 89, 23);
		panelCedula.add(btnAceptar);
		
		lblError = new JLabel("");
		lblError.setVisible(false);
		lblError.setIcon(new ImageIcon(PanelConsulta.class.getResource("/disenio/error_st_obj@2x.png")));
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(408, 10, 34, 35);
		panelCedula.add(lblError);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setWheelScrollingEnabled(false);
		scrollPane.setBounds(10, 168, 633, 213);
		add(scrollPane);
		
		table = new JTable() {
			/**
			 * para que la tabla/celdas sea no editable
			 */
			private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int row, int column) {
		            return false; // evita que se edite cualquier celda
		        }
		};
		scrollPane.setViewportView(table);

	}
}
