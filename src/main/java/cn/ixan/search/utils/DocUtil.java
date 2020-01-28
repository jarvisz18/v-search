package cn.ixan.search.utils;

import cn.ixan.search.domain.ImExtInfo;
import cn.ixan.search.domain.ImageExtInfo;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.uwyn.jhighlight.tools.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * word 文档处理类
 */
@Slf4j
public class DocUtil {

    public static void main(String[] args) {
        //List<String> strings = readWordFile("/Users/mac/Desktop/ES/Doc1.docx");
        List<String> strings = readWordFile("C:\\Users\\Administrator\\Desktop\\规范.docx");
        System.out.println(wordToString(strings));
    }

    public static ImExtInfo readFile(String inputPath){
        ImExtInfo imExtInfo;
        String fileExt = FileUtils.getExtension(inputPath);
        switch (fileExt){
            case "pdf":
                imExtInfo = readPdfFile(inputPath);
                break;
            case "docx":
                imExtInfo = readDocxFile(inputPath);
                break;
            case "doc":
                imExtInfo = readDocFile(inputPath);
                break;
            case "json":
            case "txt":
            case "md":
                imExtInfo = readTxtFile(inputPath);
                break;
            default:
                log.info("unknow type:"+fileExt);
                imExtInfo = null;
        }
        return imExtInfo;
    }

    private static ImExtInfo readTxtFile(String inputPath) {
        return null;
    }

    /**
     * doc文档解析<br/>
     * @param inputPath 文件路径
     * @return 解析结果
     */
    private static ImExtInfo readDocFile(String inputPath) {
        ImExtInfo imExtInfo = new ImageExtInfo();
        try {
            File file = new File(inputPath.trim());
            String title = file.getName();
            String path = file.getAbsolutePath();
            FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(inputStream);
            WordExtractor extractor = new WordExtractor(document);
            String[] contextArray = extractor.getParagraphText();
            StringBuilder builder = new StringBuilder();
            for(String context:contextArray){
                builder.append(context.trim());
            }
            String content = builder.toString();
            imExtInfo.setTitle(title);
            imExtInfo.setPath(path);
            imExtInfo.setContent(content);
            imExtInfo.setCreate_time(DateUtil.currentTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imExtInfo;
    }

    /**
     * docx文档解析<br/>
     * @param inputPath 文件路径
     * @return 解析结果
     */
    public static ImExtInfo readDocxFile(String inputPath) {
        ImExtInfo imExtInfo = new ImageExtInfo();
        try {
            File file = new File(inputPath.trim());
            String title = file.getName();
            String path = file.getAbsolutePath();
            FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            StringBuilder builder = new StringBuilder();
            for(XWPFParagraph paragraph:paragraphs){
                builder.append(paragraph.getText().trim());
            }
            String content = builder.toString();
            imExtInfo.setTitle(title);
            imExtInfo.setPath(path);
            imExtInfo.setContent(content);
            imExtInfo.setCreate_time(DateUtil.currentTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imExtInfo;
    }

    private static ImExtInfo readPdfFile(String inputPath) {
        return null;
    }


    public static String wordToString(List<String> word) {
        StringBuilder builder = new StringBuilder();
        word.forEach(builder::append);
        String s = builder.toString();
        log.info("文件内容为:{}", StringUtils.isNotBlank(s)?s:"null of content");
        return s;
    }

    public static List<String> readWordToString(InputStream stream,String fileName) {
        List<String> contextList = Lists.newArrayList();
        try {
            if (fileName.endsWith(".doc")) {
                HWPFDocument document = new HWPFDocument(stream);
                WordExtractor extractor = new WordExtractor(document);
                String[] contextArray = extractor.getParagraphText();
                Arrays.asList(contextArray).forEach(context -> contextList.add(CharMatcher.whitespace().removeFrom(context)));
                extractor.close();
                document.close();
            } else if (fileName.endsWith(".docx")) {
                XWPFDocument document = new XWPFDocument(stream).getXWPFDocument();
                List<XWPFParagraph> paragraphList = document.getParagraphs();
                paragraphList.forEach(paragraph -> contextList.add(CharMatcher.whitespace().removeFrom(paragraph.getParagraphText())));
                document.close();
            } else {
                log.debug("此文件{}不是word文件", fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.debug("读取word文件失败");
            }
        }
        return contextList;
    }

    public static List<String> readWordFile(String path) {
        List<String> contextList = Lists.newArrayList();
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(path));
            if (path.endsWith(".doc")) {
                HWPFDocument document = new HWPFDocument(stream);
                WordExtractor extractor = new WordExtractor(document);
                String[] contextArray = extractor.getParagraphText();
                Arrays.asList(contextArray).forEach(context -> contextList.add(CharMatcher.whitespace().removeFrom(context)));
                extractor.close();
                document.close();
            } else if (path.endsWith(".docx")) {
                XWPFDocument document = new XWPFDocument(stream).getXWPFDocument();
                List<XWPFParagraph> paragraphList = document.getParagraphs();
                paragraphList.forEach(paragraph -> contextList.add(CharMatcher.whitespace().removeFrom(paragraph.getParagraphText())));
                document.close();
            } else {
                log.debug("此文件{}不是word文件", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != stream) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.debug("读取word文件失败");
            }
        }
        return contextList;
    }
}
