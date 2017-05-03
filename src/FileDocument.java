/**
 * 
 * 
 */
import java.io.File;
import java.io.FileReader;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.htmlparser.*;
import org.htmlparser.visitors.*;
import org.htmlparser.util.*;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.*;
import java.io.*;
import org.apache.pdfbox.util.*;
import org.apache.pdfbox.searchengine.lucene.*;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.hslf.*;
import org.apache.poi.hslf.model.*;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

/*
 * document��
 *
 **/

public class FileDocument {
    public static Document Document(File f) throws java.io.
            FileNotFoundException {

        Document doc = new Document();
        String[] encoding = {"UTF-8", "GBK", "GB2312", "UTF-8", "ISO8859_1"};
        doc.add(new Field("path", f.getPath(), Field.Store.YES,
                          Field.Index.NOT_ANALYZED));

        doc.add(new Field("modified",
                          DateTools.timeToString(f.lastModified(),
                                                 DateTools.Resolution.MINUTE),
                          Field.Store.YES, Field.Index.NOT_ANALYZED));

        if (f.getPath().endsWith(".txt")) {
            doc.add(new Field("contents", new FileReader(f)));
        } else if (f.getPath().endsWith(".html") || f.getPath().endsWith(".htm")) {
            int i = 0;
            while (true) {
                try {
                    Parser myParser = new Parser(f.getPath());
                    myParser.setEncoding(encoding[i]);
                    TextExtractingVisitor visitor = new TextExtractingVisitor();
                    myParser.visitAllNodesWith(visitor);
                    doc.add(new Field("contents", visitor.getExtractedText(),
                                      Field.Store.YES, Field.Index.ANALYZED));
                    break;
                } catch (ParserException ex) {
                    System.out.println(ex.getCause() + ex.getMessage());
                    i++;
                    if (i >= 4) {
                        MainFrame.pw.println("errors occur in adding file " +
                                             f.getPath());
                        return null;
                    }
                }
            }
        } else if (f.getPath().endsWith(".pdf")) {
            try {
                doc = LucenePDFDocument.getDocument(f);
            } catch (IOException ex1) {
                System.out.println("errors occured");
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        } else if (f.getPath().endsWith(".doc")) {
            String bodyText = null;
            InputStream is = new FileInputStream(f.getPath());
            try {
                WordExtractor ex = new WordExtractor(is); //is��WORD�ļ���InputStream
                bodyText = ex.getText();
                doc.add(new Field("contents", bodyText,
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception e) {
                e.printStackTrace();
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        }else if (f.getPath().endsWith(".jpg")||f.getPath().endsWith(".png")||f.getPath().endsWith(".jpg")||f.getPath().endsWith(".jpeg")||f.getPath().endsWith(".gif")||f.getPath().endsWith(".bmp")) {
            String bodyText = null;
            try {
                bodyText = f.getName();
                doc.add(new Field("contents", bodyText,
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception e) {
                e.printStackTrace();
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        }else if (f.getPath().endsWith(".mp3")||f.getPath().endsWith(".wav")||f.getPath().endsWith(".ogg")) {
            String bodyText = null;
            try {
                bodyText = f.getName();
                doc.add(new Field("contents", bodyText,
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception e) {
                e.printStackTrace();
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        }else if (f.getPath().endsWith(".mp4")||f.getPath().endsWith(".avi")||f.getPath().endsWith(".wmv")||f.getPath().endsWith(".mpg")||f.getPath().endsWith(".3gp")||f.getPath().endsWith(".mov")) {
            String bodyText = null;
            try {
                bodyText = f.getName();
                doc.add(new Field("contents", bodyText,
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception e) {
                e.printStackTrace();
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        }else if (f.getPath().endsWith(".ppt")) {
            StringBuffer content = new StringBuffer("");
            InputStream is = new FileInputStream(f.getPath());
            try {
                SlideShow ss = new SlideShow(new HSLFSlideShow(is)); //is Ϊ�ļ���InputStream������SlideShow
                Slide[] slides = ss.getSlides(); //���ÿһ�Żõ�Ƭ
                for (int i = 0; i < slides.length; i++) {
                    TextRun[] t = slides[i].getTextRuns(); //Ϊ��ȡ�ûõ�Ƭ���������ݣ�����TextRun
                    for (int j = 0; j < t.length; j++) {
                        content.append(t[j].getText()); //����Ὣ�������ݼӵ�content��ȥ
                    }
                    content.append(slides[i].getTitle());
                }
                doc.add(new Field("contents", content.toString(),
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception ex) {
                System.out.println(ex.toString());
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        } else if (f.getPath().endsWith(".xls")) {
            try {
                StringBuffer content = new StringBuffer("");
                InputStream is = new FileInputStream(f.getPath());
                HSSFWorkbook workbook = new HSSFWorkbook(is); //������Excel�������ļ�������
                for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
                    if (null != workbook.getSheetAt(numSheets)) {
                        HSSFSheet aSheet = workbook.getSheetAt(numSheets); //���һ��sheet
                        for (Iterator rit = aSheet.rowIterator(); rit.hasNext(); ) {
                                HSSFRow aRow = (HSSFRow)rit.next(); //���һ����
                                for (Iterator cit = aRow.cellIterator(); cit.hasNext(); ) {
                                        HSSFCell cell = (HSSFCell)cit.next(); //�����ֵ
                                        switch(cell.getCellType()) {
                                        case HSSFCell.CELL_TYPE_STRING:
                                            content.append(cell.getStringCellValue());
                                            break;
                                        case HSSFCell.CELL_TYPE_NUMERIC:
                                            if (DateUtil.isCellDateFormatted(cell)) {
                                                content.append(cell.getDateCellValue());
                                            } else {
                                                content.append(cell.getNumericCellValue());
                                            }
                                            break;
                                        case HSSFCell.CELL_TYPE_BOOLEAN:
                                            content.append(cell.getBooleanCellValue());
                                            break;
                                        case HSSFCell.CELL_TYPE_FORMULA:
                                            content.append(cell.getCellFormula());
                                            break;
                                        default:
                                            content.append(cell.getRichStringCellValue());
                                        }
                            }
                        }
                    }
                }
                doc.add(new Field("contents", content.toString(),
                                  Field.Store.YES, Field.Index.ANALYZED));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                MainFrame.pw.println("errors occur in adding file " + f.getPath());
                return null;
            }
        }else {
            System.out.println("��ʱ��֧�ָ����͵��ļ���");
            //MainFrame.pw.println("��ʱ��֧�ָ����͵��ļ�" + f.getPath());
            return null;
        }
// return the document
        return doc;
    }

    private FileDocument() {}
}
