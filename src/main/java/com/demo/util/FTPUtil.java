package com.demo.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;

public class FTPUtil {
    private FTPClient ftp;

    private  boolean connect(String path,String addr,int port,String username,String password) throws Exception {
        boolean result = false;
        ftp = new FTPClient();
        int reply;
        ftp.connect(addr,port);
        ftp.login(username,password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return result;
        }
        ftp.changeWorkingDirectory(path + "\\file");
        result = true;
        return result;
    }

    private void upload(File file) throws Exception{
        if(file.isDirectory()){
            ftp.makeDirectory(file.getName());
            ftp.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                File file1 = new File(file.getPath()+"\\"+files[i] );
                if(file1.isDirectory()){
                    upload(file1);
                    ftp.changeToParentDirectory();
                }else{
                    File file2 = new File(file.getPath()+"\\"+files[i]);
                    FileInputStream input = new FileInputStream(file2);
                    ftp.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        }else{
            File file2 = new File(file.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftp.storeFile(file2.getName(), input);
            input.close();
        }
    }

    public static void main(String[] args) throws Exception{
        FTPUtil t = new FTPUtil();
        boolean abc = t.connect("/tmp","139.199.164.21", 21, "ftp_test", "xt940904");
        System.out.println("是否连接上ftp服务器：" + abc);
        File file = new File("F://ftp//567.txt");
        if(file.exists()){
            t.upload(file);
         //   file.delete();
          //  System.out.println("上传的文件已经被删除！");
        }else{
            System.out.println("不存在上传的文件！请等待！");
        }
        System.out.println("上传流程结束");
    }

}
