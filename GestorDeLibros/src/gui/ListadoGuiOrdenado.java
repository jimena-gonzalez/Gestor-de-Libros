package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.Libro;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

public class ListadoGuiOrdenado {

	private JFrame frame;
	private Vector<Libro> vector = new Vector<Libro>();
	private JFrame menu;

	public ListadoGuiOrdenado(Vector<Libro> vector, JFrame frmMenuPrincipal) {
		this.vector = vector;
		this.menu = frmMenuPrincipal;
		initialize();
	}

	private void initialize() {
		// Define el frame actual
		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ingresa el titulo de la operacion que se realizara
		frame.setTitle("LISTADO DE LIBROS ORDENADOS POR ISBN");
		frame.setResizable(false);
		// Se define las columnas a mostrar
		List<String> columns = new ArrayList<String>();
		columns.add("ISBN");
		columns.add("TITULO");
		columns.add("AUTOR");
		columns.add("EDITORIAL");
		columns.add("EDICION");
		columns.add("A\u00d1O DE PUBLICACION");

		// Se define una coleccion para los valores a mostrar
		List<String[]> values = new ArrayList<String[]>();
		// Se procede a cargar los valores a mostrar que provienen de la coleccion de
		// libros leidos del archivo
		for (int i = 0; i < vector.size(); i++) {
			values.add(new String[] { vector.get(i).getISBN(), vector.get(i).getTitulo(), vector.get(i).getAutor(),
					vector.get(i).getEditorial(), Integer.toString(vector.get(i).getEdicion()),
					Integer.toString(vector.get(i).getAnno_de_publicacion()) });
		}

		TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
		frame.getContentPane().setLayout(null);
		JTable table = new JTable(tableModel);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 884, 161);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Se muestra el menu
				menu.setVisible(true);
				// Se finaliza la pantalla actual
				frame.dispose();
			}
		});
		btnCancelar.setToolTipText("Volver al menu");
		btnCancelar.setBounds(380, 170, 89, 23);
		// Se añade el boton al contenedor del frame
		frame.getContentPane().add(btnCancelar);

		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setSize(900, 240);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

	}

	public Window getFrame() {
		return frame;
	}
}