package cn.ixan.search.utils;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * word 文档处理类
 */
public class DocUtil {
    private static final Logger logger = LoggerFactory.getLogger(DocUtil.class);

    public static void main(String[] args) {
        List<String> strings = readWordFile("/Users/mac/Desktop/ES/Doc1.docx");
        StringBuilder stringBuilder = new StringBuilder();

        strings.forEach(stringBuilder::append);
        String s = stringBuilder.toString();
        System.out.println(StringUtils.isNotBlank(s)?s:"null of content");
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
                logger.debug("此文件{}不是word文件", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != stream) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("读取word文件失败");
            }
        }
        return contextList;
    }
}
