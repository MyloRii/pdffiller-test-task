package com.pdffiller.autotests.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final public class FileUtils {

    private static int numberOfFiles = 0;
    private final int MAX_TIMEOUT_TO_DOWNLOAD_FILE = 60; // Maximum time limit to download a file (in seconds)
    private final String TEST_FILE_NAME = "TestFile";
    private final String PDF_EXTENSION = ".pdf";
    private final String DOWNLOAD_FOLDER = getDownloadFolderLocation();

    public void waitForFileDownload(String fileName) {
        Path pathToFile = Paths.get(DOWNLOAD_FOLDER, fileName);
        try {
            waitUntilDownloadFinishes(pathToFile, MAX_TIMEOUT_TO_DOWNLOAD_FILE);
            Files.move(pathToFile, pathToFile.resolveSibling(TEST_FILE_NAME + numberOfFiles + PDF_EXTENSION));
            numberOfFiles++;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTestFilesInDownloadsFolder() {
        File dir = new File(DOWNLOAD_FOLDER);
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(TEST_FILE_NAME));
        if (files.length > 0) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public boolean checkIfTwoTestFilesHaveDifferentLength() {
        File dir = new File(DOWNLOAD_FOLDER);
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(TEST_FILE_NAME));
        return files.length == 2 && files[0].length() != files[1].length();
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



