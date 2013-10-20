package app.main.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * �ļ�������,�����ļ�Ŀ¼�Ĵ������ļ��Ĵ���ɾ�����ļ��Ķ�д���ļ��ı���ȹ��ܡ�
 * 
 * @author hzy
 * 
 */
public class FileUtility {

	/**
	 * SDCard�洢��Ŀ¼��
	 */
	private String SDPATH;
	/**
	 * ��Ŀ�洢Ŀ¼
	 */
	private String SAVE_PATH;

	/**
	 * ������Ŀ�洢Ŀ¼
	 * 
	 * @param root
	 *            ��Ŀ�洢��Ŀ¼��
	 */
	public FileUtility(String root) {
		SDPATH = Environment.getExternalStorageDirectory() + File.separator;
		SAVE_PATH = SDPATH + root + File.separator;
		/**
		 * �½���Ŀ�洢��Ŀ¼
		 */
		File file = new File(SAVE_PATH);
		file.mkdir();
	}

	/**
	 * �õ��洢��Ŀ¼
	 * 
	 * @return ��Ŀ�洢��Ŀ¼
	 */
	public String getPath() {
		return this.SAVE_PATH;
	}

	/**
	 * ͨ���ļ�Ŀ¼���ļ����ж��ļ��Ƿ���ڡ�
	 * 
	 * @param dir
	 *            �ļ�Ŀ¼
	 * @param name
	 *            �ļ���
	 * @return -true �ļ��Ѵ��� -false �ļ�������
	 */
	@SuppressWarnings("unused")
	private boolean isFileExist(String dir, String name) {
		File file = new File(SAVE_PATH + dir + File.separator + name);
		return file.exists();
	}

	/**
	 * ����Ŀ¼�������ļ�Ŀ¼
	 * 
	 * @param dirname
	 *            Ŀ¼��
	 * @return ����Ŀ¼�ļ�
	 */
	public File createDirectory(String dirname) {
		File dir = new File(SAVE_PATH + dirname);
		dir.mkdir();
		return dir;
	}

	/**
	 * ����Ŀ¼���ļ����������ļ�
	 * 
	 * @param dir
	 *            Ŀ¼��
	 * @param name
	 *            �ļ���
	 * @return �����ļ�
	 */
	public File createFileFromName(String dir, String name) {
		File file = new File(SAVE_PATH + dir + File.separator + name);
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * ����ָ����·��ɾ���ļ���
	 * 
	 * @param path
	 *            �ļ�·��
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		} else {
			file.delete();
		}
	}

	/**
	 * ��ȡ�ļ�����,�õ��ļ���ȡ��
	 * 
	 * @param file
	 *            ��ȡ���ļ�
	 * @return �ļ�������
	 */

	public InputStream readFileSDCard(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			return is;
		}

	}

	/**
	 * �����ṩ���ļ���������������д�����ļ�
	 * 
	 * @param dir
	 *            ���ļ����ļ�Ŀ¼
	 * @param name
	 *            ���ļ����ļ���
	 * @param is
	 *            ���ļ��Ķ�����
	 * @return ���ļ�
	 */
	public File writeFileToSdCard(String dir, String name, InputStream is) {
		File file = null;
		OutputStream os = null;

		try {
			// this.createDirectory(dir); // �������ļ�����Ŀ¼��
			file = this.createFileFromName(dir, name); // �������ļ�
			os = new FileOutputStream(file);

			/**
			 * д�����ļ�����
			 */
			int length = is.available();
			byte buffer[] = new byte[length];
			int temp = 0;
			while ((temp = is.read(buffer)) != -1) {
				os.write(buffer, 0, temp);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
					os = null;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}

		}

		return file;

	}

	/**
	 * ������Ƭ�����ش洢Ŀ¼��
	 * 
	 * @param bitmap
	 *            ��Ƭ���ڴ��еı������
	 * @param photoDirectory
	 *            ��Ŀ��Ƭ�洢Ŀ¼
	 * @return -true��Ƭ�洢�ɹ� -false��Ƭ�洢ʧ��
	 */
	@SuppressWarnings("static-access")
	public boolean savePhoto(Bitmap bitmap, String photoDirectory) {
		/**
		 * ���ݵ�ǰϵͳʱ��������Ƭ����
		 */
		String picture_name = new DateFormat().format("yyyyMMdd_hhmmss",
				Calendar.getInstance(Locale.CHINA)) + ".jpg";

		// createDirectory(photoDirectory);// �����洢Ŀ¼
		File file = createFileFromName(photoDirectory, picture_name);// ������Ƭ�ļ�

		System.out.println(file.getAbsolutePath());

		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			/** ����Ƭ����������������У�������ļ��� */
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}

		}
		return true;

	}

	/**
	 * ��ԭ¼����Ƶ���浽��Ŀ�洢Ŀ¼��
	 * 
	 * @param origionPath
	 *            ԭ¼����Ƶ·��
	 * @param audioDirName
	 *            ��¼����Ƶ��¼���洢ʧ��
	 */
	@SuppressWarnings("static-access")
	public boolean saveAudio(String origionPath, String audioDirName) {
		/**
		 * ���ݵ�ǰϵͳʱ������¼������
		 */
		String audio_name = new DateFormat().format("yyyyMMdd_hhmmss",
				Calendar.getInstance(Locale.CHINA))
				+ ".3gpp";

		System.out.println(audio_name);
		Log.d("test", audio_name);
		createDirectory(audioDirName);// �����洢Ŀ¼
		InputStream is = readFileSDCard(new File(origionPath));// ����ԭ¼��������

		/**
		 * ����ԭ��¼������д���½�¼���ļ���
		 */
		if (is != null) {

			File file = writeFileToSdCard(audioDirName, audio_name, is);
			if (file != null) {
				System.out.println(file.getAbsolutePath());
				Log.d("test", file.getAbsolutePath());
				return true;
			} else
				return false;
		} else
			return false;

	}

	/**
	 * ����ֻ�ƽ��SDCard�Ƿ����
	 * 
	 * @return -true SDCard���� -false SDCard������
	 */
	public boolean isSDCardUseful() {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<String> getSubfolder() {
		ArrayList<String> ml = new ArrayList<String>();
		ml.clear();
		File file = new File(SAVE_PATH);
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			ml.add(f.getPath());
		}
		return ml;
	}

	public ArrayList<String> getImageList(String dir) {
		ArrayList<String> ml = new ArrayList<String>();
		ml.clear();
		File file = new File(dir);
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (isImageFile(f.getPath()))
				ml.add(f.getPath());
		}
		return ml;
	}

	public boolean isImageFile(String name) {
		boolean b = false;
		String end = name.substring(name.lastIndexOf(".") + 1, name.length())
				.toLowerCase();
		if (end.equals("jpg") || end.equals("png") || end.equals("gif")
				|| end.equals("jpeg") || end.equals("bmp"))
			b = true;
		return b;
	}

	public ArrayList<String> getAudioList(String dir) {
		ArrayList<String> ml = new ArrayList<String>();
		ml.clear();
		File file = new File(dir);
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (isAudioFile(f.getPath()))
				ml.add(f.getPath());
		}
		return ml;
	}

	public boolean isAudioFile(String name) {
		boolean b = false;
		String end = name.substring(name.lastIndexOf(".") + 1, name.length())
				.toLowerCase();
		if (end.equals("3gpp") || end.equals("mp4") || end.equals("mp3")
				|| end.equals("wma"))
			b = true;
		return b;
	}

}