package com.easecom.common.util;

import com.business.BookActivationCode.BookActivationCodeActionForm;
import org.apache.struts.upload.FormFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubaibing on 2016/12/13.
 */
public class huanyuan {
    public static void main(String[] args) {
        try {
            String encoding="GBK";
            File file=new File("F:/code/4级200.txt");
            FileWriter writer;
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                writer = new FileWriter("f:/code/MD5-4-200.txt");
                while((lineTxt = bufferedReader.readLine()) != null){
                    lineTxt = MD5.getInstance().getMD5ofStr(lineTxt);
                    writer.write(lineTxt+",0,2017");
                    writer.write("\r\n");

                }
                read.close();
                writer.flush();
                writer.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

}
