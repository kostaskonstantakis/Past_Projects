/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xanaxxxsearch.indexer;

import xanaxxxsearch.model.MedFile;
import gr.uoc.csd.hy463.NXMLFileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csd3219@csd.uoc.gr
 */
public class Indexer {
    private static final Set<String> STOPWORDS = new TreeSet<>();
    private class NXMLFileReader2 extends NXMLFileReader {
    
        public NXMLFileReader2(File file) throws UnsupportedEncodingException, IOException {
            super(file);
        }
        public MedFile readFile() {
            return new MedFile(
                    file.getAbsolutePath(), 
                    this.getPMCID(), 
                    STOPWORDS,
                    this.getTitle(),
                    this.getAbstr(),
                    this.getBody(),
                    this.getJournal(),
                    this.getPublisher(),
                    this.getAuthors(),
                    this.getCategories());
        }
    }
    private File file = null;
    private NXMLFileReader2 fileReader;

    public Indexer() {
        if (STOPWORDS == null || STOPWORDS.isEmpty())
            throw new IllegalStateException("stopwords dictionary not initialized");
    }

    public Indexer(String stopwordsEnPath, String stopwordsGrPath) throws FileNotFoundException, IOException {
        if (!STOPWORDS.isEmpty())
            return;
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(
                    new FileInputStream(stopwordsEnPath), "UTF-8"));
            String line = bf.readLine();
            while (line != null) {
                STOPWORDS.add(new String(line.getBytes("UTF-8"), "UTF-8"));
                line = bf.readLine();
            }
            bf.close();
            bf = new BufferedReader(new InputStreamReader(
                    new FileInputStream(stopwordsGrPath), "UTF8"));
            line = bf.readLine();
            while (line != null) {                
                STOPWORDS.add(new String(line.getBytes("UTF-8"), "UTF-8"));
                line = bf.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (bf != null)
                bf.close();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public MedFile read() throws FileNotFoundException, IOException {
        if (file == null)
            throw new FileNotFoundException();
        fileReader = new NXMLFileReader2(file);
        return fileReader.readFile();        
    }
}