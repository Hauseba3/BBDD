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

public class VentanaActualizarEditorial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtAño;
	private JButton btnCancelar;
	private JButton btnActualizar;
	private JPanel buttonPane;
	private Controlador controlador;
	private JLabel lblEditorial;
	private JLabel lblCodEditorial;
	
	//private Editorial editorial;

	/**
	 * Create the dialog.
	 */
	public VentanaActualizarEditorial() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		{
			JLabel lblTitulo = new JLabel("Actualizar Editorial");
			lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 26));
			contentPanel.add(lblTitulo, "cell 0 0 2 1");
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
			contentPanel.add(lblAño, "cell 0 3,alignx trailing");
		}
		{
			txtAño = new JTextField();
			txtAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtAño, "cell 1 3,growx");
			txtAño.setColumns(10);
		}
		{
			lblEditorial = new JLabel("CodEditorial:");
			lblEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblEditorial, "cell 0 4");
		}
		{
			lblCodEditorial = new JLabel("");
			lblCodEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblCodEditorial, "cell 1 4");
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						llamarActualizarEditorial();
						
					}
				});
				btnActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnActualizar.setActionCommand("OK");
				buttonPane.add(btnActualizar);
				getRootPane().setDefaultButton(btnActualizar);
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

	protected void llamarActualizarEditorial() {
		
		try {
		int codEditorial = Integer.parseInt(lblCodEditorial.getText());	
		String nombre = txtNombre.getText();
		int año = Integer.parseInt(txtAño.getText());
		
		Editorial ed = new Editorial();
		ed.setCodEditorial(codEditorial);
		ed.setNombre(nombre);
		ed.setAño(año);
		
		controlador.actualizarEditoriales(ed);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Introduce un año correcto!");
		}
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setEditorial(Editorial ed) {
		//this.editorial = ed; PARA GUARDAR EL OBJETO Y ACTUALIZAR
		lblCodEditorial.setText(""+ed.getCodEditorial());
		txtNombre.setText(ed.getNombre());
		txtAño.setText(""+ed.getAño());
	}

}
