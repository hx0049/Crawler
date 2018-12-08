package com.you.win;
 
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;

/**
 * @author 东哥 2016年10月27日
 *
 */
public class AsciiPic {

	public static void createAsciiPic(String imageStr) {
		try {
			byte[] buffer = new BASE64Decoder().decodeBuffer(imageStr.substring(22));
			String filePath = "G:\\youwin\\vCode.gif";
			FileOutputStream fos = new FileOutputStream(filePath);
			IOUtils.write(buffer,fos);
			fos.close();
			String base = "@#&$%*o!;.";// 字符串由复杂到简单
			final BufferedImage image = ImageIO.read(new File(filePath));
			for (int y = 0; y < image.getHeight(); y += 4) {
				for (int x = 0; x < image.getWidth(); x = x+2) {
					final int pixel = image.getRGB(x, y);
					final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
					final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
					final int index = Math.round(gray * (base.length() + 1) / 255);
					System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
				}
				System.out.println();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
