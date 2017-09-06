/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.demo.IndexFiles;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.plexus.util.StringUtils;
import org.xml.sax.SAXException;


/**
 *
 * @author piyush
 */
public class Indexer {
    
    public Indexer(){
        
    }
    
    public void indexing() throws TikaException, SAXException {
        //Input folder
        String docsPath = "C:\\Users\\piyush\\Documents\\NetBeansProjects\\luceneFinal\\indexing\\doc";
        //Output folder
        String indexPath = "C:\\Users\\piyush\\Documents\\NetBeansProjects\\luceneFinal\\indexing\\index";
        //Input Path Variable
        final Path docDir = Paths.get(docsPath);
        try {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            //analyzer with the default stop words
            Analyzer analyzer = new StandardAnalyzer();
            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);
            //Its recursive method to iterate all files and directories
            indexDocs(writer, docDir);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
}
    
    static void indexDocs(final IndexWriter writer, Path path) throws IOException, SAXException, TikaException {
        //Directory?
        if (Files.isDirectory(path)) {
            //Iterate directory
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        //Index this file
                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    } catch (SAXException ex) {
                        Logger.getLogger(IndexFiles.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (TikaException ex) {
                        Logger.getLogger(IndexFiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            //Index this file
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }
    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException, SAXException, TikaException {
        try (InputStream stream = Files.newInputStream(file)) {
            
            BodyContentHandler contenthandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
           
            metadata.set(Metadata.RESOURCE_NAME_KEY, file.getFileName().toString());
            
            
            Parser parser = new AutoDetectParser();
            parser.parse(stream, contenthandler, metadata, new ParseContext());
           
            String[] metadataNames = metadata.names();
            String fileName = file.getFileName().toString();
            //Create lucene Document
            Document doc = new Document();
            for(String key : metadataNames){
                //String name = key.toLowerCase();
                String value = metadata.get(key);
                
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                
                if ("keywords".equalsIgnoreCase(key)) {
                    for (String keyword : value.split(",?(\\s+)")) {
                        doc.add(new StringField("name", keyword, Field.Store.YES));
                    }
                }
                else if ("title".equalsIgnoreCase(key)) {
                    doc.add(new StringField("name", value, Field.Store.YES));
                }
                else {
                    doc.add(new StringField("name", fileName, Field.Store.YES));
                }
                
                
                
            }
            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", contenthandler.toString(), Field.Store.YES));
            
            writer.updateDocument(new Term("path", file.toString()), doc);
        
        }
    }
}
    

