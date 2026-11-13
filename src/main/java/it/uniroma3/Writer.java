package it.uniroma3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

public class Writer {

    private Path pathIdx;
    private Path pathTxt;

    public Writer(Path pathIdx, Path pathTxt) {
        this.pathIdx = pathIdx;
        this.pathTxt = pathTxt;
    }

    public void createIndex() throws IOException {

        Directory directory = FSDirectory.open(pathIdx);

        Map<String, Analyzer> byField = Map.of( //Se ne abbiamo diversi per campo
            "titolo", new StandardAnalyzer(),
            "contenuto", new ItalianAnalyzer()
        );
        Analyzer perFieldAnalyzer = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), byField);

        IndexWriterConfig config = new IndexWriterConfig(perFieldAnalyzer);
        IndexWriter writer = new IndexWriter(directory, config);


        File cartellaTxt = pathTxt.toFile();
        File[] listaFile = cartellaTxt.listFiles();

        if(listaFile != null){
            
            for (File file : listaFile) {
                if (file.getName().endsWith(".txt") && file.isFile()) {
                    Document doc = new Document();
    
                    doc.add(new TextField("titolo", file.getName(), Field.Store.YES));
    
                    doc.add(new TextField("contenuto", new FileReader(file)));
    
                    writer.addDocument(doc);
                }
    
            }
            writer.commit();
            writer.close();
        }
        else System.err.println("La cartella è vuota o non è stata trovata");
        // Document paper = new Document();
        // paper.add(new StringField("id", "p01", Field.Store.YES));
        // paper.add(new TextField("title", "Neural information retrieval", Field.Store.YES));
        // paper.add(new TextField("abstract", "Neural models improve information retrieval and ranking.", Field.Store.NO));
        // writer.addDocument(paper);

    }

}
