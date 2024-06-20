package cl.praxis.servicio;

import java.util.ArrayList;
import cl.praxis.modelo.Cliente;
import java.util.List;

public class ClienteServicio {

    static List<Cliente> listaClientes;
    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
    }
    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }


    public void retornolistarClientes(){

    }
    public void agregarCliente(Cliente cliente){
        this.listaClientes.add(cliente);
    }

}