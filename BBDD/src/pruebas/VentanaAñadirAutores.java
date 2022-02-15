package pruebas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Autor;
import net.miginfocom.swing.MigLayout;

public class VentanaAñadirAutores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private Controlador controlador;
	private JButton btnCancelar;
	private JButton btnOk;


	/**
	 * Create the dialog.
	 */
	public VentanaAñadirAutores() {
		
		setBounds(100, 100, 435, 324);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[52.00][][]"));
		{
			JLabel lblTitulo = new JLabel("Inserccion de autores");
			lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
			contentPanel.add(lblTitulo, "cell 0 0 3 1");
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblNombre, "cell 1 1,alignx trailing");
		}
		{
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtNombre, "cell 2 1,growx");
			txtNombre.setColumns(10);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblApellidos, "cell 1 2,alignx trailing");
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtApellidos, "cell 2 2,growx");
			txtApellidos.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						recogerDatos();
						
					}
				});
				btnOk.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						
					}
				});
				btnCancelar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	protected void recogerDatos() {
		
		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		
		Autor a = new Autor();
		a.setNombre(nombre);
		a.setApellidos(apellidos);
		
		controlador.insertarAutor(a);
		
	}

	public void setControlador(Controlador controlador) {
		
		this.controlador = controlador;
		
	}

}
