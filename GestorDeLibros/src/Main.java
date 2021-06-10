import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static String ruta = "libros.tsv";
    public static Scanner teclado = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static Usuario defaultUser;
    
    public static void pausar(String mensage) {
        out.print(mensage + "\nPresione <ENTER> para continuar . . . ");
        teclado.nextLine();
        out.println();
    }

    public static String leer_cadena(String mensaje) {
        out.print(mensaje + ": ");
        return teclado.nextLine();
    }

    public static int leer_entero(String mensaje) {
        try {
            return Integer.parseInt(leer_cadena(mensaje));
        } catch (NumberFormatException e) {
            out.print("N\u00FAmero incorrecto.");
            return leer_entero(mensaje);
        }
    }

    public static void mostrar_menu_pppal() {
        out.println("MEN\u00DA");
        out.println("1.- Altas");
        out.println("2.- Consultas");
        out.println("3.- Actualizaciones");
        out.println("4.- Bajas");
        out.println("5.- Ordenar registros");
        out.println("6.- Listar registros");
        out.println("7.- Salir");
    }
    
    public static Libro alta(Libro libro,  Vector<Libro> vec) {
    	
        libro.setTitulo(leer_cadena ("Ingrese el titulo"));
        libro.setAutor(leer_cadena ("Ingrese el autor"));
        libro.setEditorial(leer_cadena ("Ingrese el editorial"));
        libro.setEdicion(leer_entero ("Ingrese el edicion"));
        libro.setAnno_de_publicacion(leer_entero ("Ingrese el anno de publicacion"));
        vec.add(libro);
        out.println("\nRegistro agregado correctamente.");
        return new Libro();
        
    }
    
    public static int ingresar_opcion() {
    	
    	int opcion = 7;
        do {
            opcion = leer_entero ("Seleccione una opci\u00F3n");
            if(opcion<1 || opcion>7)
                out.println("Opci\u00F3nn no v\u00E1lida.");
        } while (opcion<1 || opcion>7);
        
        return opcion;
        
    }
    
    public static int modificar(Libro dato) {
    	
    	int subopcion;
    	
        out.println("Men\u00FA de modificaci\u00F3n de campos");
        out.println("1.- titulo");
        out.println("2.- autor");
        out.println("3.- editorial");
        out.println("4.- edicion");
        out.println("5.- anno de publicacion");
        do {
            subopcion = leer_entero ("Seleccione un n\u00FAmero de campo a modificar");
            if (subopcion<1 || subopcion>5)
                out.println("Opci\u00F3n no v\u00E1lida.");
        } while (subopcion<1 || subopcion>5);
        switch (subopcion) {
            case 1:
                dato.setTitulo(leer_cadena ("Ingrese el nuevo titulo"));
                break;
            case 2:
                dato.setAutor(leer_cadena ("Ingrese el nuevo autor"));
                break;
            case 3:
                dato.setEditorial(leer_cadena ("Ingrese el nuevo editorial"));
                break;
            case 4:
                dato.setEdicion(leer_entero ("Ingrese el nuevo edicion"));
                break;
            case 5:
                dato.setAnno_de_publicacion(leer_entero ("Ingrese el nuevo anno de publicacion"));
                break;
        }
        out.println("\nRegistro actualizado correctamente.");
        
        return subopcion;
        
    }
    
    public static void baja(Vector<Libro> vector, Libro dato) {
    	
    	vector.remove(dato);
        out.println("Registro borrado correctamente.");
        
    }
    
    public static void ordenar_registros(Vector<Libro> vector) {
    	Collections.sort(vector);
        out.println("Registros ordenados correctamente.");
    }
    
    public static int listar_registros(Vector<Libro> vector, int[] contador, Funcion<Libro> imprimir) {
        int n = vector.size();
        contador[0] = 0;
        for (int i=0; i<n; i++)
            imprimir.funcion(vector.get(i), contador);
        out.println("Total de registros: " + contador[0] + ".");
        return n;
    }
    
    public static void main(String[] args) {

        Funcion<Libro> imprimir = new Funcion<Libro>() {
            @Override
            public void funcion(Libro libro, Object parametros) {
                out.println(libro);
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
        if(!System.getProperties().get("os.name").equals("Linux") && System.console()!=null)
            try {
                out = new PrintStream(System.out, true, "CP850");
                teclado = new Scanner(System.in, "CP850");
            } catch (UnsupportedEncodingException e) {}
        
        Libro dato = null, libro;
        Vector<Libro> vector = new Vector<Libro>();
        String[] campos;
        int[] contador = {0};
        int opcion, subopcion;
        int i, n;
        defaultUser = new Usuario();
        
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
            
        } catch (FileNotFoundException e) {}
        
        int status = 0;
        do {
        	
        	if(status != 0) {
        		out.println("Error de credenciales.");
        	}
        	out.println("---------------- Log in ----------------");
        	status = defaultUser.validarUsuario(leer_cadena("Ingrese nombre de usuario"), leer_cadena("Ingrese contraseña"));
        
        }while(status != 0);
        
        libro = new Libro();
        do {
        	
        	mostrar_menu_pppal();
        	
        	opcion = ingresar_opcion();
            out.println();
            
            if (vector.isEmpty() && opcion!=1 && opcion!=7) {
                pausar("No hay registros.\n");
                continue;
            }
            
            if (opcion < 5) {
            	
                libro.setISBN(leer_cadena ("Ingrese el ISBN del libro"));
                i = vector.indexOf(libro);
                dato = i<0 ? null : vector.get(i);
                if (dato!=null) {
                    out.println();
                    imprimir.funcion(dato, contador);
                }
                
            }
            
            if (opcion==1 && dato!=null)
                out.println("El registro ya existe.");
            else if (opcion>=2 && opcion<=4 && dato==null)
                out.println("\nRegistro no encontrado.");
            else switch (opcion) {
	            case 1:
	            	libro = alta(libro, vector);
	                break;
	            case 3:
	            	subopcion = modificar(dato);
	                break;
	            case 4:
	                baja(vector, dato);
	                break;
	            case 5:
	                ordenar_registros(vector);
	                break;
	            case 6:
	            	n = listar_registros(vector, contador, imprimir);
	                break;
	            case 7:
	            	out.println("Saliendo...");
            }
            if (opcion<7 && opcion>=1)
                pausar("");
            
        } while (opcion!=7);
        
        try {
        	
            PrintStream salida = new PrintStream(ruta);
            n = vector.size();
            for (i=0; i<n; i++)
                imprimirEnArchivo.funcion(vector.get(i), salida);
            salida.close();
            
        } catch (FileNotFoundException e) {}
    }
}

interface Funcion<T extends Comparable<T>> {
    void funcion(T dato, Object parametros);
}
