package gui;

import java.awt.EventQueue;

import com.Usuario;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
//Se genera una interfaz para el login de la aplicacion

public class LoginGui {
	// Atributo que hace referencia al frame de login
	private JFrame frame;
	// Atributo que hace referencia al campo donde el usuario ingresara el nombre de
	// usuario
	private JTextField textFieldUser;
	// Atributo que hace referencia al campo donde el usuario ingresara el el
	// password correspondiente
	private JPasswordField passwordField;
	// Atributo donde se informaran los posibles errores a mostrar al usuario
	private JLabel lblError;
	// Clase main de toda la aplicacion de ABM, es el punt ode inicio de la
	// aplcaicion
	private static String rutaLog = "logSeguimiento.log";
	private static PrintStream logSalida = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logSalida = new PrintStream(rutaLog);
					LoginGui window = new LoginGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGui() {
		// Se procede inicializar los diversos componentes
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// Se define las caracteristicas de los diferentes componentes
	private void initialize() {
		// Personalizacion de componente frame del login de la aplicacion
		// Tamaño
		// Operacion de cierre
		// Se añade layout
		// Posicion en la pantalla
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Gestor");
		frame.setBounds(100, 100, 267, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		// Personalizacion del componente donde se indicaran los errores al usuario
		// Tamaño
		// Operacion de cierre
		// Se añade layout
		// Posicion en la pantalla
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(12, 113, 212, 14);
		frame.getContentPane().add(lblError);
		lblError.setVisible(true);
		// Personalizacion del componente, boton aceptar, con el que el usuario
		// intentara realizar el ingreso.

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			// Se define un evento para el boton aceptar
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton aceptar de login");
				// Se crear un objeto usuario para comparar los datos ingresados por el usuario
				// y verificar si el login es correcto
				Usuario userDefault = new Usuario();
				// Se obtienen los datos ingresados por el usuario
				String usuarioText = textFieldUser.getText();
				String passwordText = new String(passwordField.getPassword());
				// Se procede a realizar la validacion de los campos de usuario y password
				if (usuarioText.length() == 0 || passwordText.length() == 0) {
					lblError.setText("Se deben completar ambos campos");
					textFieldUser.setText("");
					passwordField.setText("");
					lblError.setVisible(true);
				} else {
					// LOGIN EXITOSO
					// En caso de que los campos esten completados se procede a verificar si el
					// intento de login es correcto.
					// En caso de ser correcto se crear el menu principal y se cierra la pantalla de
					// login.
					if (userDefault.validarUsuario(usuarioText, passwordText)) {
						MenuPrincipalGui menuGui = new MenuPrincipalGui();
						menuGui.getFrame().setVisible(true);
						frame.dispose();
					} else {
						// LOGIN FALLIDO
						// Se procede a mostar un mensaje al usuario indicado el fallo al realizar el
						// login.
						lblError.setText("Usuario o contrase\u00f1a incorrecto");
						textFieldUser.setText("");
						passwordField.setText("");
						lblError.setVisible(true);
					}
				}

			}
		});
		btnAceptar.setToolTipText("Ingresar");
		btnAceptar.setBounds(24, 135, 89, 23);
		frame.getContentPane().add(btnAceptar);
		// Personalizacion del componente, boton cancelar.
		JButton btnCancelar = new JButton("Cancelar");
		// Se genera una escucha de eventos para el boton
		btnCancelar.addActionListener(new ActionListener() {
			// Se procede a cerrar la aplicacion si se oprime el boton cancelar.
			public void actionPerformed(ActionEvent e) {
				logSalida.println(java.time.LocalTime.now() + "->" + "Se oprimio el boton cancelar de login");
				System.exit(1);
			}
		});
		btnCancelar.setToolTipText("Salir del programa");
		btnCancelar.setBounds(133, 135, 89, 23);
		frame.getContentPane().add(btnCancelar);
		// Personalizacion del componente, de input de nombre de usuario
		// Tamaño
		// Posicion en la pantalla
		// Texto default
		textFieldUser = new JTextField();
		textFieldUser.setText("admin");
		textFieldUser.setBounds(94, 22, 128, 20);
		frame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);
		// Personalizacion del componente, de password
		// Tamaño
		// Posicion en la pantalla
		// Texto default
		passwordField = new JPasswordField();
		passwordField.setText("pass123");
		passwordField.setBounds(94, 60, 128, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(0);

		JCheckBox showPassCheckBox = new JCheckBox("Mostrar Password");
		showPassCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se oprimio el checkbox en Login");
				if (showPassCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPassCheckBox.setToolTipText("Tildar = mostrar");
		showPassCheckBox.setBounds(94, 85, 131, 23);
		frame.getContentPane().add(showPassCheckBox);
		// Se añade un componente de texto, Usuario
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 25, 74, 14);
		frame.getContentPane().add(lblUsuario);
		// Se añade un componente de texto, Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 63, 74, 14);
		frame.getContentPane().add(lblPassword);

	}
}
