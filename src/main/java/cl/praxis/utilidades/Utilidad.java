package cl.praxis.utilidades;


import java.util.Scanner;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;



public class Utilidad {
    Scanner sc = new Scanner(System.in);
    public Utilidad() {
    }

    public void limpiarPantalla(){
        try {
            final String os = System.getProperty("os.name");
            System.out.println(os);
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public Cliente crearCliente(){
        String run = pedirDato("run");
        String name = pedirDato("nombre");
        String last = pedirDato("apellido");
        String anio = pedirDato("años como cliente");
        String estado = pedirDato("Activo o Inactivo?");

        Cliente cliente = new Cliente(run, name, last, anio, CategoriaEnum.valueOf(estado));
        return cliente;
    }

    private String pedirDato(String s) {
        System.out.println("Ingrese "+s+ ": ");
        return sc.next();
    }

}