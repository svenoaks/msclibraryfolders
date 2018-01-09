package com.smp.msclibraryfolders.utils;

import java.io.File;

public class FileMethods
{
	private static final char EXTENSION_SEPARATOR = '.';
	private static final char DIRECTORY_SEPARATOR = File.separatorChar;

	public static boolean isAudioForMetadata(String extension)
	{
		extension = extension.toLowerCase();
		return extension.equals("ogg") || extension.equals("mp3") ||
				extension.equals("aac") || extension.equals("flac")
				|| extension.equals("m4a") || extension.equals("wma")
				|| extension.equals("asf") || extension.equals("alac") || extension.equals("opus") ||
				extension.equals("wv") || extension.equals("aiff")
				|| extension.equals("amr");
	}
	public static String getExtension(String filename)
	{
		if (filename == null)
		{
			return null;
		}

		int index = indexOfExtension(filename);

		if (index == -1)
		{
			return filename;
		}
		else
		{
			return filename.substring(index);
		}
	}

	public static String removeExtension(String filename)
	{
		if (filename == null)
		{
			return null;
		}

		int index = indexOfExtension(filename);

		if (index == -1)
		{
			return filename;
		}
		else
		{
			return filename.substring(0, index);
		}
	}

	public static int indexOfExtension(String filename)
	{
		if (filename == null)
		{
			return -1;
		}

		// Check that no directory separator appears after the
		// EXTENSION_SEPARATOR
		int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);

		int lastDirSeparator = filename.lastIndexOf(DIRECTORY_SEPARATOR);

		if (lastDirSeparator > extensionPos)
		{
			return -1;
		}

		return extensionPos;
	}

	public static File checkFileName(File fileIn, String fileExtension)
	{
		int n = 1;
		File fileOut = fileIn;
		while (fileOut.isFile())
		{
			fileOut = new File(removeExtension(fileIn.getAbsolutePath()) + "-"
					+ n + fileExtension);
			++n;
		}
		return fileOut;
	}
}
