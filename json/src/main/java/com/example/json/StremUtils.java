package com.example.json;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Diviner
 * 流的工具类 把输入流转化成字符串
 * 2018年4月24日 05:09:01
 */
public class StremUtils {
    /**
     * 一般情况下 业务方法的异常 都是抛出去
     * 把输入流的内容转化为字符串
     * @param inputStream 输入流
     * @return null:代表失败 String:代表成功
     */
    public static String readStream(InputStream inputStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            inputStream.close();
            String tempText = new String(baos.toByteArray());
            //解析meta标签
            if (tempText.contains("charset=gb2312")){
                return new String(baos.toByteArray(),"gb2312");
            }else {
                return new String(baos.toByteArray(),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
