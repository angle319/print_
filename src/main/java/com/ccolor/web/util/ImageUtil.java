package com.ccolor.web.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	static public int middle_width = 500;
	static public int small_width = 200;
	static public int mini_width = 100;

	public static BufferedImage getScaledImage(byte[] data, int width) throws IOException {
		return getScaledImage(data, width, -1);
	}

	public static BufferedImage getScaledImage(byte[] data, int width, int height) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		BufferedImage image = ImageIO.read(in);

		return getScaledImage(image, width, height);
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width) throws IOException {
		return getScaledImage(image, width, -1);
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		double scaleX = (double) width / imageWidth;
		double scaleY = (height != -1) ? (double) height / imageHeight : scaleX;
		if (height == -1)
			height = (int) (image.getHeight() * scaleX);
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

		return bilinearScaleOp.filter(image, new BufferedImage(width, height, image.getType()));
	}

}
