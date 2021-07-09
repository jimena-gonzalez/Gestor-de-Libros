package gui;

import javax.swing.JFrame;

import com.Libro;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

//Se crea la clase para la definicion del menu del ABM de biblioteca
public class MenuPrincipalGui {
	// Se define el frame principal del menu
	private JFrame frmMenuPrincipal;
	// Se define el PATH del achivo para almacenar los datos generados por la
	// aplicacion
	private static String ruta = "libros.tsv";
	// Se define un componente para indicar un mensaje, "SIN REGISTRO"
	private JLabel lblSinRegistro;
	// Se define un objeto donde se almacenaran los libros previamentes almacenados
	// en la aplicacion.
	private Vector<Libro> vector = new Vector<Libro>();
	private TableModel tableModel;
	private static String rutaLog = "logSeguimiento.log";
	private static PrintStream logSalida = null;

	// Constructor de la clase
	public MenuPrincipalGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// Se inicializan los diferentes componentes y se definen las acciones de los
	// botones
	private void initialize() {
		try {
			logSalida = new PrintStream(rutaLog);
		} catch (FileNotFoundException e1) {
			logSalida.println(java.time.LocalTime.now() + "->" + "Error al crear el archivo");
		}
		// Personalizacion de componente frame del menu de la aplicacion
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setResizable(false);
		frmMenuPrincipal.setTitle("Menu");
		frmMenuPrincipal.setBounds(100, 100, 244, 390);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		frmMenuPrincipal.setLocationRelativeTo(null);
		// Se realiza la llamada al metodo para la lectura de los registros provenientes
		// del archivo de almacenaje
		leerRegistrosPrevios(vector);
		// Se crea, personaliza el boton de opcion de alta de libro
		JButton btnAlta = new JButton("Alta");
		// Se añade un evento al boton de alta
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de alta del menu");
				// En caso de seleccionar el boton de alta se crea la ventana de ABM
				AbmGui abm = new AbmGui(1, vector, frmMenuPrincipal);
				// Se ingresa el titulo de la pantalla generada, para indicar que operacion se
				// realizara
				abm.getFrame().setTitle("ALTA");
				// Se procede a mostrar la pantalla de ABM para el alta de libro
				abm.getFrame().setVisible(true);
				// Se deja de mostrar la pantalla de menu de ABM
				frmMenuPrincipal.setVisible(false);
			}
		});
		btnAlta.setToolTipText("Agregar nuevo libro.");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		btnAlta.setBounds(44, 24, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnAlta);
		// Se crea el boton para la opcion de consulta del ABM
		JButton btnConsulta = new JButton("Consulta");
		// Se añade un evento al boton de consulta
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de consulta del menu");
				// Se llama al metodo para verificar la existencia registros previos
				if (verificarRegistros(vector)) {
					// Si existen registros previos se procede a crear la pantalla de consulta
					AbmGui abm = new AbmGui(2, vector, frmMenuPrincipal);
					abm.getFrame().setTitle("CONSULTA");
					// Se muestra la pantalla de consulta de la aplicacion
					abm.getFrame().setVisible(true);
					// Se deja de mostrar la pantalla de menu de la aplicacion
					frmMenuPrincipal.setVisible(false);
				}
				// En caso de no existir registros previos el boton no realizara accion
				return;
			}
		});
		btnConsulta.setToolTipText("Consultar datos de un libro registrado, dado su ISBN.");
		btnConsulta.setBounds(44, 58, 145, 23);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(btnConsulta);

		JButton btnActualizacion = new JButton("Actualizacion");
		// Se añade un evento al boton de actualizacion del menu
		btnActualizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de actualizacion del menu");
				// Se llama al metodo para verificar la existencia registros previos
				if (verificarRegistros(vector)) {
					// Si existen registros previos se procede a crear la pantalla de actualizacion
					AbmGui abm = new AbmGui(3, vector, frmMenuPrincipal);
					// Se setea el titulo de la pantalla creada
					abm.getFrame().setTitle("ACTUALIZACION");
					// Se procede a mostrar la pantalla de actualizacion
					abm.getFrame().setVisible(true);
					// Se deja de mostrar la pantalla de menu
					frmMenuPrincipal.setVisible(false);
				}
				return;
			}
		});
		btnActualizacion.setToolTipText("Modificar datos de un libro registrado.");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		btnActualizacion.setBounds(44, 92, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnActualizacion);

		JButton btnBaja = new JButton("Baja");
		// Se añade un evento al boton de baja
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de baja del menu");
				// Si existen registros previos se procede a crear la pantalla de actualizacion
				if (verificarRegistros(vector)) {
					// Si existen registros previos se procede a crear la pantalla de baja
					AbmGui abm = new AbmGui(4, vector, frmMenuPrincipal);
					// Se setea el titulo de la pantalla creada
					abm.getFrame().setTitle("BAJA");
					// Se procede a mostrar la pantalla de baja
					abm.getFrame().setVisible(true);
					// Se deja de mostrar la pantalla de menu
					frmMenuPrincipal.setVisible(false);
				}
				return;
			}
		});
		btnBaja.setToolTipText("Eliminar nuevo libro, dado su ISBN.");
		btnBaja.setBounds(44, 126, 145, 23);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(btnBaja);
		// Se crea el boton de ordenar registros
		JButton btnOrdenarRegistros = new JButton("Ordenar registros");
		btnOrdenarRegistros.addActionListener(new ActionListener() {
			// Se añade un evento al boton de ordenar registros
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de ordenar registros del menu");
				// Se verifica la exitencia de registros previos
				if (verificarRegistros(vector)) {
					// Se procede a ordenar los datos almacenados en la coleccion
					vector.sort(null);
					// Se realiza el llamado a la pantalla que mostrar los datos
					ListadoGuiOrdenado formulario = new ListadoGuiOrdenado(vector, frmMenuPrincipal);
				}
				// En caso de no existir registros previos no se realiza accion
				return;
			}
		});
		btnOrdenarRegistros.setToolTipText("Ordenar registros de forma ascendente");
		btnOrdenarRegistros.setBounds(44, 160, 145, 23);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(btnOrdenarRegistros);
		// Se crea el boton de listar registros
		JButton btnListarRegistros = new JButton("Listar registros");
		// Se añade un evento al boton de listar registros
		btnListarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de listar registros del menu");
				// Se verifica la exitencia de registros previos
				if (verificarRegistros(vector)) {
					// Se crea la pantalla de listado
					ListadoGui listado = new ListadoGui(vector, frmMenuPrincipal);
					// Se muestra la pantalla creada
					listado.getFrame().setVisible(true); // Se deja de mostrar el menu principal
					// Se deja de mostrar el menu principal
					frmMenuPrincipal.setVisible(false);
				}
				// En caso de no existir registros previos no se realiza accion
				return;
			}

		});
		btnListarRegistros.setBounds(44, 194, 145, 23);
		btnListarRegistros.setToolTipText("Mostrar libros registrados");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(btnListarRegistros);
		// Se crea el boton de salir
		JButton btnSalir = new JButton("Salir");
		// Se añade un evento al boton de salir del menu de la aplicacion
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton de listar del menu");
				// Termina la ejecucion de la aplicacion
				System.exit(0);
			}
		});
		btnSalir.setBounds(44, 274, 145, 23);
		btnSalir.setToolTipText("Salir del programa");
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(btnSalir);

		lblSinRegistro = new JLabel("No hay registros");
		lblSinRegistro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSinRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinRegistro.setForeground(Color.RED);
		lblSinRegistro.setVisible(false);
		lblSinRegistro.setBounds(24, 229, 179, 23);
		// Se añade el componente al contenedor de la pantalla de la aplicacion
		frmMenuPrincipal.getContentPane().add(lblSinRegistro);
	}
	// Funcion encargada de leer los registros previamente almacenados en el archivo
	// definido en el PATH

	private void leerRegistrosPrevios(Vector<Libro> vector) {
		String[] campos;
		Libro libro = null;
		try {
			// Se crea un scanner para la lectura de datos del archivo de almacenaje
			Scanner entrada = new Scanner(new FileReader(ruta));
			// Mientras exista contenido añadira un nuevo registro
			while (entrada.hasNextLine()) {
				campos = entrada.nextLine().split("\t");
				// Ingresa un nuevo dato del archivo
				libro = new Libro();
				libro.setISBN(Long.parseLong(campos[0]));
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
				vector.add(libro);
			}
			// Cierra el stream de datos
			entrada.close();

		} catch (FileNotFoundException e) {
		}
	}

	// Metodo encargado de verificar que se pudo cargar datos del archivo de
	// almacenaje
	private boolean verificarRegistros(Vector<Libro> vector) {
		// Si no se encontraron datos muestra el mensaje al usuario en el menu principal
		if (vector.isEmpty()) {
			this.lblSinRegistro.setVisible(true);
			this.logSalida.println(java.time.LocalTime.now() + "->" +"No se encotraron registros previos");
			return false;
		}
		this.lblSinRegistro.setVisible(false);
		return true;
	}

	public JFrame getFrame() {
		return this.frmMenuPrincipal;
	}
}
