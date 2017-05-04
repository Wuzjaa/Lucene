

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;  
import java.io.FileWriter;
import java.io.RandomAccessFile;  
  
public class FileOperation {  
   
 /** 
  * 创建文件 
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
  * 读TXT文件内容 
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
   * 覆盖原有内容写文件
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
 * 更新原有内容
 * */  
public static void contentToTxt(File  fileName, String content) {  
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {  
            File f = fileName;  
            if (f.exists()) {  
               // System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在");  
                f.createNewFile();// 不存在则创建  
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