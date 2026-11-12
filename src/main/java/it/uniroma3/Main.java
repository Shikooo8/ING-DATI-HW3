package it.uniroma3;


import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



public class Main {
    public static void main(String[] args) {

        Path path = Paths.get("../../../../../target/idx0"); //Dove lucene metterà l'indice
        Directory directory = FSDirectory.open(path);
        Document paper = new Document();
        paper.add(new TextField( "title", "Neural information retrieval", Field.Store.YES));
        paper.add(new TextField("abstract","Neural models improve information retrieval and ranking.", Field.Store.NO)); //non voglio prenderlo tutto
    
        Searcher searcher = new Searcher();
    }
}