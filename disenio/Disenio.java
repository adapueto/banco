package disenio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Disenio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelDisenio;
	private PanelDisenio panelInicial=null;
	private static PanelNuevaCuenta nuevaCuenta=nuevaCuenta=new PanelNuevaCuenta();
	private static PanelConsulta consulta=consulta=new PanelConsulta();

	/**
	 * Create the frame.
	 */
	public Disenio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Disenio.class.getResource("/disenio/images billetes.jpg")));
	
		setTitle("Banco - Gestión de cuentas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(119, 136, 153), 1, true));
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(10, 11, 833, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Nueva Cuenta");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane.remove(panelInicial);
				contentPane.remove(consulta);
				contentPane.add(nuevaCuenta);
				repaint();
				
				
			}
		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 11, 192, 31);
		panel.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEditar.setBounds(212, 11, 112, 31);
		panel.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBorrar.setBounds(334, 11, 101, 31);
		panel.add(btnBorrar);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDepositar.setBounds(445, 11, 130, 31);
		panel.add(btnDepositar);
		
		JButton btnExtraer = new JButton("Extraer");
		btnExtraer.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExtraer.setBounds(585, 11, 101, 31);
		panel.add(btnExtraer);
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				contentPane.remove(panelInicial);
				contentPane.remove(nuevaCuenta);
				contentPane.add(consulta);
				repaint();
				
				
			}
		});
		btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConsultas.setBounds(696, 11, 127, 31);
		panel.add(btnConsultas);
		
		
		panelInicial=new PanelDisenio();
		contentPane.add(panelInicial);
		
	}
}
