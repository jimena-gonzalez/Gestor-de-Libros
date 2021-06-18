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

//Clase donde se define la pantalla de ABM
public class AbmGui {
	// Frame de la pantalla de abm
	private JFrame frame;
	// Atributo que almacenara el tipo de operacion a realizar
	private int opcion;
	// Coleccion de libros previamente cargados
	private Vector<Libro> libros;
	// Se definen los campos de input para mostrar|ingresar los datos de los libros
	private JTextField textFieldISBN;
	private JTextField textFieldTitulo;
	private JTextField textFieldAutor;
	private JTextField textFieldEditorial;
	private JTextField textFieldEdicion;
	private JTextField textFieldAnioPublicacion;
	// Se define el componente para mostrar el resultado de la operacion
	private JLabel lblResultado;
	// Se define para la creacion del boton de busqueda
	private JButton btnBuscar;
	// Se crea un libro auxiliar donde se almacenar los datos para ingresar a la
	// coleccion de libros
	private Libro libroAux = new Libro();
	// Ruta del archivo de almacenaje
	private static String ruta = "libros.tsv";
	// Se usara para tener una referencia del menu de la aplicacion
	private JFrame menu;
	// Se definen un conjunto de variables el almacenje de los campos de input
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int annoPublicacion;
	private static String rutaLog = "logSeguimiento.log";
	private static PrintStream logSalida = null;

	// Constructor de la clase AbmGui
	public AbmGui(int opcion, Vector<Libro> libros, JFrame frmMenuPrincipal) {
		// Setea la opcion proveniente del menu
		this.opcion = opcion;
		// Setea la coleccion de libros
		this.libros = libros;
		// Referencia al menu principal
		this.menu = frmMenuPrincipal;

		try {
			this.logSalida = new PrintStream(rutaLog);
		} catch (FileNotFoundException e) {
			logSalida.println(java.time.LocalTime.now() + "->" + "Error al crear el archivo");
		}

		initialize();
	}

	// Funcion de creacion, personalizacion de los diferentes componentes de la
	// aplicacion
	private void initialize() {
		// Funcion encargarda de grabar en el archivos lo datos de la coleccion
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

		// FRAME DEL ABM
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		// -------------LABEL-------------
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 28, 99, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblNewLabel);

		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(10, 53, 99, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblTitulo);

		JLabel lblNewLabel_1_1 = new JLabel("Autor");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(10, 78, 99, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Editorial");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(10, 103, 99, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblEdicin = new JLabel("Edici\u00F3n");
		lblEdicin.setHorizontalAlignment(SwingConstants.LEFT);
		lblEdicin.setBounds(10, 128, 99, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblEdicin);

		JLabel lblNewLabel_1_2 = new JLabel("A\u00F1o de publicaci\u00F3n");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setBounds(10, 153, 128, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblNewLabel_1_2);

		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(148, 179, 276, 14);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(lblResultado);

		// -------------TEXTFIELD-------------
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEnabled(false);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(148, 51, 276, 17);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(textFieldTitulo);

		textFieldAutor = new JTextField();
		textFieldAutor.setEnabled(false);
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(148, 76, 276, 17);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(textFieldAutor);

		textFieldEditorial = new JTextField();
		textFieldEditorial.setEnabled(false);
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBounds(148, 101, 276, 17);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(textFieldEditorial);

		textFieldEdicion = new JTextField();
		textFieldEdicion.setEnabled(false);
		textFieldEdicion.setColumns(10);
		textFieldEdicion.setBounds(148, 126, 276, 17);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(textFieldEdicion);

		textFieldAnioPublicacion = new JTextField();
		textFieldAnioPublicacion.setEnabled(false);
		textFieldAnioPublicacion.setColumns(10);
		textFieldAnioPublicacion.setBounds(148, 151, 276, 17);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(textFieldAnioPublicacion);

		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(148, 26, 115, 17);
		frame.getContentPane().add(textFieldISBN);
		textFieldISBN.setColumns(10);

		// Se crea el boton de aceptar
		JButton btnNewButton = new JButton("Aceptar");
		// Se añade un evento
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se presiono el boton aceptar de pantalla ABM");
				// Dependiendo de la opcion elegida en el menu realizara diferentes opciones
				// opcion 2 -> CONSULTA
				if (opcion == 2) {
					// Se vuelve a mostrar el menu
					menu.setVisible(true);
					// Se cierra la pantalla actual
					frame.dispose();
					return;
				}
				// Se verifica si los datos ingresados a los campos de input son correctos
				// En caso de error se muestra el mismo al usuario
				// if (!verificarCampos())
				// return;
				// opcion 1 -> ALTA
				// Se procede a carga los datos para el alta del libro
				if (opcion == 1) {
					if (!alta())
						return;
				}

				// opcion 3 -> ACTUALIZAR
				// Se procede a cambiar si se decide los datos de un libro
				if (opcion == 3) {
					if (!actualizar())
						return;
				}
				// opcion 4 -> DAR DE BAJA
				// Se procede ingresar el libro a eliminar
				if (opcion == 4)
					darBaja();
				// Se procede a grabar la informacion de la coleccion de libros
				grabarInformacion(imprimirEnArchivo);
				// Se vuelve a mostrar el menu
				menu.setVisible(true);
				// Se finaliza la pantalla de ABM
				frame.dispose();
			}

		});
		btnNewButton.setBounds(148, 204, 89, 23);
		btnNewButton.setToolTipText("Confirmar acción");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(btnNewButton);

		// Se crea el boton CANCELAR
		JButton btnCancelar = new JButton("Cancelar");
		// Se añade un evento
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se presiono el boton cancelar de pantalla ABM");
				// Se muestra el menu
				menu.setVisible(true);
				// Se finaliza la pantalla de ABM
				frame.dispose();
			}
		});
		btnCancelar.setBounds(250, 204, 89, 23);
		btnCancelar.setToolTipText("Volver al menu sin aplicar la accion.");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(btnCancelar);
		// Se crea el boton BUSCAR
		btnBuscar = new JButton("Buscar");
		// Se añade un evento
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se presiono el boton buscar de pantalla ABM");
				// Se inicializa el label de resultado
				lblResultado.setText("");
				// Se crea un objeto libro donde se almacenara el libro buscado si se encuentra
				Libro dato = new Libro();
				// Se ingresa el ISBN para buscar el libro
				dato.setISBN(textFieldISBN.getText());
				// Se procede a buscar el libro en la coleccion
				int i = libros.indexOf(dato);
				// Si no se encuentra este null, o se devolvera el libro encotrado
				libroAux = i < 0 ? null : libros.get(i);
				// opcion del menu -> ALTA
				if (opcion == 1) {
					// Si ya existe el libro se informa y no realiza otra accion
					if (libroAux != null) {
						lblResultado.setText("El registro ya existe.");
						return;
					}
					// En caso de no encontrarse, significa que no existe en el archivos de datos
					libroAux = new Libro();
					// Se setea el ISBN del libro
					libroAux.setISBN(dato.getISBN());
					// Se bloquea el campo ISBN para no permitir el cambio de este
					textFieldISBN.setEnabled(false);
				}
				// Si la opcion es alta, actualizacion, baja se procede a activar los campos de
				// informacion de libro
				if (opcion == 1 || opcion > 2 && opcion <= 4) {
					activarCampos();
				}
				// Si la opcion es consulta, actualizacion, baja y no se encontro el registro se
				// procede a informar
				if (opcion >= 2 && opcion <= 4 && libroAux == null) {
					lblResultado.setText("Registro no encontrado");
					return;
				}
				// Si llego aca significa que existe
				if (opcion == 2)
					// Se eligio consultar y se procede a mostrar los datos del libro solicitado
					consultar();
				if (opcion == 3 || opcion == 4)
					// Se eligio actualizacion o baja y se procede a mostrar los datos del libro
					// solicitado
					mostrar();
				if (opcion == 4) {
					// Se eligio baja, se muestra pero no permite la modificacion
					mostrar();
					bloquearInputs();
				}

			}

		});
		btnBuscar.setBounds(279, 26, 89, 18);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frame.getContentPane().add(btnBuscar);

	}

	// Metodo que permite obtener desde otra clase el frame de la pantalla ABM
	public JFrame getFrame() {
		return this.frame;
	}

	// Verifica el correcto ingreso de datos en los campos de inputs
	private boolean verificarCampos() {
		// Se obtiene los valores de los inputs
		titulo = textFieldTitulo.getText();
		autor = textFieldAutor.getText();
		editorial = textFieldEditorial.getText();
		// Verifica que sean vacios, en caso de ser asi se procede a informar al usuario
		if (titulo.length() == 0 || editorial.length() == 0 || autor.length() == 0 || titulo.length() == 0
				|| textFieldAnioPublicacion.getText().length() == 0 || textFieldEditorial.getText().length() == 0) {
			lblResultado.setText("Debe completar todos los campos");
			return false;
		}
		// Verifica que los campos de edicion y año de publicacion sean numericos, en
		// caso de no ser asi se informa
		if (!esNumerico()) {
			return false;
		}
		// Se obtiene los valores de los inputs
		edicion = Integer.parseInt(textFieldEdicion.getText());
		annoPublicacion = Integer.parseInt(textFieldAnioPublicacion.getText());

		return true;
	}

	// Metodo que realiza el alta de un nuevo libro
	private boolean alta() {
		// Se obtiene los valores de los inputs
		titulo = textFieldTitulo.getText();
		autor = textFieldAutor.getText();
		editorial = textFieldEditorial.getText();
		// Verifica que sean vacios, en caso de ser asi se procede a informar al usuario
		if (titulo.length() == 0 || editorial.length() == 0 || autor.length() == 0 || titulo.length() == 0
				|| textFieldAnioPublicacion.getText().length() == 0 || textFieldEditorial.getText().length() == 0) {
			lblResultado.setText("Debe completar todos los campos");
			return false;
		}
		// Verifica que los campos de edicion y año de publicacion sean numericos, en
		// caso de no ser asi se informa
		if (!esNumerico()) {
			return false;
		}
		// Se obtiene los valores de los inputs
		edicion = Integer.parseInt(textFieldEdicion.getText());
		annoPublicacion = Integer.parseInt(textFieldAnioPublicacion.getText());

		insertarDatos();
		libros.add(libroAux);
		return true;

		// inserta los datos en un objeto libro
		// añade el libro a la coleccion de libros a grabar
	}

	// Funcion que permite actualizar los campos de un libro
	private boolean actualizar() {
		titulo = textFieldTitulo.getText();
		autor = textFieldAutor.getText();
		editorial = textFieldEditorial.getText();
		// Verifica que sean vacios, en caso de ser asi se procede a informar al usuario
		if (titulo.length() == 0 || editorial.length() == 0 || autor.length() == 0 || titulo.length() == 0
				|| textFieldAnioPublicacion.getText().length() == 0 || textFieldEditorial.getText().length() == 0) {
			lblResultado.setText("Debe completar todos los campos");
			return false;
		}
		// Verifica que los campos de edicion y año de publicacion sean numericos, en
		// caso de no ser asi se informa
		if (!esNumerico()) {
			return false;
		}
		// Se obtiene los valores de los inputs
		edicion = Integer.parseInt(textFieldEdicion.getText());
		annoPublicacion = Integer.parseInt(textFieldAnioPublicacion.getText());

		// inserta los datos en un objeto libro
		insertarDatos();
		return true;
	}

	// Metodo que completa los campos de input con los datos obtenidos del archivo
	private void consultar() {
		// Ingresa el dato en campo correspondiente
		this.textFieldTitulo.setText(libroAux.getTitulo());
		this.textFieldAutor.setText(libroAux.getAutor());
		this.textFieldEditorial.setText(libroAux.getEditorial());
		this.textFieldEdicion.setText(Integer.toString(libroAux.getEdicion()));
		this.textFieldAnioPublicacion.setText(Integer.toString(libroAux.getAnno_de_publicacion()));
	}

	// Muestra los datos
	private void mostrar() {
		this.textFieldISBN.setEnabled(false);
		consultar();
	}

	// Funcion que elimina un libro especifico de la coleccion a grabar
	private void darBaja() {
		libros.remove(libroAux);
	}

	// Funcion que bloquea los inputs para el usuario
	private void bloquearInputs() {
		this.textFieldTitulo.setEnabled(false);
		this.textFieldAutor.setEnabled(false);
		this.textFieldEditorial.setEnabled(false);
		this.textFieldEdicion.setEnabled(false);
		this.textFieldAnioPublicacion.setEnabled(false);

	}

	// Funcion que verifica que los campos de edicion y anio de publicacion sean
	// numericos
	private boolean esNumerico() {
		try {
			// Se intenta parsear los datos a un Integer
			Integer.parseInt(this.textFieldEdicion.getText());
			Integer.parseInt(this.textFieldAnioPublicacion.getText());
		} catch (NumberFormatException e) {
			// En caso de no poder parsear los datos se procede a informar al usario
			lblResultado.setText("Edici\u00F3n y A\u00F1o de publicacion deben ser num\u00e9ricos");
			lblResultado.setVisible(true);
			return false;
		}
		return true;
	}

	// Activa los inputs previamente bloqueados
	private void activarCampos() {
		this.textFieldTitulo.setEnabled(true);
		this.textFieldAutor.setEnabled(true);
		this.textFieldEditorial.setEnabled(true);
		this.textFieldEdicion.setEnabled(true);
		this.textFieldAnioPublicacion.setEnabled(true);
	}

	// Ingresa los datos a un objeto libroAux para la actualizion del archivo
	private void insertarDatos() {
		libroAux.setTitulo(titulo);
		libroAux.setAutor(autor);
		libroAux.setEditorial(editorial);
		libroAux.setEdicion(edicion);
		libroAux.setAnno_de_publicacion(annoPublicacion);
		libroAux.setEdicion(edicion);
	}

	// Funcion encargada de grabar los datos de la coleccion en un archivo
	private void grabarInformacion(Funcion<Libro> imprimirEnArchivo) {
		PrintStream salida = null;
		try {
			salida = new PrintStream(ruta);
			// Se encarga de recorrer la collecion y graba en el archivo
			for (int i = 0; i < libros.size(); i++) {
				imprimirEnArchivo.funcion(libros.get(i), salida);
			}
			// Cierra el stream del archivo de salida
			salida.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}

interface Funcion<T extends Comparable<T>> {
	void funcion(T dato, Object parametros);
}
