package com.fww.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 范文武
 * @date 2018/05/04 14:21
 */
public class Indexer {
    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException {
        //this directory will contain the indexes
        Directory indexDirectory =
                FSDirectory.open(Paths.get(indexDirectoryPath));

        //create the indexer
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(indexDirectory, config);
    }

    public void close() throws CorruptIndexException, IOException{
        writer.close();
    }

    private Document getDocument(Path file) throws IOException{
        try(InputStream stream = Files.newInputStream(file)) {
            Document document = new Document();
            //index file contents
            Field contentField = new StringField(LuceneConstants.CONTENTS, file.toString(), Field.Store.YES);
            //index file name
            Field fileNameField = new TextField(LuceneConstants.FILE_NAME,
                    new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)));
            //index file path
            document.add(contentField);
            document.add(fileNameField);
            return document;
        }
    }

    private void indexFile(Path file) throws IOException{
        System.out.println("Indexing "+file.getFileName());
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    public int createIndex(String dataDirPath, FileFilter filter)
            throws IOException{
        //get all files in the data directory
        File[] files = new File(dataDirPath).listFiles();

        for (File file : files) {
            if(!file.isDirectory()
                    && !file.isHidden()
                    && file.exists()
                    && file.canRead()
                    && filter.accept(file)
                    ){
                indexFile(Paths.get(dataDirPath));
            }
        }
        return writer.numDocs();
    }
}
