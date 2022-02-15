package pruebas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnEditoriales;
	private JButton btnLibros;
	private Controlador controlador;
	private JButton btnAutores;
	private JButton btnInsertar;
	private JButton btnAñadirEditorial;
	private JButton btnInsertarlibro;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][118.00][][][][211.00]", "[][][][][][59.00][][53.00,grow]"));
		
		JLabel lblTitulo = new JLabel("Ventana Principal");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		contentPane.add(lblTitulo, "cell 0 0 9 4,grow");
		
		btnEditoriales = new JButton("Mostrar Editoriales");
		btnEditoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarEditoriales();
				
			}
		});
		
		btnAutores = new JButton("Mostrar Autores");
		btnAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarAutores();
				
			}
		});
		btnAutores.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnAutores, "cell 4 4");
		
		btnInsertar = new JButton("Insertar Autor");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarVentanaAutor();
				
			}
		});
		btnInsertar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnInsertar, "cell 8 4,alignx center");
		btnEditoriales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnEditoriales, "cell 4 5,alignx center");
		
		btnLibros = new JButton("Mostrar Libros");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarLibros();
				
			}
		});
		btnLibros.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnLibros, "cell 8 5,alignx center");
		
		btnAñadirEditorial = new JButton("A\u00F1adir Editorial");
		btnAñadirEditorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarVentanaEditorial();
				
			}
		});
		btnAñadirEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnAñadirEditorial, "cell 4 7,alignx center,aligny center");
		
		btnInsertarlibro = new JButton("Insertar Libro");
		btnInsertarlibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.mostrarVentanaInsertarLibro();
				
			}
		});
		btnInsertarlibro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(btnInsertarlibro, "cell 8 7,alignx center,aligny center");
	}


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
