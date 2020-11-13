/**
 * 
 */
package com.weimi.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lishupeng
 * @create 2017-05-06 下午 2:56
 **/
public class Base64Img {
    public static void main(String[] args) {
//        String strImg = GetImageStr();
        String strImg = "";
        HttpServletRequest request=null;
        System.out.println(strImg);
        GenerateImage(strImg,request,"");
    }

    //图片转化成base64字符串
    public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "C:\\Users\\Administrator\\Desktop\\img\\400886329.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static String  GenerateImage(String imgStr,HttpServletRequest request,String linuxPath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
    	
    		File uploadPath = new File(linuxPath);
    		if(!uploadPath.exists()) {
    			uploadPath.mkdirs();
    		}
    		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //生成jpeg图片
            String imgFilePath =linuxPath+uuid+".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();        
           return uuid+".jpg";     
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

}
