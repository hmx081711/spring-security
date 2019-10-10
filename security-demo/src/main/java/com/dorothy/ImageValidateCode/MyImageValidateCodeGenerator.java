package com.dorothy.ImageValidateCode;

import com.dorothy.security.properties.ValidateCode.ImageCode;
import com.dorothy.security.properties.ValidateCode.ValidateCodeProperties;
import com.dorothy.security.properties.ValidateCode.generator.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


@Component("imageValidCodeGenerator")
public class MyImageValidateCodeGenerator implements ValidateCodeGenerator {


    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    @Override
    public ImageCode generateImageCode() {

        // 设置图片宽度和高度
        int width = validateCodeProperties.getImageCode().getWidth();
        int height = validateCodeProperties.getImageCode().getHeight();
        // 干扰线条数
        int lines = 10;
        // 验证码数组
        int[] random = new int[validateCodeProperties.getImageCode().getLength()];
        // 定义用户保存验证码
        String sysCode = "";
        Random r = new Random();
        BufferedImage b = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = b.getGraphics();
        g.setFont(new Font("宋体", Font.BOLD, 30));
        for (int i = 0; i < validateCodeProperties.getImageCode().getLength(); i++) {
            int number = r.nextInt(10);
            random[i] = number;
            int y = 10 + r.nextInt(40);// 10~40范围内的一个整数，作为y坐标
            // 随机颜色，RGB模式
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            // g.drawString("" + a, 5 + i * width / 4, y);
            // 写验证码
            g.drawString(Integer.toString(number), 5 + i * width / 4, y);
            sysCode += random[i];
        }
        for (int i = 0; i < lines; i++) {
            // 设置干扰线颜色
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
        return new ImageCode(b,sysCode,validateCodeProperties.getImageCode().getExpireIn());
    }
}
