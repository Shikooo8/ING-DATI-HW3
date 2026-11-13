package it.uniroma3;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

    private final Directory directory;

    public Searcher(Directory directory) {
        this.directory = directory;
    }

    public void queryMangage() throws IOException {
        Analyzer titoloAnalyzer = CustomAnalyzer.builder()
                .withTokenizer("whitespace")
                .addTokenFilter("wordDelimiterGraph")
                .addTokenFilter("flattenGraph")
                .addTokenFilter("lowercase")
                .build();

        Map<String, Analyzer> byField = Map.of(
                "titolo", titoloAnalyzer,
                "contenuto", new ItalianAnalyzer());
        Analyzer perFieldAnalyzer = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), byField);

        try (IndexReader reader = DirectoryReader.open(directory);
                Scanner in = new Scanner(System.in)) {

            IndexSearcher searcher = new IndexSearcher(reader);
            StoredFields stored = searcher.storedFields();

            System.out.println("Sintassi: titolo <termini>  |  contenuto <termini>");
            System.out.println("Frase esatta tra virgolette. Esempi:");
            System.out.println("  titolo gita");
            System.out.println("  contenuto \"viaggio a roma\"");
            System.out.println("Scrivi 'esci' per terminare.");

            while (true) {
                System.out.print("> ");
                if (!in.hasNextLine())
                    break;
                String riga = in.nextLine().trim();
                if (riga.equalsIgnoreCase("esci"))
                    break;
                if (riga.isEmpty())
                    continue;

                String[] parti = riga.split("\\s+", 2);
                String campo = parti[0].toLowerCase();
                if (parti.length < 2 || !(campo.equals("titolo") || campo.equals("contenuto"))) {
                    System.out.println("Query non valida: inizia con 'titolo' o 'contenuto'.");
                    continue;
                }

                try {
                    QueryParser parser = new QueryParser(campo, perFieldAnalyzer);
                    Query query = parser.parse(parti[1]); // le "virgolette" diventano PhraseQuery
                    TopDocs hits = searcher.search(query, 10);

                    System.out.println(hits.totalHits + " risultati");
                    for (int i = 0; i < hits.scoreDocs.length; i++) {
                        ScoreDoc sd = hits.scoreDocs[i];
                        Document doc = stored.document(sd.doc);
                        System.out.printf("%d. %s (score %.3f)%n", i + 1, doc.get("percorso"), sd.score);
                    }
                } catch (ParseException e) {
                    System.out.println("Query non interpretabile: " + e.getMessage());
                }
            }
        }
    }
}