package com.dorothy.security.properties.ValidateCode.generator;

import com.dorothy.security.properties.ValidateCode.ImageCode;
import com.dorothy.security.properties.ValidateCode.ValidateCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DefaultImageValidCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    @Override
    public ImageCode generateImageCode() {
        //定义字符串记录验证码信息
        String yzm = "";
        int width = validateCodeProperties.getImageCode().getWidth();
        int height = validateCodeProperties.getImageCode().getHeight();
        int length = validateCodeProperties.getImageCode().getLength();
        int expireIn = validateCodeProperties.getImageCode().getExpireIn();
        String yzms = "1234567980abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//			    *1 准备画布
        //创建一个缓冲区  作为画布
        BufferedImage bi = new BufferedImage(width, height, 1);//200宽  60 高  1图片类型
//			    *2 获取画笔
        Graphics2D g = (Graphics2D) bi.getGraphics();
//			    *3 绘制背景
        //设置背景颜色
        g.setColor(Color.white);
        g.fillRect(1, 1, 158, 57);
//			    *4绘制图案
        //设置字体
        Font font = new Font(null, Font.BOLD, 22);//第一个参数  字体样式  第二个参数 加粗/斜体  第三个 字体大小
        g.setFont(font);//把字体和画笔关联
        //写字
        //设置字体颜色
        for (int i = 0; i < length; i++) {
            //随机下标
            int index = (int) (Math.random() * yzms.length());
            //随机字符
            char c = yzms.charAt(index);
            yzm += c;
            //随机颜色
            Color color = getColor();
            g.setColor(color);
            //写字
            g.drawString(c + "", 24 * i, 26);//第一次参数  写的内容  第二三个参数是字体的坐标
            //随机线颜色
            color = getColor();
            g.setColor(color);
            //划线
            g.drawLine(0, (int) (Math.random() * 28), 160, (int) (Math.random() * 28));

        }
        return new ImageCode(bi, yzm, expireIn);
    }

    public static Color getColor() {
        int a1 = (int) (Math.random() * 100 + 120);
        int a2 = (int) (Math.random() * 100 + 120);
        int a3 = (int) (Math.random() * 100 + 120);
        return new Color(a1, a2, a3);
    }
}
