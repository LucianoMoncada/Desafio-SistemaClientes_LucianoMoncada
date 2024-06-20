package cl.praxis.vistas;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ArchivoServicio;
import cl.praxis.servicio.ClienteServicio;
import cl.praxis.servicio.ExportadorCsv;
import cl.praxis.servicio.ExportadorTxt;
import cl.praxis.utilidades.Utilidad;


import java.util.Scanner;

public class Menu {
    ClienteServicio clienteServicio = new ClienteServicio();
    ArchivoServicio archivoServicio = new ArchivoServicio();
    ExportadorTxt exportadorTXT = new ExportadorTxt();
    ExportadorCsv exportadorCSV = new ExportadorCsv();
    String fileName = "Clientes";
    String fileName1 = "DBClientes.csv";
    Scanner sc = new Scanner(System.in);
    Utilidad utilidad = new Utilidad();
    public void menuPrincipal(){
        System.out.println("1. Listar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Cargar Datos");
        System.out.println("5. Exportar Datos");
        System.out.println("6. Salir");
    }

    public void menuEditarCliente(){
        System.out.println("Seleccione opcion:");
        System.out.println("1.-Cambiar estado Cliente");
        System.out.println("2.-Editar datos de Cliente");
        System.out.println("3.-Salir");
    }
    public int menuEstado(CategoriaEnum estado){
        System.out.println("Estado actual: " + estado );
        if(estado == CategoriaEnum.Activo){
            System.out.println("1.-Desea cambiar estado de Cliente a Inactivo");
            System.out.println("2.-Desea mantener estado de cliente");
        }else{
            System.out.println("1.-Desea cambiar estado de Cliente");
            System.out.println("2.-Desea mantener estado de cliente a Inactivo");
        }
        utilidad.mostrarMensaje("Seleccione opcion: ");
        return sc.nextInt();


    }
    public int menuDatosACambiar(Cliente cliente){
        System.out.println("DATOS DEL CLIENTE");
        System.out.println("1.-El RUN del Cliente es: " + cliente.getRunCliente());
        System.out.println("2.-El Nombre del Cliente es: " + cliente.getNombreCliente());
        System.out.println("3.-El Apellido del Cliente es: "+ cliente.getApellidoCliente());
        System.out.println("4.-Los años como Cliente son: "+ cliente.getAniosCliente());

        return  accion();
    }

    public int menuExportador(){
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.-Formato csv");
        System.out.println("2.-Formato txt");
        return accion();
    }
    public void iniciarMenu(){
        boolean salirPPAL = false;

        int op =0;
        do {
            menuPrincipal();
            op = accion();
            if(op==1){
                clienteServicio.getListaClientes().forEach(System.out::println);
            } else if (op==2) {
                clienteServicio.agregarCliente(utilidad.crearCliente());
            }else if (op==3) {
                editarCliente();
            }else if (op==4) {
                archivoServicio.exportar(fileName1, ClienteServicio.getListaClientes());
            }else if (op==5) {
                exportarDatos();
            }else if (op==6) {
                salirPPAL = true;
            }

        }while (!salirPPAL);
    }

    public void exportarDatos() {
        int op = menuExportador();
        if(op==1){
            ExportadorCsv.export(fileName, ClienteServicio.getListaClientes());
        } else if (op==2) {
          exportadorTXT.exportar(fileName, ClienteServicio.getListaClientes());
        }else{
            System.out.println("Opción incorrecta");
        }
    }

    private void editarCliente() {
        boolean salirPPAL = false;

        int op =0;
        do {
            menuEditarCliente();
            op = accion();
            if(op!=3){
                int editRun =  getRun();
                if(editRun>=0) {
                    if (op == 1) {
                        editarEstado(editRun);
                    } else if (op == 2) {
                        editarDatosCliente(editRun);
                    }
                }else{
                    System.out.println("Run no existe");
                }
            }else{
                salirPPAL =true;
                System.out.println("Salir editar Cliente");
            }
        }while (!salirPPAL);
    }

    private void editarDatosCliente(int editRun) {
        Cliente cliente =  cambiarItem(menuDatosACambiar(ClienteServicio.getListaClientes().get(editRun)), ClienteServicio.getListaClientes().get(editRun));
        ClienteServicio.getListaClientes().remove(editRun);
        ClienteServicio.getListaClientes().add(editRun,cliente);
    }

    private Cliente cambiarItem(int op, Cliente cliente) {
        String dato = accionDato();
        if(op==1){
            cliente.setRunCliente(dato);
        }else if(op==2){
            cliente.setNombreCliente(dato);
        }else if(op==3){
            cliente.setApellidoCliente(dato);
        }else if(op==4){
            cliente.setAniosCliente(dato);
        }
        return cliente;
    }

    private void editarEstado(int editRun) {
        Cliente editCliente = clienteServicio.getListaClientes().get(editRun);
        int op = menuEstado(editCliente.getNombreCategoria());
        if(op==1){
            if(editCliente.getNombreCategoria() == CategoriaEnum.Activo){
                editCliente.setNombreCategoria(CategoriaEnum.Inactivo);
            }else{
                editCliente.setNombreCategoria(CategoriaEnum.Activo);
            }
        }else{
            System.out.println("Conservar Estado");
        }
    }

    private int getRun() {
        utilidad.mostrarMensaje("Ingrese Run a editar: ");
        String run = sc.next();
        int pos = -1;
        for (int i = 0; i < clienteServicio.getListaClientes().size(); i++) {
            if(run.equals(clienteServicio.getListaClientes().get(i).getRunCliente())){
                pos = i;
            }
        }
        return pos;
    }

    public int accion(){
        utilidad.mostrarMensaje("Ingrese la opción: ");
        return sc.nextInt();
    }
    public String accionDato(){
        utilidad.mostrarMensaje("Ingrese información: ");
        return sc.next();
    }
}