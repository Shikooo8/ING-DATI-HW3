package it.uniroma3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

public class Writer {

    private Directory directory;
    private Path pathTxt;

    public Writer(Directory directory, Path pathTxt) {
        this.directory = directory;
        this.pathTxt = pathTxt;
    }

    public void createIndex() throws IOException {
        long start = System.nanoTime();
        int indicizzati = 0;

        Map<String, Analyzer> byField = Map.of( // Se ne abbiamo diversi per campo
                "titolo", new StandardAnalyzer(),
                "contenuto", new ItalianAnalyzer());
        Analyzer perFieldAnalyzer = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), byField);

        IndexWriterConfig config = new IndexWriterConfig(perFieldAnalyzer);
        config.setOpenMode((IndexWriterConfig.OpenMode.CREATE));

        IndexWriter writer = new IndexWriter(directory, config);

        DirectoryStream<Path> files = Files.newDirectoryStream(pathTxt, ".txt");

        for (Path file : files) {
            if (Files.isRegularFile(file)) { // TODO controlla

                String contenuto = Files.readString(file, StandardCharsets.UTF_8);
                String nomeFile = file.getFileName().toString();
                String nomePulito = nomeFile.replaceAll("(?i)\\.txt$", "").replaceAll("[_\\-.]+", " ");

                Document doc = new Document();

                doc.add(new TextField("titolo", nomePulito, Field.Store.YES));
                doc.add(new TextField("contenuto", contenuto, Field.Store.NO));
                // paper.add(new StringField("id", "p01", Field.Store.YES)); ci metto l'ID

                writer.addDocument(doc);
                indicizzati++;
            }
        }
        writer.commit();
        writer.close();
        files.close();

        long ms = (System.nanoTime() - start) / 1000000;
        System.out.println("File incicizzati: " + indicizzati + " in " + ms + " ms");
    }

}
