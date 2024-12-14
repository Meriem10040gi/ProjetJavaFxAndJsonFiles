package org.example.gestioncommande.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.*;
import org.example.gestioncommande.Model.Commande;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class FileReaderWriter {

    public List<Commande> JsonReader(String path) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Commande>>() {}.getType();
        List<Commande> commandes = gson.fromJson(new FileReader(path), listType);
        return commandes;
    }

    public void JsonWriter(Commande commande,String path,int i) throws IOException {
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            if(commande==null&&i==0){
                writer.write("[");
                writer.newLine();
                writer.flush();
                writer.close();
            }
            else{
                if(commande==null&&i==1){
                    writer.write("]");
                    writer.newLine();
                    writer.flush();
                    writer.close();
                }
                else{
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(commande);
                    writer.write(json+",");
                    writer.newLine();
                    writer.flush();
                    writer.close();}
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            throw e;
        }
    }
    public String getResourcePath(String relativePath) {
        try {
            return new File(getClass().getResource(relativePath).toURI()).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void clearFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
