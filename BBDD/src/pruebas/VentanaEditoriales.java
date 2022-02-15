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

import conexion.FuncionesBD;
import controlador.Controlador;
import modelo.Editorial;
import net.miginfocom.swing.MigLayout;

public class VentanaEditoriales extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaListado;
	private JScrollPane scrollPane;
	private JPanel buttonPane;
	private JButton btnSalir;
	
	private Controlador controlador;
	private JButton btnActualizar;


	/**
	 * Create the dialog.
	 */
	public VentanaEditoriales() {
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setTitle("Lista de Editoriales");
		
		
		setBounds(100, 100, 637, 308);
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
				scrollPane.setViewportView(tablaListado);
				tablaListado.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo Editorial", "Nombre", "A\u00F1o"
					}
				));
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
					btnActualizar = new JButton("Actualizar");
					btnActualizar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							mostrarVentanaActualizar();
							
						}

					});
					btnActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
					buttonPane.add(btnActualizar);
				}
				btnSalir.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnSalir.setActionCommand("OK");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
		
		
	}
	
	protected void mostrarVentanaActualizar() {
		
		DefaultTableModel modelo = (DefaultTableModel) tablaListado.getModel();
		int fila = tablaListado.getSelectedRow();
		
		int codEditorial = (int) modelo.getValueAt(fila, 0);
		
		controlador.mostrarActualizarEditorial(codEditorial);
		
		
	}

	public void setListaEditoriales(ArrayList<Editorial> listaEditoriales) {
		DefaultTableModel modelo = (DefaultTableModel) tablaListado.getModel();
		modelo.setRowCount(0);
		for (Editorial ed : listaEditoriales) {
			Object fila [] = {
					ed.getCodEditorial(), ed.getNombre(), ed.getAño()
			};
			modelo.addRow(fila);	
		}
	}


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
