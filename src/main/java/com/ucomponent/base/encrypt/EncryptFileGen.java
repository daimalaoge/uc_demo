package com.ucomponent.base.encrypt;

import com.ucomponent.base.ICommons;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *  2014 All Rights Reserved
 *   EncryptGen.java
 *  2014-1-16
 * NAME:
 * DESC:
 **/
public class EncryptFileGen implements ICommons {

		//static boolean debug =false ;
		// 加密KEY不能随便改动
		static final byte[] KEYVALUE = ENCRY_FILE_KEY.getBytes();
		static final int BUFFERLEN = 512;

		public EncryptFileGen() {
		}

		/**
		 * 对文件进行加密
		 *
		 * @param oldFile
		 *          oldFile 原始要加密的文件
		 * @param newFile
		 *          newFile 加密后的文件
		 * @return
		 */
		public static void encryptFile(String oldFile, String newFile) throws Exception {
			FileInputStream in = new FileInputStream(oldFile);
			File file = new File(newFile);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			int c, pos, keylen;
			pos = 0;
			keylen = KEYVALUE.length;
			byte buffer[] = new byte[BUFFERLEN];
			while ((c = in.read(buffer)) != -1) {
				for (int i = 0; i < c; i++) {
					buffer[i] ^= KEYVALUE[pos];
					out.write(buffer[i]);
					pos++;
					if (pos == keylen)
						pos = 0;
				}
			}
			in.close();
			out.close();
		}

		/**
		 * 对文件进行解密
		 *
		 * @param oldFile
		 *          oldFile 原始要解密的文件
		 * @param newFile
		 *          newFile 解密后的文件
		 * @return
		 */
		public static void decryptFile(String oldFile, String newFile) throws Exception {
			FileInputStream in = new FileInputStream(oldFile);
			File file = new File(newFile);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			int c, pos, keylen;
			pos = 0;
			keylen = KEYVALUE.length;
			byte buffer[] = new byte[BUFFERLEN];
			while ((c = in.read(buffer)) != -1) {
				for (int i = 0; i < c; i++) {
					buffer[i] ^= KEYVALUE[pos];
					out.write(buffer[i]);
					pos++;
					if (pos == keylen)
						pos = 0;
				}
			}
			in.close();
			out.close();
		}

//		/**
//		 * @param args
//		 */
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			try {
//				// debug =false ;
////			String oldFile = new String("d:\\aaa.txt");
////			String newFile = new String("d:\\aaa_en.txt");
////			encryptFile(oldFile, newFile);
//
//				String oldFile2 = new String("d:\\JIEMI.txt");
//				String newFile2 = new String("d:\\JFTJ.txt");
//				encryptFile(oldFile2, newFile2);
//				System.out.println("ok");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
