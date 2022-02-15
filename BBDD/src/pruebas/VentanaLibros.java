package pruebas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.FuncionesBD;
import controlador.Controlador;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;

public class VentanaLibros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaListado;
	private JButton btnSalir;
	private JPanel buttonPane;
	private JScrollPane scrollPane;
	
	private Controlador controlador;
	private JButton btnBorrar;

	/**
	 * Create the dialog.
	 */
	public VentanaLibros() {
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setTitle("Lista de Libros");
		
		
		setBounds(100, 100, 867, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			{
				tablaListado = new JTable();
				tablaListado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				tablaListado.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ISBN", "T\u00EDtulo", "CodEditorial", "A\u00F1o", "N\u00BA Pags", "Precio", "Cantidad", "PrecioCD"
					}
				));
				scrollPane.setViewportView(tablaListado);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						
					}
				});
				{
					btnBorrar = new JButton("Borrar");
					btnBorrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							llamarBorrarLibros();
							
						}
					});
					btnBorrar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
					buttonPane.add(btnBorrar);
				}
				btnSalir.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
	}
	

	protected void llamarBorrarLibros() {
		
		DefaultTableModel modelo = (DefaultTableModel) tablaListado.getModel();
		int fila = tablaListado.getSelectedRow();
		
		String isbn = modelo.getValueAt(fila, 0).toString();
		int resultado = controlador.borrarLibro(isbn);
		
		if (resultado != 0) {
			modelo.removeRow(fila);
		} else {
			JOptionPane.showMessageDialog(null, "Error al borrar el libro " + isbn);
		}
		
	}


	public void setListaLibros (ArrayList<Libro> listaLibros) {
		DefaultTableModel modelo = (DefaultTableModel) tablaListado.getModel();
		modelo.setRowCount(0);
		for(Libro lib : listaLibros) {
			Object fila [] = {
					lib.getIsbn(), lib.getTitulo(), lib.getCodEditorial(), lib.getAño(), lib.getNumPags(), lib.getPrecio(),
					lib.getCantidad(), lib.getPrecioCD()
			};
			modelo.addRow(fila);	
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
