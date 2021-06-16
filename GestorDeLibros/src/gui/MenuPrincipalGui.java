package gui;

import javax.swing.JFrame;

import com.Libro;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class MenuPrincipalGui {

	private JFrame frmMenuPrincipal;
	private static String ruta = "libros.tsv";
	private JLabel lblSinRegistro;
	private Vector<Libro> vector = new Vector<Libro>();
	private TableModel tableModel;

	public MenuPrincipalGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu");
		frmMenuPrincipal.setBounds(100, 100, 244, 390);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		frmMenuPrincipal.setLocationRelativeTo(null);

		leerRegistrosPrevios(vector);

		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbmGui abm = new AbmGui(1, vector, frmMenuPrincipal);
				abm.getFrame().setTitle("ALTA");
				abm.getFrame().setVisible(true);
				frmMenuPrincipal.setVisible(false);
			}
		});

		btnAlta.setBounds(44, 24, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnAlta);

		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarRegistros(vector)) {
					AbmGui abm = new AbmGui(2, vector, frmMenuPrincipal);
					abm.getFrame().setTitle("CONSULTA");
					abm.getFrame().setVisible(true);
					frmMenuPrincipal.setVisible(false);
				}
				return;

			}
		});
		btnConsulta.setBounds(44, 58, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnConsulta);

		JButton btnActualizacion = new JButton("Actualizacion");
		btnActualizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarRegistros(vector)) {
					AbmGui abm = new AbmGui(3, vector, frmMenuPrincipal);
					abm.getFrame().setTitle("ACTUALIZACION");
					abm.getFrame().setVisible(true);
					frmMenuPrincipal.setVisible(false);
				}
				return;

			}
		});
		btnActualizacion.setBounds(44, 92, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnActualizacion);

		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarRegistros(vector)) {
					AbmGui abm = new AbmGui(4, vector, frmMenuPrincipal);
					abm.getFrame().setTitle("BAJA");
					abm.getFrame().setVisible(true);
					frmMenuPrincipal.setVisible(false);
				}
				return;
			}
		});
		btnBaja.setBounds(44, 126, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnBaja);

		JButton btnOrdenarRegistros = new JButton("Ordenar registros");
		btnOrdenarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				verificarRegistros(vector);
				vector.sort(null);
				ListadoGuiOrdenado formulario = new ListadoGuiOrdenado(vector, frmMenuPrincipal);
				
				
			}
		});
		btnOrdenarRegistros.setBounds(44, 160, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnOrdenarRegistros);

		JButton btnListarRegistros = new JButton("Listar registros");
		btnListarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarRegistros(vector)) {
					ListadoGui listado = new ListadoGui(vector, frmMenuPrincipal);
					listado.getFrame().setVisible(true);
					frmMenuPrincipal.setVisible(false);
				}
				return;
			}

		});
		btnListarRegistros.setBounds(44, 194, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnListarRegistros);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(44, 274, 145, 23);
		frmMenuPrincipal.getContentPane().add(btnSalir);

		lblSinRegistro = new JLabel("No hay registros");
		lblSinRegistro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSinRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinRegistro.setForeground(Color.RED);
		lblSinRegistro.setVisible(false);
		lblSinRegistro.setBounds(24, 229, 179, 23);
		frmMenuPrincipal.getContentPane().add(lblSinRegistro);
	}

	private void leerRegistrosPrevios(Vector<Libro> vector) {
		String[] campos;
		Libro libro = null;
		try {
			Scanner entrada = new Scanner(new FileReader(ruta));
			while (entrada.hasNextLine()) {
				campos = entrada.nextLine().split("\t");
				libro = new Libro();
				libro.setISBN(campos[0]);
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
				vector.add(libro);
			}
			entrada.close();

		} catch (FileNotFoundException e) {
		}
	}

	private boolean verificarRegistros(Vector<Libro> vector) {
		if (vector.isEmpty()) {
			this.lblSinRegistro.setVisible(true);
			System.out.println("No hay registros.\n");
			return false;
		}
		this.lblSinRegistro.setVisible(false);
		return true;
	}

	public JFrame getFrame() {
		return this.frmMenuPrincipal;
	}
}
