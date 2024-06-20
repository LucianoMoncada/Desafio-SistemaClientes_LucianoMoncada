package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;

import java.util.List;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class ExportadorTxt extends Exportador{
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        //implementar  PrintWriter y Filewriter
        File newFile = new File("C:/Users/lucia/OneDrive/Documentos/Curso Full Stack/"+fileName+".txt");
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            int contador = 0;
            FileWriter fr = new FileWriter(newFile);
            PrintWriter br = new PrintWriter(fr,true);
            boolean comprobar = false;
            String data;
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente c = listaClientes.get(i);
                data = c.getRunCliente()+" "+c.getNombreCliente()+" "+c.getApellidoCliente()+" "+c.getAniosCliente()+" "+c.getNombreCategoria()+"\n";
                fr.write(data);
            }
            fr.close();
            br.close();
            System.out.println("CSV exportado con éxito en " + newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}