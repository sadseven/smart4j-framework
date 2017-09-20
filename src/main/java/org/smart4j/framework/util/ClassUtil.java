package org.smart4j.framework.util;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.helper.ConfigHelper;

import com.mysql.jdbc.StringUtils;

/**
 * 类操作工具类
 * @author Arthur.liang
 *
 */
public final class ClassUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);	
	
	
	/**
	 * 获取类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader() {
	
		return Thread.currentThread().getContextClassLoader();
		
	}
	
	/**
	 * 加载类
	 * @param className
	 * @param isInitialized
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){

		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure", e);
			throw new RuntimeException();
		}
		return cls;
	}
	
	/**
	 * 获取制定包名下的所有类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocal = url.getProtocol();
					if (protocal.equals("file")) {
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(classSet, packagePath, packageName);
					} else if (protocal.equals("jar")) {
						JarURLConnection jarURLConnection =	(JarURLConnection)url.openConnection();
						if (jarURLConnection != null) {
							JarFile jarFile = jarURLConnection.getJarFile();
							if (jarFile != null) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								if (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if (jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("get class set failure", e);
			throw new RuntimeException();
		}
		return classSet;
	}
	
	
	/**
	 * 遍历给定路径下的所有文件,如果class文件，加载类,如果是目录,递归继续遍历
	 * @param classSet
	 * @param packagePath
	 * @param packageName
	 */
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new java.io.FileFilter() {
			
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if (StringUtil.isNotEmpty(packageName)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className);
			} else {
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(subPackagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(subPackageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}
	
	/**
	 * 根据类名加载类并添加到类集合中
	 * @param classSet
	 * @param classNmae
	 */
	public static void doAddClass(Set<Class<?>> classSet, String classNmae) {
		Class<?> loadClass = loadClass(classNmae, false);
		classSet.add(loadClass);
	}
	
}
