package it.uniroma3;

import org.apache.lucene.search.SearcherFactory;

public class Searcher {

    String title = SearcherFactory.storedFields().document(hit.doc).get("title");

}
