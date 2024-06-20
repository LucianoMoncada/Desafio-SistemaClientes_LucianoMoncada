package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArchivoServicio extends Exportador{
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        cargarDatos(fileName,listaClientes);
    }
    void cargarDatos(String fileName,List<Cliente> listaClientes){
        //implementar Filereader y BufferedReader
        File newFile = new File("C:/Users/lucia/OneDrive/Documentos/Curso Full Stack/"+fileName);

        if (!newFile.exists()) {
            newFile.mkdirs();
        }

        try {
            int contador = 0;
            FileReader fr = new FileReader(newFile);
            BufferedReader br = new BufferedReader(fr);
            boolean comprobar = false;
            String data;
            while ((data = br.readLine()) != null) {
                String [] aux = data.split(",");
                Cliente newCliente = new Cliente(aux[0],aux[1],aux[2],aux[3],CategoriaEnum.valueOf(aux[4]));
                listaClientes.add(newCliente);
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}