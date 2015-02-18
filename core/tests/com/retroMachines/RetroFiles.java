package com.retroMachines;

import java.io.File;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.headless.HeadlessFileHandle;
import com.badlogic.gdx.files.FileHandle;

public class RetroFiles implements Files {
	
	public static final String externalPath = System.getProperty("user.home") + "/";
	public static final String localPath = new File("").getAbsolutePath() + File.separator;

	@Override
	public FileHandle getFileHandle (String fileName, FileType type) {
		return new HeadlessFileHandle(fileName, type);
	}

	@Override
	public FileHandle classpath (String path) {
		return new HeadlessFileHandle(path, FileType.Classpath);
	}

	@Override
	public FileHandle internal (String path) {
		// bypassing the issue of headlessfile handle starting in the root.
		String betterPath = path;
		if (!path.contains("assets")) {
			betterPath = "assets/" + betterPath;
		}
		return new HeadlessFileHandle(betterPath, FileType.Internal);
	}

	@Override
	public FileHandle external (String path) {
		return new HeadlessFileHandle(path, FileType.External);
	}

	@Override
	public FileHandle absolute (String path) {
		return new HeadlessFileHandle(path, FileType.Absolute);
	}

	@Override
	public FileHandle local (String path) {
		return new HeadlessFileHandle(path, FileType.Local);
	}

	@Override
	public String getExternalStoragePath () {
		return externalPath;
	}

	@Override
	public boolean isExternalStorageAvailable () {
		return true;
	}

	@Override
	public String getLocalStoragePath () {
		return localPath;
	}

	@Override
	public boolean isLocalStorageAvailable () {
		return true;
	}
}
