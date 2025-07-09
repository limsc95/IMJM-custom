package com.IMJM.common.cloud;

import java.io.InputStream;
import java.io.OutputStream;

public interface StorageService {
    void upload(String filePath, InputStream fileIn);
    void download(String filePath, OutputStream fileOut);
    void delete(String filePath);

    void deleteFolder(String prefix);
}
