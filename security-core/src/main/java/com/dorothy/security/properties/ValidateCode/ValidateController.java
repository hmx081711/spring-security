package com.dorothy.security.properties.ValidateCode;


import com.dorothy.security.properties.ValidateCode.generator.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageValidCodeGenerator;

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/image/code")
    public void generateValidImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //生成验证码
        ImageCode imageCode = imageValidCodeGenerator.generateImageCode();
        //将验证码放入session
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
