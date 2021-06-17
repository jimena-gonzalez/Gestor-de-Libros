package gui;

import com.Libro;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.awt.Font;

public class AbmGui {

	private JFrame frame;
	private int opcion;
	private Vector<Libro> libros;
	private JTextField textFieldISBN;
	private JTextField textFieldTitulo;
	private JTextField textFieldAutor;
	private JTextField textFieldEditorial;
	private JTextField textFieldEdicion;
	private JTextField textFieldAnioPublicacion;
	private JLabel lblResultado;
	private int[] contador = { 0 };
	private JButton btnBuscar;
	private Libro libroAux = new Libro();
	private static String ruta = "libros.tsv";

	private JFrame menu;

	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int annoPublicacion;

	public AbmGui(int opcion, Vector<Libro> libros, JFrame frmMenuPrincipal) {
		this.opcion = opcion;
		this.libros = libros;
		this.menu = frmMenuPrincipal;
		initialize();
	}

	private void initialize() {

		Funcion<Libro> imprimir = new Funcion<Libro>() {
			@Override
			public void funcion(Libro libro, Object parametros) {
				int[] contador = (int[]) parametros;
				contador[0]++;
			}
		};

		Funcion<Libro> imprimirEnArchivo = new Funcion<Libro>() {
			@Override
			public void funcion(Libro libro, Object parametros) {
				PrintStream archivo = (PrintStream) parametros;
				archivo.print(libro.getISBN() + "\t");
				archivo.print(libro.getTitulo() + "\t");
				archivo.print(libro.getAutor() + "\t");
				archivo.print(libro.getEditorial() + "\t");
				archivo.print(libro.getEdicion() + "\t");
				archivo.print(libro.getAnno_de_publicacion() + "\n");
			}
		};

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		// LABEL
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 28, 99, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(10, 53, 99, 14);
		frame.getContentPane().add(lblTitulo);

		JLabel lblNewLabel_1_1 = new JLabel("Autor");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(10, 78, 99, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Editorial");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(10, 103, 99, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblEdicin = new JLabel("Edici\u00F3n");
		lblEdicin.setHorizontalAlignment(SwingConstants.LEFT);
		lblEdicin.setBounds(10, 128, 99, 14);
		frame.getContentPane().add(lblEdicin);

		JLabel lblNewLabel_1_2 = new JLabel("A\u00F1o de publicaci\u00F3n");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setBounds(10, 153, 128, 14);
		frame.getContentPane().add(lblNewLabel_1_2);

		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(148, 179, 276, 14);
		frame.getContentPane().add(lblResultado);

		// TEXTFIELD
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEnabled(false);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(148, 51, 276, 17);
		frame.getContentPane().add(textFieldTitulo);

		textFieldAutor = new JTextField();
		textFieldAutor.setEnabled(false);
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(148, 76, 276, 17);
		frame.getContentPane().add(textFieldAutor);

		textFieldEditorial = new JTextField();
		textFieldEditorial.setEnabled(false);
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBounds(148, 101, 276, 17);
		frame.getContentPane().add(textFieldEditorial);

		textFieldEdicion = new JTextField();
		textFieldEdicion.setEnabled(false);
		textFieldEdicion.setColumns(10);
		textFieldEdicion.setBounds(148, 126, 276, 17);
		frame.getContentPane().add(textFieldEdicion);

		textFieldAnioPublicacion = new JTextField();
		textFieldAnioPublicacion.setEnabled(false);
		textFieldAnioPublicacion.setColumns(10);
		textFieldAnioPublicacion.setBounds(148, 151, 276, 17);
		frame.getContentPane().add(textFieldAnioPublicacion);

		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(148, 26, 115, 17);
		frame.getContentPane().add(textFieldISBN);
		textFieldISBN.setColumns(10);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (opcion == 2) {
					menu.setVisible(true);
					frame.dispose();
					return;
				}

				if (!verificarCampos())
					return;

				if (opcion == 1)
					alta();
				if (opcion == 3)
					actualizar();
				if (opcion == 4)
					darBaja();
				if (opcion == 5)
					ordenar();
				if (opcion == 6)
					listar();

				grabarInformacion(imprimirEnArchivo);
				menu.setVisible(true);
				frame.dispose();
			}

		});
		btnNewButton.setBounds(148, 204, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(250, 204, 89, 23);
		frame.getContentPane().add(btnCancelar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Libro dato = new Libro();
				dato.setISBN(textFieldISBN.getText());
				int i = libros.indexOf(dato);
				libroAux = i < 0 ? null : libros.get(i);

				if (opcion == 1) {
					if (libroAux != null) {
						lblResultado.setText("El registro ya existe.");
						return;
					}
					libroAux = new Libro();
					libroAux.setISBN(dato.getISBN());
					textFieldISBN.setEnabled(false);
				}

				if (opcion == 1 || opcion > 2 && opcion <= 4) {
					activarCampos();
				}

				if (opcion >= 2 && opcion <= 4 && libroAux == null) {
					lblResultado.setText("Registro no encontrado");
					return;
				}

				if (opcion == 2)
					consultar();
				if (opcion == 3 || opcion == 4)
					mostrar();
				if (opcion == 4) {
					mostrar();
					bloquearInputs();
				}

			}

		});
		btnBuscar.setBounds(279, 26, 89, 18);
		frame.getContentPane().add(btnBuscar);

	}

	public JFrame getFrame() {
		return this.frame;
	}

	private boolean verificarCampos() {

		titulo = textFieldTitulo.getText();
		autor = textFieldAutor.getText();
		editorial = textFieldEditorial.getText();

		if (titulo.length() == 0 || editorial.length() == 0 || autor.length() == 0 || titulo.length() == 0
				|| textFieldAnioPublicacion.getText().length() == 0 || textFieldEditorial.getText().length() == 0) {
			lblResultado.setText("Debe completar todos los campos");
			return false;
		}

		if (!esNumerico()) {
			return false;
		}
		edicion = Integer.parseInt(textFieldEdicion.getText());
		annoPublicacion = Integer.parseInt(textFieldAnioPublicacion.getText());

		return true;
	}

	private void alta() {
		insertarDatos();
		libros.add(libroAux);
	}

	private void consultar() {
		this.textFieldTitulo.setText(libroAux.getTitulo());
		this.textFieldAutor.setText(libroAux.getAutor());
		this.textFieldEditorial.setText(libroAux.getEditorial());
		this.textFieldEdicion.setText(Integer.toString(libroAux.getEdicion()));
		this.textFieldAnioPublicacion.setText(Integer.toString(libroAux.getAnno_de_publicacion()));
	}

	private void mostrar() {
		this.textFieldISBN.setEnabled(false);
		consultar();
	}

	private void actualizar() {
		insertarDatos();
	}

	private void listar() {
	}

	private void ordenar() {

	}

	private void darBaja() {
		libros.remove(libroAux);
	}

	private void bloquearInputs() {
		this.textFieldTitulo.setEnabled(false);
		this.textFieldAutor.setEnabled(false);
		this.textFieldEditorial.setEnabled(false);
		this.textFieldEdicion.setEnabled(false);
		this.textFieldAnioPublicacion.setEnabled(false);

	}

	private boolean esNumerico() {
		try {
			Integer.parseInt(this.textFieldEdicion.getText());
			Integer.parseInt(this.textFieldAnioPublicacion.getText());
		} catch (NumberFormatException e) {
			lblResultado.setText("Edici\u00F3n y A\u00F1o de publicacion deben ser num\u00e9ricos");
			lblResultado.setVisible(true);
			return false;
		}
		return true;
	}

	private void activarCampos() {
		this.textFieldTitulo.setEnabled(true);
		this.textFieldAutor.setEnabled(true);
		this.textFieldEditorial.setEnabled(true);
		this.textFieldEdicion.setEnabled(true);
		this.textFieldAnioPublicacion.setEnabled(true);
	}

	private void insertarDatos() {
		libroAux.setTitulo(titulo);
		libroAux.setAutor(autor);
		libroAux.setEditorial(editorial);
		libroAux.setAnno_de_publicacion(annoPublicacion);
	}

	private void grabarInformacion(Funcion<Libro> imprimirEnArchivo) {
		PrintStream salida = null;
		try {
			salida = new PrintStream(ruta);
			for (int i = 0; i < libros.size(); i++) {
				imprimirEnArchivo.funcion(libros.get(i), salida);
			}
			salida.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}

interface Funcion<T extends Comparable<T>> {
	void funcion(T dato, Object parametros);
}
