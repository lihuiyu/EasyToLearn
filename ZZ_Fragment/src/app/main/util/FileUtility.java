package app.main.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
	private final static String FUCTION_NOTE_DIR = "ѧϰ�ĵ�";
	private final static String FUCTION_WRONG_DIR = "��������";
	private final static String FUCTION_CLASS_DIR = "���ñʼ�";

	private String rootPath;
	/**
	 * SDCard�洢��Ŀ¼��
	 */
	private String SDPath;
	/**
	 * ��ǰĿ¼
	 */
	private String previousPath;

	/**
	 * ������Ŀ�洢Ŀ¼
	 * 
	 */
	public FileUtility() {
		SDPath = Environment.getExternalStorageDirectory() + File.separator;
		rootPath = SDPath + "MySyllabus" + File.separator;
		previousPath = rootPath;
		/**
		 * �½���Ŀ�洢��Ŀ¼
		 */
		File file = new File(previousPath);
		file.mkdir();
	}

	public void reset() {
		previousPath = rootPath;
	}

	/**
	 * �õ��洢��Ŀ¼
	 * 
	 * @return ��Ŀ�洢��Ŀ¼
	 */
	public String getPath() {
		return this.previousPath;
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
		File file = new File(previousPath + dir + File.separator + name);
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
		previousPath = previousPath + dirname;
		File dir = new File(previousPath);
		dir.mkdir();
		previousPath = previousPath + File.separator;
		return dir;
	}

	public File createRootSubFolder(String subName) {
		this.reset();
		File file = createDirectory(subName);
		return file;
	}

	public File createPreSubFolder(String subName) {
		File file = createDirectory(subName);
		createDirectory(FUCTION_NOTE_DIR);
		Rollback();
		createDirectory(FUCTION_WRONG_DIR);
		Rollback();
		createDirectory(FUCTION_CLASS_DIR);
		return file;
	}

	/**
	 * ����Ŀ¼���ļ����������ļ�
	 * 
	 * @param name
	 *            �ļ���
	 * @return �����ļ�
	 */
	public File createFileFromName(String name) {
		File file = new File(previousPath + name);
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
	 * �ع���ǰ·������һ��
	 */
	public void Rollback() {
		String dirs[] = previousPath.split(File.separator);
		int len = dirs.length;
		String dir = dirs[0];
		for (int i = 1; i < len - 1; i++) {
			dir = dir + File.separator + dirs[i] + File.separator;
		}
		previousPath = dir;
	}

	/**
	 * ɾ����Ŀ¼��һ���Ӳ��ļ���
	 * 
	 * @param folderName
	 */
	public void deleteRootSubFolder(String folderName) {
		String deleteFolder = rootPath + folderName;
		deleteFile(deleteFolder);
		previousPath = rootPath;
		ArrayList<String> folder = getSubFolder();
		if (folder.size() > 0)
			previousPath = rootPath + folder.get(0);
	}

	/**
	 * ����ָ����·��ɾ���ļ���
	 * 
	 * @param path
	 *            �ļ�·��
	 */
	public void deleteFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		} else {
			file.delete();
		}
	}

	public ArrayList<String> getSubFolder() {
		ArrayList<String> ml = new ArrayList<String>();
		ml.clear();
		File file = new File(previousPath);
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			String[] dirs = f.getPath().split(File.separator);
			ml.add(dirs[dirs.length - 1]);
		}
		return ml;
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
	 * 
	 * @param name
	 *            ���ļ����ļ���
	 * @param is
	 *            ���ļ��Ķ�����
	 * @return ���ļ�
	 */
	public File writeFileToSdCard(String name, InputStream is) {
		File file = null;
		OutputStream os = null;

		try {
			// this.createDirectory(dir); // �������ļ�����Ŀ¼��
			file = this.createFileFromName(name); // �������ļ�
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
	 * 
	 * @return -true��Ƭ�洢�ɹ� -false��Ƭ�洢ʧ��
	 */
	@SuppressWarnings("static-access")
	public boolean savePhoto(Bitmap bitmap) {
		/**
		 * ���ݵ�ǰϵͳʱ��������Ƭ����
		 */
		String picture_name = new DateFormat().format("yyyyMMdd_hhmmss",
				Calendar.getInstance(Locale.CHINA)) + ".jpg";

		// createDirectory(photoDirectory);// �����洢Ŀ¼
		File file = createFileFromName(picture_name);// ������Ƭ�ļ�

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
	public boolean saveAudio(String origionPath) {
		/**
		 * ���ݵ�ǰϵͳʱ������¼������
		 */
		String audio_name = new DateFormat().format("yyyyMMdd_hhmmss",
				Calendar.getInstance(Locale.CHINA))
				+ ".3gpp";

		System.out.println(audio_name);
		Log.d("test", audio_name);
		InputStream is = readFileSDCard(new File(origionPath));// ����ԭ¼��������

		/**
		 * ����ԭ��¼������д���½�¼���ļ���
		 */
		if (is != null) {

			File file = writeFileToSdCard(audio_name, is);
			if (file != null) {
				System.out.println(file.getAbsolutePath());
				Log.d("test", file.getAbsolutePath());
				return true;
			} else
				return false;
		} else
			return false;

	}

	public boolean saveText(String data, String title) {
		String name = title + ".txt";
		File file = createFileFromName(name);
		BufferedWriter outStream = null;
		try {
			outStream = new BufferedWriter(new FileWriter(file));
			outStream.write(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}

	public String readText(String fileName) {
		File file = new File(previousPath + fileName);
		BufferedReader reader = null;
		String data = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp;
			data = reader.readLine();
			while ((temp = reader.readLine()) != null) {
				data = data + "\n" + temp;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return data;
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
