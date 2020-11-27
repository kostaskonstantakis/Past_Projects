/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xanaxxxsearch.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author csd3219@csd.uoc.gr
 * This class represents the entry in the vocabulary file
 * containing in its properties the posting file in which are
 * the data for the documents file(path and df properties of the 
 * Term$PostingFileEntry class)
 */
public class TermEntry implements Comparable {

    /*
     * class representation of an entry in the Posting File
     * & Document File Entry for a Term.
     */
    public class Term$PostingFileEntry {
        private String pcmid;
        private double tf;
        private String path;
        private List<Long> positions = new ArrayList<>();
        private long documentsFileOffset;
        
        
        public Term$PostingFileEntry() {}

        public String getPcmid() {
            return pcmid;
        }
        
        public void setPcmid(String pcmid) {
            this.pcmid = pcmid;
        }
        
        public double getTf() {
            return tf;
        }

        public void setTf(double tf) {
            this.tf = tf;
        }
        
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public long getDocumentsFileOffset() {
            return documentsFileOffset;
        }

        public void setDdocumentsFileOffset(long documentsFileOffset) {
            this.documentsFileOffset = documentsFileOffset;
        }

        public List<Long> getPositions() {
            return positions;
        }

        public void setPositions(List<Long> positions) {
            this.positions = positions;
        }
        
        
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 47 * hash + Objects.hashCode(this.pcmid);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Term$PostingFileEntry other = (Term$PostingFileEntry) obj;
            if (!Objects.equals(this.pcmid, other.pcmid)) {
                return false;
            }
            return true;
        }
        
    }
    /*
     * properties of the representation of a Term Entry in the
     * Vocabulary, Posting and Documents Files
     */
    private String term;
    private long df = 1;
    private List<Term$PostingFileEntry> postingFile = new ArrayList<>();
    
    public TermEntry() {}

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public long getDf() {
        return df;
    }

    public void setDf(long df) {
        this.df = df;
    }

    public List<Term$PostingFileEntry> getPostingFile() {
        return postingFile;
    }

    public void setPostingFile(List<Term$PostingFileEntry> postingFile) {
        this.postingFile = postingFile;
    }
    
    public boolean addNewPostingFileEntry(double tf, 
            long position,String pcmid, String path, long documentsFileOffset) {
        Term$PostingFileEntry e = new Term$PostingFileEntry();
        e.pcmid = pcmid;
        e.tf = tf;
        e.documentsFileOffset = documentsFileOffset;
        e.positions.add(position);
        return postingFile.add(e);
    }

    public boolean updatePostingFileEntry(String pcmid, long position) {
        Term$PostingFileEntry temp = new Term$PostingFileEntry();
        temp.setPcmid(pcmid);
        for (Term$PostingFileEntry tpfe:postingFile) {
            if (tpfe.equals(temp)) {
                return tpfe.positions.add(position);
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.term);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TermEntry other = (TermEntry) obj;
        return Objects.equals(this.term, other.term);
    }
    
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new NullPointerException("cannot compare to null");
        }
        if (getClass() != o.getClass()) {
            throw new IllegalArgumentException("cannot compare "
                    +this.getClass().getName()
                    + " with "
                    +o.getClass().getName());
        }
        TermEntry termEntry = (TermEntry)o;
        return term.compareTo(termEntry.getTerm());
    }
    
    public TermEntry parseFromFiles(
            String term,
            long startIndex,
            long endIndex,
            RandomAccessFile postingFile, 
            RandomAccessFile documentsFile) throws IOException {

        TermEntry te = new TermEntry();
        te.setTerm(term);
        List<Term$PostingFileEntry> l = new ArrayList<>();
            do {
                Term$PostingFileEntry pfe;
                pfe = new Term$PostingFileEntry();
                postingFile.seek(startIndex);
                String postLine = postingFile.readLine();                
                postLine = postLine.replace("\n", "");
                String[] postSplit = postLine.split(" ");

                pfe.setPcmid(postSplit[0]);
                try {
                    pfe.setTf(Double.parseDouble(postSplit[1]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                List<Long> positions = new ArrayList<>();
                for (int i=2;i<postSplit.length-1;++i) 
                    positions.add(Long.parseLong(postSplit[i]));
                pfe.setPositions(positions);
                documentsFile.seek(Long.parseLong(postSplit[postSplit.length-1]));
                String[] documentSplit = documentsFile.readLine().replaceAll("\n", "").split(" ");
                String path = "";
                for (int i=1;i<documentSplit.length-2;++i)
                    path += documentSplit[i];
                pfe.setPath(path);
                l.add(pfe);
                startIndex = postingFile.getFilePointer();
            } while (startIndex < endIndex);
        te.setPostingFile(l);
        return te;
        
    }
    
}
