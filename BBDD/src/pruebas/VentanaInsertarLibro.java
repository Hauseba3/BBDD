package pruebas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Autor;
import modelo.Editorial;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;

public class VentanaInsertarLibro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private Controlador controlador;
	private ArrayList<Editorial> listaEditoriales;
	private JButton btnInsertar;
	private JButton btnCancelar;
	private JList lista2;
	private JButton btnSeleccionar;
	private JList lista1;
	private JSpinner spCd;
	private JSpinner spCantidad;
	private JSpinner spPrecio;
	private JSpinner spPags;
	private JSpinner spAño;
	private JComboBox cbbEditorial;
	private ArrayList<Autor> listaAutores;
	private DefaultListModel modeloDisponibles;
	private DefaultListModel modeloSeleccionado;
	private JButton btnAtras;

	/**
	 * Create the dialog.
	 */
	public VentanaInsertarLibro() {
		setBounds(100, 100, 524, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][30.00,grow][39.00][13.00][116.00][78.00,grow][58.00]", "[][][][][][][46.00][][8.00][127.00,grow]"));
		{
			JLabel lblCabecera = new JLabel("A\u00F1adir nuevo Libro");
			lblCabecera.setFont(new Font("Times New Roman", Font.BOLD, 24));
			contentPanel.add(lblCabecera, "cell 1 0 6 1");
		}
		{
			JLabel lblisbn = new JLabel("ISBN:");
			lblisbn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblisbn, "cell 0 1,alignx trailing");
		}
		{
			txtIsbn = new JTextField();
			txtIsbn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtIsbn, "cell 1 1 5 1,growx");
			txtIsbn.setColumns(10);
		}
		{
			JLabel lblTitulo = new JLabel("T\u00EDtulo:");
			lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblTitulo, "cell 0 2,alignx trailing");
		}
		{
			txtTitulo = new JTextField();
			txtTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(txtTitulo, "cell 1 2 5 1,growx");
			txtTitulo.setColumns(10);
		}
		{
			JLabel lblEditorial = new JLabel("Editorial:");
			lblEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblEditorial, "cell 0 3,alignx trailing");
		}
		{
			cbbEditorial = new JComboBox();
			cbbEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(cbbEditorial, "cell 1 3 5 1,growx");
		}
		{
			JLabel lblAño = new JLabel("A\u00F1o:");
			lblAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblAño, "cell 0 4,alignx right");
		}
		{
			spAño = new JSpinner();
			spAño.setModel(new SpinnerNumberModel(1800, 1800, 2050, 1));
			spAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(spAño, "cell 1 4");
		}
		{
			JLabel lblPags = new JLabel("NumPags:");
			lblPags.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblPags, "cell 3 4");
		}
		{
			spPags = new JSpinner();
			spPags.setModel(new SpinnerNumberModel(200, 0, 999, 1));
			spPags.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(spPags, "cell 4 4");
		}
		{
			JLabel lblPrecio = new JLabel("Precio:");
			lblPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblPrecio, "cell 0 5,alignx right");
		}
		{
			spPrecio = new JSpinner();
			spPrecio.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			spPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(spPrecio, "cell 1 5");
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblCantidad, "cell 3 5,alignx right");
		}
		{
			spCantidad = new JSpinner();
			spCantidad.setModel(new SpinnerNumberModel(1, 0, 99, 1));
			spCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(spCantidad, "cell 4 5");
		}
		{
			JLabel lblCd = new JLabel("PrecioCD:");
			lblCd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblCd, "cell 5 5,alignx right");
		}
		{
			spCd = new JSpinner();
			spCd.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			spCd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(spCd, "cell 6 5");
		}
		{
			JLabel lblAutores = new JLabel("Autores Disponibles:");
			lblAutores.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblAutores, "cell 0 6 3 1");
		}
		{
			JLabel lblAutores2 = new JLabel("Autores Seleccionados:");
			lblAutores2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblAutores2, "cell 5 6 2 1");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 7 3 3,grow");
			{
				lista1 = new JList();
				lista1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				scrollPane.setViewportView(lista1);
			}
		}
		{
			btnSeleccionar = new JButton(">");
			btnSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					añadir();
					
				}
			});
			{
				btnAtras = new JButton("<");
				btnAtras.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						desmarcar();
						
					}
				});
				btnAtras.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				contentPanel.add(btnAtras, "cell 3 8 2 1,alignx center,aligny center");
			}
			btnSeleccionar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(btnSeleccionar, "cell 3 9 2 1,alignx center,aligny center");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 5 7 2 3,grow");
			{
				lista2 = new JList();
				lista2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				scrollPane.setViewportView(lista2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnInsertar = new JButton("Insertar");
				btnInsertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						recogerDatos();
						
					}
				});
				btnInsertar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				btnInsertar.setActionCommand("OK");
				buttonPane.add(btnInsertar);
				getRootPane().setDefaultButton(btnInsertar);
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
		
		String isbn = txtIsbn.getText();
		String titulo = txtTitulo.getText();
		Editorial e = (Editorial) cbbEditorial.getSelectedItem();
		int año = (int) spAño.getValue();
		int pags = (int) spPags.getValue();
		double precio = Double.parseDouble(spPrecio.getValue().toString());
		int cantidad = (int) spCantidad.getValue();
		double precioCd = Double.parseDouble(spCd.getValue().toString());
		
		Libro l = new Libro(isbn, titulo, e.getCodEditorial(), año, pags, precio, cantidad, precioCd);
		
		ArrayList<Autor> listaAutoresSeleccionados = new ArrayList<Autor>();
		for(int i =0 ; i<modeloSeleccionado.size(); i++) {
			listaAutoresSeleccionados.add((Autor) modeloSeleccionado.get(i));
		}
		
		controlador.insertarLibro(l, listaAutoresSeleccionados);
		
	}

	protected void desmarcar() {
		
		Autor autor = (Autor) lista2.getSelectedValue();
		modeloSeleccionado.removeElement(autor);
		modeloDisponibles.addElement(autor);
		
	}

	protected void añadir() {
		
		Autor autor = (Autor) lista1.getSelectedValue();
		modeloDisponibles.removeElement(autor);
		modeloSeleccionado.addElement(autor);
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		
	}

	public void setEditoriales(ArrayList<Editorial> listaEditoriales) {
		
		this.listaEditoriales = listaEditoriales;
		for (Editorial editorial : listaEditoriales) {
			cbbEditorial.addItem(editorial);
		}
		
	}

	public void setAutores(ArrayList<Autor> listaAutores) {
		
		this.listaAutores = listaAutores;
		modeloDisponibles = new DefaultListModel();
		modeloDisponibles.addAll(listaAutores);
		lista1.setModel(modeloDisponibles);
		
		modeloSeleccionado = new DefaultListModel();
		lista2.setModel(modeloSeleccionado);
	}

}
