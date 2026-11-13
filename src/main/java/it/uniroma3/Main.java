package it.uniroma3;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {
    public static void main(String[] args) throws IOException {

        // Path path = Paths.get("../../../../../target/idx0"); 
        Path pathIdx = Paths.get("indice"); //Dove lucene metterà l'indice
        Path pathTxt = Paths.get("files"); //Dove lucene cerca i file


        Writer writer = new Writer(pathIdx, pathTxt);
        writer.createIndex();

        

        // Searcher searcher = new Searcher();
    }
}

 
