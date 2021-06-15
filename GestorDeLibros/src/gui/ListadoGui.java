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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

public class ListadoGui {

	private JFrame frame;
	private Vector<Libro> vector = new Vector<Libro>();

	public ListadoGui(Vector<Libro> vector) {
		this.vector = vector;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("LISTADO DE LIBROS");

		List<String> columns = new ArrayList<String>();
		columns.add("ISBN");
		columns.add("TITULO");
		columns.add("AUTOR");
		columns.add("EDITORIAL");
		columns.add("EDICION");
		columns.add("AÑO DE PUBLICACION");
		List<String[]> values = new ArrayList<String[]>();

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
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setSize(900, 200);
		frame.setLocationRelativeTo(null);
	}

	public Window getFrame() {
		return frame;
	}
}
