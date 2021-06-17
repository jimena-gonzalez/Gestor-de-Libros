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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class LoginGui {

	private JFrame frame;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 267, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(12, 113, 212, 14);
		frame.getContentPane().add(lblError);
		lblError.setVisible(true);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario userDefault = new Usuario();
				String usuarioText = textFieldUser.getText();
				String passwordText = new String(passwordField.getPassword());

				if (usuarioText.length() == 0 || passwordText.length() == 0) {
					lblError.setText("Se deben completar ambos campos");
					textFieldUser.setText("");
					passwordField.setText("");
					lblError.setVisible(true);
				} else {
					if (userDefault.validarUsuario(usuarioText, passwordText)) {
						MenuPrincipalGui menuGui = new MenuPrincipalGui();
						menuGui.getFrame().setVisible(true);
						frame.dispose();
					}else{
						lblError.setText("Usuario o contrase\u00f1a incorrecto");
						textFieldUser.setText("");
						passwordField.setText("");
						lblError.setVisible(true);
					}
				}

			}
		});
		btnAceptar.setBounds(24, 135, 89, 23);
		frame.getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnCancelar.setBounds(133, 135, 89, 23);
		frame.getContentPane().add(btnCancelar);

		textFieldUser = new JTextField();
		textFieldUser.setText("admin");
		textFieldUser.setBounds(94, 22, 128, 20);
		frame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setText("pass123");
		passwordField.setBounds(94, 60, 128, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(0);
		
		JCheckBox showPassCheckBox = new JCheckBox("Mostrar Password");
		showPassCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPassCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPassCheckBox.setToolTipText("Tildar = mostrar");
		showPassCheckBox.setBounds(94, 85, 131, 23);
		frame.getContentPane().add(showPassCheckBox);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 25, 74, 14);
		frame.getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 63, 74, 14);
		frame.getContentPane().add(lblPassword);

	}
}
