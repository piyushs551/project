/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.bean.Bean;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
//import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author piyush
 */
public class Searcher {

    List<Bean> beanList;

    //path of indexed files
    public static final String Index_Dir = "C:\\Users\\piyush\\Documents\\NetBeansProjects\\luceneFinal\\indexing\\index";

    public Searcher() {

        beanList = new ArrayList<>();

    }

    public List<Bean> searching(String s1, String s2, String radioBtn) throws IOException, ParseException, InvalidTokenOffsetsException {
        //getting reference of directory
        Directory dir = FSDirectory.open(Paths.get(Index_Dir));

        //Index reader - an interface for accessing a point-in-time view of a lucene index
        IndexReader reader = DirectoryReader.open(dir);

        IndexSearcher searcher = new IndexSearcher(reader);
        //analyzer with the default stop words, takes out the stop words
        Analyzer analyzer = new StandardAnalyzer();

        String contents = "contents";

        QueryParser parser = new QueryParser(contents, analyzer);

        int numOfDoc = reader.numDocs();

        for (int i = 0; i < numOfDoc; i++) {

            Document d = reader.document(i);

        }

        Query q1 = parser.parse(s1);
        Query q2 = parser.parse(s2);

        //conjuction, disjunction and negation
        BooleanQuery.Builder bq = new BooleanQuery.Builder();

        //occur.must : both queries required in a doc
        if (radioBtn.equals("conjunction")) {
            bq.add(q1, BooleanClause.Occur.MUST);
            bq.add(q2, BooleanClause.Occur.MUST);
            bq.build();
        } //occur.should: one of the q1 should be presen t in doc
        else if (radioBtn.equals("disjunction")) {
            bq.add(q1, BooleanClause.Occur.SHOULD);
            bq.add(q2, BooleanClause.Occur.SHOULD);
            bq.build();
        } //negation: first should present , second should not
        else {
            bq.add(q1, BooleanClause.Occur.MUST);
            bq.add(q2, BooleanClause.Occur.MUST_NOT);
            bq.build();
        }

        TopDocs hits = searcher.search(bq.build(), 10);

        Formatter formatter = new SimpleHTMLFormatter();

        QueryScorer scorer = new QueryScorer(bq.build());

        //used to markup highlighted terms found in the best sections of a cont
        Highlighter highlighter = new Highlighter(formatter, scorer);
        //It breaks cont up into same-size texts but does not split up spans
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 10);
        //breaks cont up into same-size fragments with no concerns over spotting sentence boundaries.

        //set fragmenter to highlighter
        highlighter.setTextFragmenter(fragmenter);

        for (int i = 0; i < hits.scoreDocs.length; i++) {
            Bean bean = new Bean();

            int outResult = hits.scoreDocs.length;
            bean.setNumFile(outResult);
            int docid = hits.scoreDocs[i].doc;
            double rank = hits.scoreDocs[i].score;
            bean.setRankSc(rank);
            Document doc = searcher.doc(docid);

            String name = doc.get("name");
            String title = doc.get("title");
            bean.setTitle(name);

            String path = doc.get("path");
            bean.setPath(path);

            String cont = doc.get("contents");
            //Create token stream
            TokenStream stream = TokenSources.getAnyTokenStream(reader, docid, "contents", analyzer);
            //Get highlighted cont fragments
            String[] frags = highlighter.getBestFragments(stream, cont, 10);

            ArrayList<String> dummy = new ArrayList<>();
            for (String frag : frags) {

                dummy.add(frag);
            }

            bean.setContent(dummy);
            beanList.add(bean);
        }

        dir.close();
        // }
        return beanList;
    }

    //when only one q1 is given
    public List<Bean> searching(String s1) throws IOException, ParseException, InvalidTokenOffsetsException {
        //Get directory reference
        Directory dir = FSDirectory.open(Paths.get(Index_Dir));
        //Index reader - an interface for accessing a point-in-time view of a lucene index
        IndexReader reader = DirectoryReader.open(dir);
        //CreateIndexReader reader = DirectoryReader.open(dir); lucene searcher. It search over a single IndexReader.
        IndexSearcher searcher = new IndexSearcher(reader);
        //analyzer with the default stop words
        Analyzer analyzer = new StandardAnalyzer();
        //Query parser to be used for creating TermQuery

        String queries = null;
        String queryString = null;   //regular search
        String contents = "contents";
        BufferedReader in = null;
        if (queries != null) {
            in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
        } else {
            in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        }
        QueryParser parser = new QueryParser(contents, analyzer);

        int numOfDoc = reader.numDocs();

        for (int i = 0; i < numOfDoc; i++) {

            Document d = reader.document(i);

        }

        Query q1 = parser.parse(s1);

        BooleanQuery.Builder bq = new BooleanQuery.Builder();

        bq.add(q1, BooleanClause.Occur.MUST);
        //Search the lucene documents
        TopDocs hits = searcher.search(bq.build(), 10);
        // TopScoreDocCollector collector = TopScoreDocCollector.create(5);
        /**
         * Highlighter Code Start ***
         */
        //Uses HTML &lt;B&gt;&lt;/B&gt; tag to highlight the searched terms
        Formatter formatter = new SimpleHTMLFormatter();
        //It scores cont fragments by the number of unique q1 terms found
        //Basically the matching score in layman terms
        QueryScorer scorer = new QueryScorer(bq.build());
        //used to markup highlighted terms found in the best sections of a cont
        Highlighter highlighter = new Highlighter(formatter, scorer);
        //It breaks cont up into same-size texts but does not split up spans
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 10);
        //breaks cont up into same-size fragments with no concerns over spotting sentence boundaries.

        //set fragmenter to highlighter
        highlighter.setTextFragmenter(fragmenter);
        //Iterate over found results
        for (int i = 0; i < hits.scoreDocs.length; i++) {
            Bean bean = new Bean();
            //int rank = hits.scoreDocs.length;
            int outResult = hits.scoreDocs.length;
            bean.setNumFile(outResult);
            int docid = hits.scoreDocs[i].doc;
            double rank = hits.scoreDocs[i].score;
            bean.setRankSc(rank);
            Document doc = searcher.doc(docid);
            // String title = doc.get("title");
            String name = doc.get("name");
            String title = doc.get("title");
            bean.setTitle(name);

            String path = doc.get("path");
            bean.setPath(path);

            String cont = doc.get("contents");
            //Create token stream
            TokenStream stream = TokenSources.getAnyTokenStream(reader, docid, "contents", analyzer);
            //Get highlighted cont fragments
            String[] frags = highlighter.getBestFragments(stream, cont, 10);

            ArrayList<String> dummy = new ArrayList<>();
            for (String frag : frags) {

                dummy.add(frag);
            }

            bean.setContent(dummy);
            beanList.add(bean);
        }

        dir.close();
        // }
        return beanList;
    }
    
    //sending the path of file and query to find the line number
    public int LineNumber(String file, String word) throws Exception {
    BufferedReader buf = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));

    String line;
    int lineNumber = 0;
    while ((line = buf.readLine()) != null)   {
        lineNumber++;
        if (word.equals(line)) {
            return lineNumber;
        }
    }
    return -1;
}
//      optimize index code
//    public static void optimize() throws CorruptIndexException, LockObtainFailedException, IOException {
//		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, new StandardAnalyzer());
//		indexWriter.optimize();
//		indexWriter.close();
//	}
    
}
