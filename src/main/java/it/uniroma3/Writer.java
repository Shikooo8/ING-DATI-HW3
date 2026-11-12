package it.uniroma3;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

public class Writer {

    IndexWriterConfig config = new IndexWriterConfig();
    IndexWriter writer = new IndexWriter(directory, config);

    Document document1 = null; // bisogna aggiungere i docuente su cui dobbiamo fare l'indice
    writer.addDocument(document1);

    writer.commit();

}
