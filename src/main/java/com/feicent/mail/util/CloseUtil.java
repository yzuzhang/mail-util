package com.feicent.mail.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {

	/**
	 * 关闭资源
	 * @param closeables
	 */
	public static void close(Closeable... closeables){
		if( closeables != null )
		{
			for(Closeable closeable : closeables){
				try {
					if(closeable != null){
						closeable.close();
					}
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

}
