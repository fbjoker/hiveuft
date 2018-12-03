package com.alex.demo.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.xerces.impl.validation.ConfigurableValidationState;

public class HadoopIo {
	
	public void upload() throws IOException, InterruptedException, URISyntaxException {
		
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), conf, "Alex");
		
		FSDataOutputStream fos = fs.create(new Path("/input"));
		
		FileInputStream fis = new FileInputStream(new File("c:/update/1.txt"));
		
		IOUtils.copyBytes(fis, fos,conf);
		
		IOUtils.closeStream(fis);
		IOUtils.closeStream(fos);
		
		fs.close();
		
		
	}
	
	
	
	public void downLoad() throws IOException, InterruptedException, URISyntaxException {
Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), conf, "Alex");


		FSDataInputStream fis = fs.open(new Path("/ouput/xxx"));
		
		FileOutputStream fos = new FileOutputStream(new File("c:/update/1.txt"));
		
		IOUtils.copyBytes(fis, fos,conf);
		
		IOUtils.closeStream(fis);
		IOUtils.closeStream(fos);
		
		fs.close();
		
		
		
	}
	public void downLoadPart1() throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), conf, "Alex");
		
		
		FSDataInputStream fis = fs.open(new Path("/ouput/xxx"));
		
		FileOutputStream fos = new FileOutputStream(new File("c:/update/1.txt"));
		
		byte[] buff= new byte[1024];
		for (int i = 0; i < 1024*128; i++) {
			
			fis.read(buff);
			fos.write(buff);
			
		}
		
		
/*		byte[] buff2= new byte[1024];
		int len;
		while((len=fis.read(buff2))!=-1) {
			fos.write(buff2, 0, len);
			
			
		}*/
		
		IOUtils.closeStream(fis);
		IOUtils.closeStream(fos);
		
		fs.close();
		
		
		
	}
	
	
	public void downLoadPart2() throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), conf, "Alex");
		
		
		FSDataInputStream fis = fs.open(new Path("/ouput/xxx"));
		
		FileOutputStream fos = new FileOutputStream(new File("c:/update/1.txt"));
		
	fis.seek(1024*1024*128);
	
	IOUtils.copyBytes(fis, fos, conf);
		
		
		/*		byte[] buff2= new byte[1024];
		int len;
		while((len=fis.read(buff2))!=-1) {
			fos.write(buff2, 0, len);
			
			
		}*/
		
		IOUtils.closeStream(fis);
		IOUtils.closeStream(fos);
		
		fs.close();
		
		
		
	}
	
	

}
