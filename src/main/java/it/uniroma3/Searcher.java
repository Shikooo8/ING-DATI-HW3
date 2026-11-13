package it.uniroma3;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

public class Searcher {

    private Directory directory;

    public Searcher(Directory directory) {
        this.directory = directory;
    }

    public void queryMangage() throws IOException, ParseException{

        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        Scanner scanner = new  Scanner(System.in);
        System.out.println("Immettere query da cercare:\n scrivere il nome da cercare e poi aggiungere \n'+' con le flag necessarie\n'-' per escludere\n':' per definire un campo\n(esempio titolo:Gita.txt +contenuto:Roma");
        String frase = scanner.nextLine();
        scanner.close();

        QueryParser queryParser = new QueryParser(frase, new WhitespaceAnalyzer());
        // QueryParser queryParser = new QueryParser("titolo", new WhitespaceAnalyzer());
        Query query = queryParser.parse(frase); 

        TopDocs hits = searcher.search(query, 3); //I top 3 risultati
        StoredFields storedFields = searcher.storedFields();


        for (int i = 0; i <hits.scoreDocs.length; i++){
            ScoreDoc scoreDoc = hits.scoreDocs[i];
            Document doc = storedFields.document(scoreDoc.doc);
            String titolo = doc.get("titolo");  
            String contenuto = doc.get("contenuto");
            System.out.println("trovato" + i + " " + titolo + ": " + contenuto);

        }       
    }
}
