/**
 * 建立索引类
 * 
 */
import org.apache.lucene.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.store.*;

import java.io.*;

import org.apache.lucene.search.*;
import org.apache.lucene.queryParser.*;

import java.util.*;

import org.apache.lucene.util.Version;
import org.apache.lucene.index.*;

import javax.swing.*;
public class LuceneProc {
    public LuceneProc() {
    }
    public static final int MaxHits = 100;
    //创建索引  srcpath：建立索引文件目录  indexpath：索引存放目录
    public static int CreateIndex(String srcpath,String indexpath) {
        IndexWriter writer;
        File docDir = new File(srcpath);//调用file类判断文件路径是否存在和可读取
        if (!docDir.exists() || !docDir.canRead()) {
            MainFrame.pw.println("建立索引出错，文档路径不存在或不可读。");
            return 1;//文档路径不存在或不可读。
        }
        File indexDir = new File(indexpath);
        if(indexDir.exists()){
            delete(indexDir);//清空要存放索引文件的目录
        }
        try {
            writer = new IndexWriter(FSDirectory.open(indexDir), new StandardAnalyzer(Version.LUCENE_CURRENT),
                                     true, IndexWriter.MaxFieldLength.LIMITED);
            IndexFiles.indexDocs(writer, docDir);//创建索引
            writer.optimize();
            writer.close();
            FileOperation fOpertion = new FileOperation();
            String fileDir = indexDir+"/test.txt";
            File file = new File(fileDir);
            fOpertion.createFile(file);
        } catch (Exception ex) {
            MainFrame.pw.println("建立索引出错，索引过程出现异常。");
            return 2;//there is a exception when index the file.
        }
        MainFrame.pw.println("建立索引完成。");
        return 0;//create index successfully.
    }
    public static int SearchProc(String indexpath,String queryString,ArrayList alist){
        try {
            IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexpath)), true);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
            QueryParser qp = new QueryParser(Version.LUCENE_CURRENT,"contents", analyzer);
            Query query = qp.parse(queryString);
            TopDocs hits = searcher.search(query,MaxHits);
            MainFrame.pw.println("查询"+queryString+"成功，结果为"+hits.totalHits+"条。");
            //System.out.println(hits.totalHits);
            for (int i=0; i<hits.totalHits; i++) {
                Document doc = searcher.doc(hits.scoreDocs[i].doc);
                String[] ls = new String[2];
                ls[0]=doc.get("path");
                ls[1]=""+hits.scoreDocs[i].score;
                alist.add(ls);
                MainFrame.pw.println("第" + i + "条：" + doc.get("path"));
            }
        } catch (IOException ex) {
            System.out.println("Create searcher failed!");
            return 1;
        }
        catch (ParseException ex1) {
            System.out.println("parse error!");
            return 2;
        }
        return 0;
    }
    public static void delete(File f){
        if(f.isDirectory()){
            File[] files = f.listFiles();
            for(int i=0;i<files.length;i++)
            {
               if( files[i].isDirectory()){
                   delete(files[i]);
               }else{
                   files[i].delete();
               }
            }
            f.delete();
        }
        else f.delete();
    }
    public static void main(String args[]){
        if(CreateIndex("C:\\Documents and Settings\\Administrator\\桌面\\我","C:\\index\\")==1){
            System.out.println("Create index successful!");
            ArrayList alist = new ArrayList();
            SearchProc("C:\\index\\","error",alist);
        }
        else{
            System.out.println("Create index failed!");
        }
    }
}
