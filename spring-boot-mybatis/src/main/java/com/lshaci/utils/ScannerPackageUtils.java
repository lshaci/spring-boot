package com.lshaci.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ScannerPackageUtils {
 
    /**
     * 获取某包下所有类
     * 
     * @param packageName 	包名
     * @param isRecursion 	是否遍历子包
     * @return 类的完整名称
     */
    public static Set<String> getClassNames(String packageName, boolean isRecursion) {
        Set<String> classNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        //查找具有给定名称的资源
        //file:/E:/svn/trunk/code/crm/WebContent/WEB-INF/classes/com/lshaci/crm/service/impl
        URL url = loader.getResource(packagePath);
        if (url != null) {
        	//获取此 URL 的协议名称
            String protocol = url.getProtocol(); 	//file
            if (protocol.equals("file")) {
            	// url.getPath():获取此 URL 的路径部分
            	// /E:/svn/trunk/code/crm/WebContent/WEB-INF/classes/com/lshaci/crm/service/impl
                classNames = getClassNamesFromDir(url.getPath(), packageName, isRecursion);
            } else if (protocol.equals("jar")) {
				JarFile jarFile = null;
				try{
					jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
				} catch(Exception e){
					e.printStackTrace();
				}
				
				if(jarFile != null){
					getClassNameFromJar(jarFile.entries(), packageName, isRecursion);
				}
			} else {
				/*从所有的jar包中查找包名*/
				classNames = getClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
			}
        }
        return classNames;
    }
 
    /**
     * 从项目文件获取某包下所有类
     * 
     * @param filePath 		文件路径
     * @param className 	类名集合
     * @param isRecursion 	是否遍历子包
     * @return 类的完整名称
     */
    private static Set<String> getClassNamesFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();
        File file = new File(filePath);
        //返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件
        File[] files = file.listFiles();
        for (File childFile : files) {
        	//判断是否是一个目录
            if (childFile.isDirectory()) {
            	//判断是否需要扫描子目录
                if (isRecursion) {
                	//调用这个方法本身来拿到子目录中的className集合
                    classNames.addAll(getClassNamesFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                }
            } else {
            	//如果不是一个目录，则拿到文件名
                String fileName = childFile.getName();
                //判断文件名是否是已“.class”结尾
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                	//拼接为类的全限定名，加入到集合中
                    classNames.add(packageName+ "." + fileName.replace(".class", ""));
                }
            }
        }
        return classNames;
    }
    
    /**
     * 从jar包中获取所有类的全限定名集合
     * 
     * @param jarEntries		jar包实体
     * @param packageName		包名
     * @param isRecursion		是否遍历子包
     * @return
     */
    private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursion){
		Set<String> classNames = new HashSet<String>();
		
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			if(!jarEntry.isDirectory()){
				/*
	             * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug
	             */
				String entryName = jarEntry.getName().replace("/", ".");
				if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
					entryName = entryName.replace(".class", "");
					if(isRecursion){
						classNames.add(entryName);
					} else if(!entryName.replace(packageName+".", "").contains(".")){
						classNames.add(entryName);
					}
				}
			}
		}
		
		return classNames;
	}
	
	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * 
	 * @param urls URL集合
	 * @param packageName 包路径
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
		Set<String> classNames = new HashSet<String>();
		
		for (int i = 0; i < urls.length; i++) {
			String classPath = urls[i].getPath();
			
			//不必搜索classes文件夹
			if (classPath.endsWith("classes/")) {continue;}

			JarFile jarFile = null;
			try {
				jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jarFile != null) {
				classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
			}
		}
		
		return classNames;
	}
	
}
