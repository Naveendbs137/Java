package com.dhatvibs.util;

import com.azure.storage.blob.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class AzureUploadUtil {

    private final BlobContainerClient containerClient;

    public AzureUploadUtil(BlobContainerClient containerClient) {
        this.containerClient = containerClient;
    }

    public String upload(MultipartFile file, String folder) {
        try {
            String name = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            BlobClient blob = containerClient.getBlobClient(name);
            blob.upload(file.getInputStream(), file.getSize(), true);
            return blob.getBlobUrl();
        } catch (Exception e) {
            throw new RuntimeException("Azure upload failed");
        }
    }
}
