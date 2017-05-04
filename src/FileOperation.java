

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;  
import java.io.FileWriter;
import java.io.RandomAccessFile;  
  
public class FileOperation {  
   
 /** 
  * �����ļ� 
  * @param fileName 
  * @return 
  */  
 public static boolean createFile(File fileName)throws Exception{  
  boolean flag=false;  
  try{  
   if(!fileName.exists()){  
    fileName.createNewFile();  
    flag=true;  
   }  
  }catch(Exception e){  
   e.printStackTrace();  
  }  
  return true;  
 }   
   
 /** 
  * ��TXT�ļ����� 
  * @param fileName 
  * @return 
  */  
 public static String readTxtFile(File fileName)throws Exception{  
  String result="";  
  FileReader fileReader=null;  
  BufferedReader bufferedReader=null;  
  try{  
   fileReader=new FileReader(fileName);  
   bufferedReader=new BufferedReader(fileReader);  
   try{  
    String read="";  
    while((read=bufferedReader.readLine())!=null){  
     result=result+read+"\r\n";  
    }  
   }catch(Exception e){  
    e.printStackTrace();  
   }  
  }catch(Exception e){  
   e.printStackTrace();  
  }finally{  
   if(bufferedReader!=null){  
    bufferedReader.close();  
   }  
   if(fileReader!=null){  
    fileReader.close();  
   }  
  }   
  return result;  
 }  
   
  /*
   * ����ԭ������д�ļ�
   * */ 
 public static boolean writeTxtFile(File  fileName,String content)throws Exception{  
  RandomAccessFile mm=null;  
  boolean flag=false;  
  FileOutputStream o=null;  
  try {  
   o = new FileOutputStream(fileName);  
   	  //System.out.print(content);
      o.write(content.getBytes("GBK"));  
      o.close();  
//   mm=new RandomAccessFile(fileName,"rw");  
//   mm.writeBytes(content);  
   flag=true;  
  } catch (Exception e) {  
   // TODO: handle exception  
   e.printStackTrace();  
  }finally{  
   if(mm!=null){  
    mm.close();  
   }  
  }  
  return flag;  
 }  
  
 
/*
 * ����ԭ������
 * */  
public static void contentToTxt(File  fileName, String content) {  
        String str = new String(); //ԭ��txt����  
        String s1 = new String();//���ݸ���  
        try {  
            File f = fileName;  
            if (f.exists()) {  
               // System.out.print("�ļ�����");  
            } else {  
                System.out.print("�ļ�������");  
                f.createNewFile();// �������򴴽�  
            }  
            BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((str = input.readLine()) != null) {  
                s1 += str;  
            }  
            ///System.out.println(s1);  
            input.close();  
            s1 += content;  
  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(s1);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }  
  
}  