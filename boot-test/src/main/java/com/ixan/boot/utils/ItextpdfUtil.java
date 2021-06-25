package com.ixan.boot.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItextpdfUtil {
	private static Logger logger = LoggerFactory.getLogger(ItextpdfUtil.class);
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static byte[] addWaterMarkToPDF(String userName, byte[] contentByte, String fontPath) {
		// 待加水印的文件
		PdfReader reader = null;
		// 加完水印的文件
		PdfStamper stamper = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		if (contentByte == null) {
			return null;
		}
		try {
			reader = new PdfReader(contentByte);
			stamper = new PdfStamper(reader, outputStream);

			int total = reader.getNumberOfPages() + 1;
			//(字体参数，字体编码格式，是否将字体信息嵌入到pdf中（一般不需要嵌入），字体大小)
			BaseFont base = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			if (userName.length() < 15) {
				userName = StringUtils.leftPad(userName, 15, ' ');
			}
			String name = new StringBuilder(40).append(userName) + " " + df.format(LocalDate.now()) + "        ";

			String doubleName = new StringBuilder(120).append(name).append(name).append(name).toString();

			float fontSize = 20f;
			float fontHeight;
			float opacity = 0.4f;
			int degray = 45;
			// 设置水印透明度
			PdfGState gs = new PdfGState();
			//设置填充字体不透明度为0.4f
			gs.setFillOpacity(opacity);

			float name_width = 0;

			float name_height1 = 0;

			float name_height2 = 0;

			float name_height3 = 0;

			float name_height4 = 0;

			float name_width5 = 0;
			float name_height5 = 0;

			float name_width6 = 0;
			float name_height6 = 0;

			if (total > 1) {
				Rectangle pageRect = reader.getPageSizeWithRotation(1);

				float thirdWidth = (pageRect.getWidth() / 3);
				float standHeight = (pageRect.getHeight() / 8);


				fontSize = pageRect.getWidth() / 30;
				fontHeight = (base.getAscentPoint("民", fontSize) - base.getDescentPoint("民", fontSize));
				float spaceHeight = 2 * fontHeight;
				float startHeight = fontHeight / 2;

				name_width = 20;

				name_height1 = standHeight * 6 + startHeight;

				name_height2 = standHeight * 4 + startHeight;

				name_height3 = standHeight * 2 + startHeight;

				name_height4 = startHeight;

				float width5 = 20 + thirdWidth + spaceHeight;
				name_width5 = width5;
				name_height5 = startHeight;

				float width6 = 20 + thirdWidth * 2 + spaceHeight;
				name_width6 = width6;
				name_height6 = startHeight;

			}
			// 循环对每页插入水印
			for (int i = 1; i < total; i++) {
				// 水印的起始
				PdfContentByte overContent = stamper.getOverContent(i);
				overContent.setGState(gs);
				// 设置水印字体参数及大小
				overContent.setFontAndSize(base, fontSize);
				// 开始设置水印
				overContent.beginText();
				// 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
				overContent.showTextAligned(Element.ALIGN_LEFT, name, name_width, name_height1, degray);

				overContent.showTextAligned(Element.ALIGN_LEFT, doubleName, name_width, name_height2, degray);

				overContent.showTextAligned(Element.ALIGN_LEFT, doubleName, name_width, name_height3, degray);

				overContent.showTextAligned(Element.ALIGN_LEFT, doubleName, name_width, name_height4, degray);

				overContent.showTextAligned(Element.ALIGN_LEFT, doubleName, name_width5, name_height5, degray);

				overContent.showTextAligned(Element.ALIGN_LEFT, name, name_width6, name_height6, degray);
				//结束设置
				overContent.endText();
			}

		} catch (Exception e) {
			logger.error("addWaterMarkToPDF error:", e);
			return null;
		} finally {
			if (null != stamper) {
				try {
					stamper.close();
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != reader) {
				reader.close();
			}
		}
		return outputStream.toByteArray();
	}

	public static byte[] addImageToPDF(byte[] contentByte, String imageFile) {
		// 待加水印的文件
		PdfReader reader = null;
		// 加完水印的文件
		PdfStamper stamper = null;
		//设置填充字体不透明度为0.5f
		PdfGState gState = new PdfGState();
		gState.setFillOpacity(0.9f);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		if (contentByte == null) {
			return null;
		}
		if (StringUtils.isEmpty(imageFile)) {
			return null;
		}
		try {
			reader = new PdfReader(contentByte);
			stamper = new PdfStamper(reader, outputStream);
			int total = reader.getNumberOfPages() + 1;
			if (total > 1) {
				Rectangle pageRect = reader.getPageSizeWithRotation(1);
				float width = pageRect.getWidth() - 100F;
				float height = (pageRect.getHeight() - 100F);

				float image1_x = width;
				float image1_y = height;
				// 插入图片水印
				Image image = Image.getInstance(imageFile);
				image.scaleToFit(40, 45);
				// 循环对每页插入水印
				for (int i = 1; i < total; i++) {
					// 水印的起始
					PdfContentByte content = stamper.getOverContent(i);
					content.setGState(gState);
					// 坐标
					image.setAbsolutePosition(image1_x, image1_y);
					content.addImage(image);
				}
			}
			stamper.close();
			reader.close();
		} catch (Exception e) {
			logger.error("addImageToPDF error:", e);
			return null;
		}

		return outputStream.toByteArray();
	}
}
