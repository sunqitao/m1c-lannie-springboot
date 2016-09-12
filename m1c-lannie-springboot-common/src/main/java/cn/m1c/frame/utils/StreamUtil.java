package cn.m1c.frame.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 2016年7月29日 流工具类
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public final class StreamUtil {

	/**
	 * Private constructor, to prevent instantiation. This class has only static
	 * methods.
	 */
	private StreamUtil() {
		// Does nothing
	}

	/**
	 * Default buffer size for use in
	 * {参照 #copy(InputStream, OutputStream, boolean)}.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * Copies the contents of the given {参照 InputStream} to the given
	 * {参照 OutputStream}. Shortcut for
	 *
	 * <pre>
	 * copy(pInputStream, pOutputStream, new byte[8192]);
	 * </pre>
	 *
	 * 参数 pInputStream
	 *            The input stream, which is being read. It is guaranteed, that
	 *            {参照 InputStream#close()} is called on the stream.
	 * 参数 pOutputStream
	 *            The output stream, to which data should be written. May be
	 *            null, in which case the input streams contents are simply
	 *            discarded.
	 * 参数 pClose
	 *            True guarantees, that {参照 OutputStream#close()} is called
	 *            on the stream. False indicates, that only
	 *            {参照 OutputStream#flush()} should be called finally.
	 *
	 * 返回 Number of bytes, which have been copied.
	 * 异常 IOException
	 *             An I/O error occurred.
	 */
	public static long copy(InputStream pInputStream, OutputStream pOutputStream, boolean pClose) throws IOException {
		return copy(pInputStream, pOutputStream, pClose, new byte[DEFAULT_BUFFER_SIZE]);
	}

	/**
	 * 将InputStream流中的内容复制到OutputStream中
	 *
	 * 参数 pIn
	 *            The input stream, which is being read. It is guaranteed, that
	 *            {参照 InputStream#close()} is called on the stream.
	 * 参数 pOut
	 *            The output stream, to which data should be written. May be
	 *            null, in which case the input streams contents are simply
	 *            discarded.
	 * 参数 pClose
	 *            True guarantees, that {参照 OutputStream#close()} is called
	 *            on the stream. False indicates, that only
	 *            {参照 OutputStream#flush()} should be called finally.
	 * 参数 pBuffer
	 *            Temporary buffer, which is to be used for copying data.
	 * 返回 Number of bytes, which have been copied.
	 * 异常 IOException
	 *             An I/O error occurred.
	 */
	public static long copy(InputStream pIn, OutputStream pOut, boolean pClose,	byte[] pBuffer) throws IOException {
		OutputStream out = pOut;
		InputStream in = pIn;
		try {
			long total = 0;
			for (;;) {
				int res = in.read(pBuffer);
				if (res == -1) {
					break;
				}
				if (res > 0) {
					total += res;
					if (out != null) {
						out.write(pBuffer, 0, res);
					}
				}
			}
			if (out != null) {
				if (pClose) {
					out.close();
				} else {
					out.flush();
				}
				out = null;
			}
			in.close();
			in = null;
			return total;
		} 
		finally {
			IOUtil.close(in);
			if (pClose) {IOUtil.close(out);}
		}
	}

	/**
	 * This convenience method allows to read a
	 * {参照 org.apache.commons.fileupload.FileItemStream}'s content into a
	 * string. The platform's default character encoding is used for converting
	 * bytes into characters.
	 *
	 * 参数 pStream
	 *            The input stream to read.
	 * @see #asString(InputStream, String)
	 * 返回 The streams contents, as a string.
	 * 异常 IOException
	 *             An I/O error occurred.
	 */
	public static String asString(InputStream pStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copy(pStream, baos, true);
		return baos.toString();
	}

	public static String readingTextFile(File textFile) throws IOException{
		return readingTextFile(textFile, "UTF-8");
	}
	
	public static String readingTextFile(File textFile, String chartset) throws IOException{
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(textFile);
			return asString(fileInputStream);
		}
		catch (Exception e) {
			throw e;
		}
		finally{
			IOUtil.close(fileInputStream);
		}
	}
	/**
	 * This convenience method allows to read a
	 * {参照 org.apache.commons.fileupload.FileItemStream}'s content into a
	 * string, using the given character encoding.
	 *
	 * 参数 pStream
	 *            The input stream to read.
	 * 参数 pEncoding
	 *            The character encoding, typically "UTF-8".
	 * @see #asString(InputStream)
	 * 返回 The streams contents, as a string.
	 * 异常 IOException
	 *             An I/O error occurred.
	 */
	public static String asString(InputStream pStream, String pEncoding) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copy(pStream, baos, true);
		return baos.toString(pEncoding);
	}

	/**
	 * 通过NIO将文本写入文件中
	 * 参数 sharSequence
	 * 参数 targetFile
	 */
	public static void writingCharSequence(String sharSequence, File targetFile){
		FileOutputStream fileOutputStream = null;
		try {
			if(!targetFile.exists()){
				if(!targetFile.getParentFile().exists()){
					targetFile.getParentFile().mkdirs();
				}
				targetFile.createNewFile();
			}
			byte[] b = sharSequence.getBytes("UTF-8");
			fileOutputStream = new FileOutputStream(targetFile);
			fileOutputStream.write(b);
			fileOutputStream.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			IOUtil.close(fileOutputStream);
		}
	}


	/**
	 * 通过NIO将文本写入文件中
	 * 参数 sharSequence
	 * 参数 targetFile
	 */
	public static void writingTextFileChannel(String sharSequence, File targetFile){
		FileOutputStream fileOutputStream = null;
		FileChannel fileChannel = null;
		try {
			if(!targetFile.exists()){
				if(!targetFile.getParentFile().exists()){
					targetFile.getParentFile().mkdirs();
				}
				targetFile.createNewFile();
			}
			byte[] b = sharSequence.getBytes("UTF-8");
			fileOutputStream = new FileOutputStream(targetFile);
			fileChannel = fileOutputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(b.length);
			byteBuffer.put(b);
			byteBuffer.flip();
			fileChannel.write(byteBuffer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			IOUtil.close(fileChannel, fileOutputStream);
		}
	}


	/**
	 * 通过NIO读取文本文件
	 * 参数 targetFile
	 * 返回
	 */
	public static String readingCharSequence(File targetFile){
		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		String result = null;
		try {
			fileInputStream = new FileInputStream(targetFile);
			fileChannel = fileInputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			fileChannel.read(byteBuffer);
			byteBuffer.flip();
			byte[] b = new byte[byteBuffer.remaining()];
			byteBuffer.get(b);
			result = new String(b);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			IOUtil.close(fileChannel, fileInputStream);
		}
		
		return result;
	}

	/**
	 * 从文本文件中读取行
	 * 参数 file
	 * 返回
	 * 异常 IOException
	 */
	public static List<String> readingLineFormTextFile(File file, String encode) throws IOException{
		return readingLineFormTextFile(new FileInputStream(file), encode);
	}

	public static List<String> readingLineFormTextFile(InputStream in, String encode) throws IOException{
		List<String> lines = new ArrayList<String>();
		StringReader sr = null;
		BufferedReader br = null;
		try {
			String text = asString(in, encode);
			sr = new StringReader(text);
			br = new BufferedReader(sr);
			String s;
			while(( s = br.readLine()) != null){
				lines.add(s);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			IOUtil.close(sr, br);
		}
		
		return lines;
	}
	public static void main(String[] args) {
		File file = new File("e:\\zjh.txt");
		//writingCharSequence("张纪豪", file);
		System.out.println(readingCharSequence(file));
	}
}
