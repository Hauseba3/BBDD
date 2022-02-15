package pruebas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
import net.miginfocom.swing.MigLayout;

public class VentanaAñadirEditoriales extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtAño;
	private JButton btnOk;
	private JButton btnCancelar;
	private Controlador controlador;

	/**
	 * Create the dialog.
	 */
	public VentanaAñadirEditoriales() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		{
			JLabel lblTitulo = new JLabel("Insertar nueva Editorial");
			lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
			contentPanel.add(lblTitulo, "cell 0 0 2 2");
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblNombre, "cell 0 2,alignx trailing");
		}
		{
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtNombre, "cell 1 2,growx");
			txtNombre.setColumns(10);
		}
		{
			JLabel lblAño = new JLabel("A\u00F1o:");
			lblAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblAño, "cell 0 4,alignx trailing");
		}
		{
			txtAño = new JTextField();
			txtAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtAño, "cell 1 4,growx");
			txtAño.setColumns(10);
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
		try {
			Editorial ed = new Editorial();
			ed.setNombre(txtNombre.getText());
			ed.setAño(Integer.parseInt(txtAño.getText()));
			
			controlador.insertarEditorial(ed);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Introduzca un año correcto!");
		}
		
	}


	public void setControlador(Controlador controlador) {
		
		this.controlador = controlador;
		
	}
	
	

}
