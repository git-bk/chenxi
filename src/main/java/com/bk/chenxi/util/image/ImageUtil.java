package com.bk.chenxi.util.image;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang.StringUtils;

public class ImageUtil {

    public static String getExtName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        // 得到文件的扩展名(无扩展名时将得到全名)
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 没有扩展名
        if (StringUtils.equals(extName, fileName)) {
            return null;
        }
        return extName;
    }

    public static InputStream processImage(BufferedImage bi, String imageType, int x, int y, int width, int height)
                                                                                                                   throws Exception {
        if (null == bi) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi.getSubimage(x, y, width, height), imageType, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    public static InputStream getInputStreamFromBufferedImage(BufferedImage bi, String imageType) throws Exception {
        if (null == bi) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, imageType, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    public static InputStream cutZoom(InputStream in, String fileName, int box_width, int box_height) throws Exception {
        if (null == in) {
            return null;
        }
        String extName = getExtName(fileName);
        BufferedImage bufImg = ImageIO.read(in);
        // 计算裁剪参数
        double rat_ = (box_height * 1.0) / (box_width * 1.0);// 要求长宽比
        double rat = (bufImg.getHeight() * 1.0) / (bufImg.getWidth() * 1.0);// 实际长宽比
        BigDecimal ratbd_ = new BigDecimal(rat_).setScale(3, RoundingMode.HALF_UP);
        BigDecimal ratbd = new BigDecimal(rat).setScale(3, RoundingMode.HALF_UP);
        int rs = ratbd_.compareTo(ratbd);
        if (rs == 0) {
            InputStream inputThu = ImageUtil.zoomImage(ImageUtil.getInputStreamFromBufferedImage(bufImg, extName),
                                                       extName, box_width, box_height);
            return inputThu;
        } else {
            InputStream inputCut = ImageUtil.cutImage(bufImg, extName, bufImg.getWidth(), bufImg.getHeight(), rat_, rs);
            InputStream inputThu = ImageUtil.zoomImage(inputCut, extName, box_width, box_height);
            return inputThu;
        }
    }

    /**
     * 裁剪图片
     * 
     * @param in 待返回文件流
     * @param imageType 文件类型
     * @param imgWidth 图片宽度
     * @param imgHeight 图片高度
     * @param ratio 图片要求长宽比
     * @param ratioCmr 实际长宽比与要求长宽比的差值
     * @return InputStream
     */
    public static InputStream cutImage(BufferedImage bi, String imageType, int imgWidth, int imgHeight, double ratio,
                                       double ratioCmr) throws Exception {
        // 其他情况都需裁剪图片;
        double x = 0, y = 0, w = 0, h = 0, c = 0;
        if (ratioCmr > 0) {// 实际图太宽；w偏大
            c = ((imgWidth * 1.0) - (imgHeight * 1.0) / ratio) / 2;
            x = c;
            y = 0;
            w = (imgHeight * 1.0) / ratio;
            h = imgHeight * 1.0;
        } else {// 实际图太窄
            c = ((imgHeight * 1.0) - ratio * (imgWidth * 1.0)) / 2;
            x = 0;
            y = c;
            w = imgWidth * 1.0;
            h = ratio * (imgWidth * 1.0);
        }
        return cutImage(getInputStreamFromBufferedImage(bi, imageType), imageType, (int) x, (int) y, (int) w, (int) h);
    }

    public static InputStream cutImage(InputStream in, String imageType, int x, int y, int w, int h) throws Exception {
        if (StringUtils.equals(imageType, "gif")) {
            GifDecoder decoder = new GifDecoder();
            int status = decoder.read(in);
            if (status != GifDecoder.STATUS_OK) {
                throw new IOException("read image error!");
            }
            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            encoder.start(os);
            encoder.setRepeat(decoder.getLoopCount());
            for (int i = 0; i < decoder.getFrameCount(); i++) {
                encoder.setDelay(decoder.getDelay(i));
                BufferedImage image = decoder.getFrame(i).getSubimage(x, y, w, h);
                encoder.addFrame(image);
            }
            encoder.finish();

            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } else {
            return cutCommonImage(in, imageType, x, y, w, h);
        }
    }

    public static InputStream cutCommonImage(InputStream in, String imageType, int x, int y, int w, int h)
                                                                                                          throws IOException {
        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(imageType);
        ImageReader reader = iterator.next();
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, imageType, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    /**
     * 缩放图片
     * 
     * @param in 待返回文件流
     * @param imageType 图片类型
     * @param w 图片高度
     * @param h 图片宽度
     * @return InputStream
     * @throws Exception
     */
    public static InputStream zoomImage(InputStream in, String imageType, int w, int h) throws Exception {
        double wr = 0, hr = 0, r = 0;
        if (StringUtils.equals(imageType, "gif")) {
            GifDecoder decoder = new GifDecoder();
            int status = decoder.read(in);
            if (status != GifDecoder.STATUS_OK) {
                throw new IOException("read image error!");
            }
            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            encoder.start(os);
            encoder.setRepeat(decoder.getLoopCount());
            for (int i = 0; i < decoder.getFrameCount(); i++) {
                encoder.setDelay(decoder.getDelay(i));
                BufferedImage image = zoom(decoder.getFrame(i), "gif", w, h);
                encoder.addFrame(image);
            }
            encoder.finish();

            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } else {
            BufferedImage bufImg = ImageIO.read(in);
            Image Itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            wr = w * 1.0 / bufImg.getWidth();
            hr = h * 1.0 / bufImg.getHeight();
            if (wr <= hr) {
                r = wr;
            } else {
                r = hr;
            }
            AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(r, r), null);
            Itemp = ato.filter(bufImg, null);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage) Itemp, imageType, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        }
    }

    private static BufferedImage zoom(BufferedImage bufImg, String imageType, int w, int h) throws Exception {
        double wr = 0, hr = 0;
        Image Itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        wr = w * 1.0 / bufImg.getWidth();
        hr = h * 1.0 / bufImg.getHeight();
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        return (BufferedImage) Itemp;
    }
}
