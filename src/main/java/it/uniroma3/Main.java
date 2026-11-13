package it.uniroma3;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // Path path = Paths.get("../../../../../target/idx0"); 
        Path pathIdx = Paths.get("indice"); //Dove lucene metterà l'indice
        Path pathTxt = Paths.get("files"); //Dove lucene cerca i file

        Directory directory = FSDirectory.open(pathIdx);



        Writer writer = new Writer(directory, pathTxt);
        writer.createIndex();

        

        Searcher searcher = new Searcher(directory);
        searcher.queryMangage();
    }
}

 
