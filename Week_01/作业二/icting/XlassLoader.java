package cn.icting;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @Description Xlass文件类加载器
 * @Package cn.icting
 * @Author yanweiqiang
 * @Date 2020/10/24 9:48
 */
public class XlassLoader extends ClassLoader{
    public static void main(String[] args){
        XlassLoader xlassLoader = new XlassLoader();
        URL url = XlassLoader.class.getClassLoader().getResource("./cn/icting/Hello.xlass");
        try {
            // 加载类
            Class helloClass = xlassLoader.loadClass("Hello",url.getFile());
            // 获取类对象及初始化对象
            Object helloObj = helloClass.newInstance();
            // 获取并执行方法
            Method method = helloClass.getDeclaredMethod("hello");
            method.setAccessible(true);
            method.invoke(helloObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    * @description 加载指定文件
    * @author yanweiqiang
    * @date 2020/10/24 10:21
    */
    public Class<?> loadClass(String className,String filePath) throws ClassNotFoundException {
        byte[] data = decode(loadByte(filePath));
        return defineClass(className,data,0,data.length);
    }
    /**
    * @description Xlass文件解密
    * @author yanweiqiang
    * @date 2020/10/24 10:12
    */
    public byte[] decode(byte[] data){
        for(int i=0;i<data.length;i++){
            data[i] = (byte) - (data[i] - 255);
        }
        return data;
    }
    /**
    * @description Xlass文件加载
    * @author yanweiqiang
    * @date 2020/10/24 10:11
    */
    public byte[] loadByte(String filePath) throws ClassNotFoundException {
        File xlassFile = new File(filePath);
        try (ByteArrayOutputStream baros = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(xlassFile))){
            int len = 0;
            byte[] buffer = new byte[1024];
            while(-1 != (len = bis.read(buffer))){
                baros.write(buffer,0,len);
            }
            byte[] data = baros.toByteArray();
            return data;
        }catch (FileNotFoundException e){
            throw new ClassNotFoundException(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
