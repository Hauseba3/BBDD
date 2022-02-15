package pruebas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Autor;
import net.miginfocom.swing.MigLayout;

public class VentanaAutores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaListado;
	private JScrollPane scrollPane;
	private JPanel buttonPane;
	
	private Controlador controlador;

	/**
	 * Create the dialog.
	 */
	public VentanaAutores() {
		
		
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setTitle("Lista de Autores");
		setBounds(100, 100, 655, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			{
				tablaListado = new JTable();
				tablaListado.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID Autor", "Nombre", "Apellidos"
					}
				));
				tablaListado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				scrollPane.setViewportView(tablaListado);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						
					}
				});
				btnSalir.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
	
	public void setListaAutores(ArrayList<Autor> listaAutores) {
		DefaultTableModel modelo = (DefaultTableModel) tablaListado.getModel();
		modelo.setRowCount(0);
		for (Autor autor : listaAutores) {
			Object fila [] = {autor.getIdautor(), autor.getNombre(), autor.getApellidos()};
			modelo.addRow(fila);
		}
	}
	
	public void setControlador(Controlador controlador) {
			this.controlador = controlador;
	}

}

