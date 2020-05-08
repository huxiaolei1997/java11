package com.xlh.image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.MultiResolutionImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageIoTester {
    public static void main(String[] args) throws IOException {
        List<String> imgUrls = List.of(
                "https://s1.ax1x.com/2020/05/08/YnvydO.png");

        List<Image> images = new ArrayList<Image>();

        for (String url : imgUrls) {
            images.add(ImageIO.read(new URL(url)));
        }

//        URL url = new URL(imgUrls.get(0));
//        DataInputStream dataInputStream = new DataInputStream(url.openStream());
//
//        FileOutputStream fileOutputStream = new FileOutputStream(new File())

        // 读取所有图片
        MultiResolutionImage multiResolutionImage =
                new BaseMultiResolutionImage(images.toArray(new Image[0]));

        // 获取图片的所有分辨率
        List<Image> variants = multiResolutionImage.getResolutionVariants();

        System.out.println("Total number of images: " + variants.size());

        for (Image img : variants) {
            System.out.println(img);
        }

        // 根据不同尺寸获取对应的图像分辨率
        Image variant1 = multiResolutionImage.getResolutionVariant(156, 45);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                156, 45, variant1.getWidth(null), variant1.getHeight(null));

        Image variant2 = multiResolutionImage.getResolutionVariant(311, 89);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]", 311, 89,
                variant2.getWidth(null), variant2.getHeight(null));

        Image variant3 = multiResolutionImage.getResolutionVariant(622, 178);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]", 622, 178,
                variant3.getWidth(null), variant3.getHeight(null));

        Image variant4 = multiResolutionImage.getResolutionVariant(300, 300);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]", 300, 300,
                variant4.getWidth(null), variant4.getHeight(null));
    }
}
