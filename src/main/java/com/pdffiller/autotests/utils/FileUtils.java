package com.pdffiller.autotests.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final public class FileUtils {

    private final int MAX_TIMEOUT_TO_DOWNLOAD_FILE = 60; // Maximum time limit to download a file (in seconds)
    private final String DOWNLOAD_FOLDER = getDownloadFolderLocation();

    public void waitForFileDownload(String fileName) {
        Path pathToFile = Paths.get(DOWNLOAD_FOLDER, fileName);
        try {
            waitUntilDownloadFinishes(pathToFile, MAX_TIMEOUT_TO_DOWNLOAD_FILE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPDFfilesInDownloadsFolder() {
        File dir = new File(DOWNLOAD_FOLDER);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".pdf"));
        if (files.length > 0) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public boolean checkIfTwoFilesHaveDifferentLength(String partialNameOfFile) {
        File dir = new File(DOWNLOAD_FOLDER);
        File[] files = dir.listFiles((dir1, name) -> name.contains(partialNameOfFile));
        if (files.length == 2) {
            return files[0].length() != files[1].length();
        }
        return false;
    }

    private String getDownloadFolderLocation() {
        String downloadLocation = Paths.get(System.getProperty("user.home"), "Downloads").toString();
        return downloadLocation.trim();
    }

    private void waitUntilDownloadFinishes(Path pathToFileThatDownloads, int timeout) throws InterruptedException {
        int originalTimeoutCounter = timeout;
        while (!isDownloadFinished(pathToFileThatDownloads) && timeout > 0) {
            Thread.sleep(1000L);
            timeout--;
        }
        if (timeout == 0) {
            System.out.println(String.format("File \"%s\" has not been downloaded during %d seconds.",
                    pathToFileThatDownloads, originalTimeoutCounter));
        }
    }

    private boolean isDownloadFinished(Path pathToFileThatDownloads) {
        File downloadedFile = new File(pathToFileThatDownloads.toString());
        Path pathToPartiallyDownloadedFileInChrome = Paths.get(pathToFileThatDownloads + ".crdownload");
        return Files.exists(pathToFileThatDownloads) &&
                !Files.exists(pathToPartiallyDownloadedFileInChrome) &&
                downloadedFile.canWrite();
    }
}



