/**
 */

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.lucene.document.*;

/*
 * 
 * ´´½¨Ë÷Òý
 * 
 */
public class IndexFiles {

  private IndexFiles() {}

  static void indexDocs(IndexWriter writer, File file)
    throws IOException {
    // do not try to index files that cannot be read
    if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        // an IO error could occur
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
            indexDocs(writer, new File(file, files[i]));
          }
        }
      } else {

        try {
          Document doc = FileDocument.Document(file);
          if(doc!=null){
              writer.addDocument(doc);
              System.out.println("adding " + file);
              MainFrame.pw.println("adding " + file);
          }else{
              System.out.println("errors occur in adding file " + file);
              //MainFrame.pw.println("errors occur in adding file " + file);
          }
        }
        catch (FileNotFoundException fnfe) {
          ;
        }
      }
    }
  }

}
