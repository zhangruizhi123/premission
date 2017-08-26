package com.cjeg.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件管理的工具类
 * @author admin
 *
 */
public class FileUtils
{
	public static int BUFFERSIZE=1024*10;//定义拷贝文件时的缓冲区大小
	/**
	 * 复制两个文件
	 * @param oldFile 原文件的路径
	 * @param newFile 生成文件的路径（如果目录不存在会自动创建）
	 * @throws Exception 
	 */
	public static void copyFile(String oldFile,String newFile) throws Exception
	{
		File oldF=new File(oldFile);
		File newF=new File(newFile);
		if(!oldF.exists())
		{
			throw new Exception("原文件不存在");
		}
		if(oldF.isDirectory())
		{
			throw new Exception("不是一个有效的文件");
		}
		File parent=newF.getParentFile();
		//如果父目录不存在创建父目录
		if(!parent.exists())
		{
			parent.mkdirs();
		}
		
		FileInputStream in=new FileInputStream(oldF);
		FileOutputStream out=new FileOutputStream(newF);
		int len;
		byte bb[]=new byte[BUFFERSIZE];
		while((len=in.read(bb))>0)
		{
			out.write(bb,0,len);
		}
		in.close();
		out.close();
	}
	
	/**
	 * 移动一个文件
	 * @param oldFile
	 * @param newFile
	 * @throws Exception 如果源文件不存在抛出异常
	 */
	public static void moveFile(String oldFile,String newFile) throws Exception
	{
		File oldF=new File(oldFile);
		File newF=new File(newFile);
		if(!oldF.exists())
		{
			throw new Exception("原文件不存在");
		}
		File parent=newF.getParentFile();
		//如果父目录不存在创建父目录
		if(!parent.exists())
		{
			parent.mkdirs();
		}
		oldF.renameTo(newF);
	}
	
	/**
	 * 复制一个目录
	 * @param oldDir 原来的目录
	 * @param newDir 新目录
	 * @throws Exception 如果原目录不存在抛出异常
	 */
	public static void copyDirectory(String oldDir,String newDir) throws Exception
	{
		File oldD=new File(oldDir);
		if(!oldD.exists())
		{
			throw new Exception("不存在目录");
		}
		File newD=new File(newDir);
		if(!newD.exists())
		{
			newD.mkdirs();
		}
		File files[]=oldD.listFiles();
		for (File file : files)
		{
			File tempFile=new File(newDir,file.getName());
			if(file.isDirectory())
			{
				//如果是目录则递归
				copyDirectory(file.getAbsolutePath(),tempFile.getAbsolutePath());
			}
			else
			{
				//如果是文件则复制
				copyFile(file.getAbsolutePath(),tempFile.getAbsolutePath());
			}
		}	
	}
	
	/**
	 * 移动一个文件夹
	 * @param oldDir 原来文件夹
	 * @param newDir 新生成的文件夹
	 * @throws Exception 如果原目录不存在,则抛出异常
	 */
	public static void moveDirectory(String oldDir,String newDir) throws Exception
	{
		File oldD=new File(oldDir);
		if(!oldD.exists())
		{
			throw new Exception("不存在目录");
		}
		File newD=new File(newDir);
		File parent=newD.getParentFile();
		if(!parent.exists())
		{
			parent.mkdirs();
		}
		oldD.renameTo(newD);
		
	}
	
	/**
	 * 压缩一个文件
	 * @param oldFile
	 * @param newFile
	 * @throws Exception
	 */
	public static void zipFile(String oldFile,String newFile)throws Exception
	{
		File oldF=new File(oldFile);
		File newF=new File(newFile);
		if(!oldF.exists())
		{
			throw new Exception("原文件不存在");
		}
		if(oldF.isDirectory())
		{
			throw new Exception("不是一个有效的文件");
		}
		File parent=newF.getParentFile();
		if(!parent.exists())
		{
			parent.mkdirs();
		}
		
		FileInputStream in=new FileInputStream(oldF);
		ZipOutputStream out=new ZipOutputStream(new FileOutputStream(newF));
		out.putNextEntry(new ZipEntry(oldF.getName()));
		int len=0;
		byte bb[]=new byte[BUFFERSIZE];
		while((len=in.read(bb))>0)
		{
			out.write(bb,0,len);
		}
		in.close();
		out.close();
		
	}
	
	public static void zipDirectory(String oldDir,String newFile)throws Exception
	{
		File oldD=new File(oldDir);
		File newF=new File(newFile);
		if(!oldD.exists())
		{
			throw new Exception("原文件不存在");
		}
		File parent=newF.getParentFile();
		if(!parent.exists())
		{
			parent.mkdirs();
		}
	}
	
	/**
	 * 获取该目录下的所有文件及文件夹
	 * @return
	 */
	/*
	private Map<String,List<String>>getDirectoryFiles(String fileDir)
	{
		List<String>fileList=new ArrayList<String>();
		List<String>fileList=new ArrayList<String>();
		
		return list;
	}
	*/
	
	public static void main(String[] args)
	{
		String base="C:\\Users\\admin\\Desktop\\新建文件夹\\";
		try
		{
			zipFile(base+"README.txt",base+"\\abc\\dd");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
