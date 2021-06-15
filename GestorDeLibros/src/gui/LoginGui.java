package gui;

import java.awt.EventQueue;

import com.Usuario;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginGui {

	private JFrame frame;
	private JTextField textFieldUser;
	private JTextField textPassword;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 267, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 96, 212, 14);
		frame.getContentPane().add(lblError);
		lblError.setVisible(true);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario userDefault = new Usuario();
				String usuarioText = textFieldUser.getText();
				String passwordText = textPassword.getText();

				if (usuarioText.length() == 0 || passwordText.length() == 0) {
					lblError.setText("Se deben completar ambos campos");
					textFieldUser.setText("");
					textPassword.setText("");
					lblError.setVisible(true);
				} else {
					if (userDefault.validarUsuario(usuarioText, passwordText)) {
						MenuPrincipalGui menuGui = new MenuPrincipalGui();
						menuGui.getFrame().setVisible(true);
						frame.dispose();
					}else{
						lblError.setText("Usuario o contraseña incorrecto");
						textFieldUser.setText("");
						textPassword.setText("");
						lblError.setVisible(true);
					}
				}

			}
		});
		btnAceptar.setBounds(24, 121, 89, 23);
		frame.getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnCancelar.setBounds(133, 121, 89, 23);
		frame.getContentPane().add(btnCancelar);

		textFieldUser = new JTextField();
		textFieldUser.setText("admin");
		textFieldUser.setBounds(94, 22, 128, 20);
		frame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		textPassword = new JTextField();
		textPassword.setText("pass123");
		textPassword.setBounds(94, 60, 128, 20);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 25, 74, 14);
		frame.getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 63, 74, 14);
		frame.getContentPane().add(lblPassword);

	}
}
