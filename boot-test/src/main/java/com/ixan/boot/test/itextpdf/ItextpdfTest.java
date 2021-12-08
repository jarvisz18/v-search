package com.ixan.boot.test.itextpdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import com.ixan.boot.utils.ItextpdfUtil;
import org.junit.Test;

import java.io.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/4/8 8:59
 * @description pdf添加水印测试
 * @version 1.0
 */
public class ItextpdfTest {
	public static void main(String[] args) throws IOException {
		// 要输出的pdf文件
		File file = new File("C:\\Users\\Administrator\\Desktop\\pdf\\诉讼流程节点走向.pdf");
		String fontPath = "D:\\tools\\fonts\\simfang.ttf";
		String imageFile = "D:\\tools\\fonts\\审批通过.png";
		byte[] bytes = file2Byte(file);
		//添加文字水印
		byte[] toPDF = ItextpdfUtil.addWaterMarkToPDF("张贤龙", bytes, fontPath);
		//添加图片水印
		byte[] image = ItextpdfUtil.addImageToPDF(toPDF, imageFile);
		//生成文件
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\pdf\\诉讼流程节点走向2mark.pdf"));
		fos.write(image);
		fos.close();
	}

	@Test
	public void addWaterMarkTest() throws Exception {
		// 要输出的pdf文件
		String file = "C:\\Users\\Administrator\\Desktop\\pdf\\诉讼流程节点走向.pdf";
		//生成文件
		String outPutFile = "C:\\Users\\Administrator\\Desktop\\pdf\\诉讼流程节点走向2mark.pdf";
		String fontPath = "D:\\tools\\fonts\\simfang.ttf";
		addWaterMark(file, outPutFile, "张贤龙", fontPath);
	}

	/**
	 * 添加文字水印，并附加UUID
	 * @param srcFile 待加水印文件
	 * @param destFile 加水印后输出文件
	 * @param text 文本内容
	 * @throws Exception
	 */
	public static void addWaterMark(String srcFile, String destFile, String text, String fontPath) throws Exception {
		// 待加水印的文件
		PdfReader reader = new PdfReader(srcFile);
		// 加完水印的文件
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile));
		//stamper.setEncryption(USER_PASS.getBytes(),OWNER_PASS.getBytes(),1,false);
		int total = reader.getNumberOfPages() + 1;
		PdfContentByte content;
		// 设置透明度
		PdfGState gs = new PdfGState();
		gs.setFillOpacity(0.4f);
		// 设置字体
		BaseFont base = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// 循环对每页插入水印
		for (int i = 1; i < total; i++) {
			// 水印的起始
			content = stamper.getOverContent(i);
			content.setGState(gs);
			content.setFontAndSize(base, 28);
			// 开始
			content.beginText();
			// 设置颜色 默认为黑色
			content.setColorFill(BaseColor.BLACK);
			// 开始写入水印
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 100,
					50, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 100,
					250, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 100,
					450, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 100,
					650, 30);

			content.showTextAligned(Element.ALIGN_MIDDLE, text, 300,
					50, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 300,
					250, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 300,
					450, 30);
			content.showTextAligned(Element.ALIGN_MIDDLE, text, 300,
					650, 30);
			content.endText();
		}
		stamper.close();
	}

	public static byte[] file2Byte(File tradeFile) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(tradeFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
