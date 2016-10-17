package com.avancial.socle.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils extends org.apache.commons.io.FileUtils {
	public static void copyFileAndDeleteWhiteSpacesLines(File fin, File fout) throws IOException {

		// Open and input and output stream
		FileInputStream fis = new FileInputStream(fin);
		FileOutputStream fos = new FileOutputStream(fout);

		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

		// The pattern matches control characters
		Pattern p = Pattern.compile("^\\s*$");
		Matcher m = p.matcher("");
		String aLine = null;
		while ((aLine = in.readLine()) != null) {
			m.reset(aLine);
			// Replaces control characters with an empty
			// string.
			if (!m.matches()) {
				out.write(aLine);
				out.newLine();
			}
		}
		in.close();
		out.close();

	}

	public static void mkDir(String dir) throws IOException {
		File directory = new File(dir);
		if (directory.isDirectory() == false)
			directory.mkdir();
	}

	/**
	 * @author julien.tourlonias
	 * @see Cr�er un r�pertoire, avec les r�pertoire parent si il y en a. . Il
	 *      faut noter que si la cr�ation �choue il est possible que certain
	 *      r�pertoires parent aient �t� cr�es.
	 * @return true et seulement si le r�pertoire � �t� cr�e, avec les
	 *         r�pertoire parent necessaire, false sinon.
	 */
	public static Boolean mkDirs(String dir) throws IOException {
		File directory = new File(dir);
		if (directory.isDirectory() == false)
			return directory.mkdirs();
		else
			return false;
	}

	public static Boolean mkDirBooleanEnRetour(String dir) throws IOException {
		File directory = new File(dir);
		if (directory.isDirectory() == false) {
			directory.mkdir();
			return true;
		} else {
			return false;
		}
	}

	public static String extractFileName(String nomDuFichierSource) throws Exception {
		File file = new File(nomDuFichierSource);
		return file.getName();
	}

	public static String extractFilePath(String nomDuFichierSource) throws Exception {
		File file = new File(nomDuFichierSource);
		return file.getParent();
	}

	public static void renameFile(String nomDuFichierSource, String nomDuFichierDestination) throws Exception {
		File file = new File(nomDuFichierSource);
		file.renameTo(new File(nomDuFichierDestination));
	}

	public static boolean existFile(String nomDuFichierSource) throws Exception {
		return new File(nomDuFichierSource).exists();
	}

	public static boolean canWriteFile(String nomDuFichierSource) throws Exception {
		return new File(nomDuFichierSource).canWrite();
	}

	public static boolean canReadFile(String nomDuFichierSource) throws Exception {
		return new File(nomDuFichierSource).canRead();
	}

	public static boolean checksumFichierToMd5(String fichier, String fichierMd5, String encodageFichier)
			throws Exception {
		return generateFileMd5(fichier).equalsIgnoreCase(lectureFileMd5(fichierMd5, encodageFichier));
	}

	public static String generateFileMd5(String fichier) throws Exception {
		return generateFileMd5(new File(fichier));
	}

	public static String generateFileMd5(File fichier) throws Exception {
		String output = null;
		MessageDigest digest = null;

		InputStream is = null;
		byte[] buffer = new byte[1024];
		int read = 0;

		digest = MessageDigest.getInstance("MD5");
		is = new FileInputStream(fichier);

		while ((read = is.read(buffer)) > 0) {
			digest.update(buffer, 0, read);
		}
		byte[] md5sum = digest.digest();
		BigInteger bigInt = new BigInteger(1, md5sum);
		output = bigInt.toString(16);

		// -- formatage sur 32 char
		output = StringUtils.leftPad(output, 32, "0");

		is.close();

		return output;
	}

	public static String lectureFileMd5(String fichier, String encodageFichier) throws Exception {
		return lectureFileMd5(new File(fichier), encodageFichier);
	}

	public static String lectureFileMd5(File fichier, String encodageFichier) throws Exception {
		String output = null;

		InputStream inBytes = new FileInputStream(fichier);
		Reader inChars = new InputStreamReader(inBytes, encodageFichier);
		BufferedReader in = new BufferedReader(inChars);

		output = in.readLine();
		in.close();

		return output;
	}

	/**
	 * @param pieceJointeFaqQuestion
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.delete()) {
			file.deleteOnExit();
		}
	}

	// save uploaded file to new location
	public static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}