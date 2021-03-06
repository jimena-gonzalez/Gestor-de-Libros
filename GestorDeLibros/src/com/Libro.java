package com;

public class Libro implements Comparable<Libro> {

	private long ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int anno_de_publicacion;

	@Override
	public boolean equals(Object libro) {
		return this == libro || (libro instanceof Libro && ISBN == ((Libro) libro).ISBN);
	}

	@Override
	public int compareTo(Libro libro) {
		return ISBN == libro.ISBN?0:-1;
	}

	@Override
	public String toString() {
		return "ISBN               : " + ISBN + "\n" + "titulo             : " + titulo + "\n" + "autor              : "
				+ autor + "\n" + "editorial          : " + editorial + "\n" + "edicion            : " + edicion + "\n"
				+ "a\\u00f1o de publicacion: " + anno_de_publicacion + "\n";
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	public int getAnno_de_publicacion() {
		return anno_de_publicacion;
	}

	public void setAnno_de_publicacion(int anno_de_publicacion) {
		this.anno_de_publicacion = anno_de_publicacion;
	}
}