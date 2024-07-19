package com.distribuidoraferreira.backend.api_tools;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.distribuidoraferreira.backend.dtos.ImageResponse;
import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.upload.FileUploader;
import com.uploadcare.upload.UploadFailureException;
import com.uploadcare.upload.Uploader;

@AutoConfiguration
public class Upload_Helper {

    // UTILS
    // Project project = client.getProject();
    // Project.Collaborator owner = project.getOwner();
    // List<URI> published = new ArrayList<URI>();
    Client client;

    public Client getClient() {
        if (this.client != null) {
            return this.client;
        }
        return new Client(Enviroments.UPLOADCARE_PUBLIC_KEY2, Enviroments.UPLOADCARE_SECRET_KEY2);
    }

    public boolean delete(String fileName) {
        Client client = new Client(Enviroments.UPLOADCARE_PUBLIC_KEY2, Enviroments.UPLOADCARE_SECRET_KEY2);
        Iterable<File> files = client.getFiles().asIterable();
        boolean deleted = false;
        for (File file : files) {
            if (file.getOriginalFilename().equals(fileName)) {
                file.delete();
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean deleteById(String fileID) {
        Client client = getClient();
        Iterable<File> files = client.getFiles().asIterable();
        boolean deleted = false;
        for (File file : files) {
            if (file.getFileId().equals(fileID)) {
                file.delete();
            }
        }
        return deleted;
    }

    public boolean uploadImage(String pathFile) {

        Client client = getClient();
        java.io.File file = new java.io.File(pathFile);
        Uploader uploader = new FileUploader(client, file);
        boolean uploaded = false;
        try {
            File fileResponse = uploader.upload().save();
            System.out.println(fileResponse.getOriginalFileUrl());
            uploaded = true;
        } catch (UploadFailureException e) {
            System.out.println("Upload failed :(");
            System.out.println(e.getMessage());
        }
        return uploaded;
    }

    public ImageResponse uploadImage(java.io.File file) {

        Client client = getClient();
        Uploader uploader = new FileUploader(client, file);
        File fileResponse;

        try {
            fileResponse = uploader.upload().save();
            return new ImageResponse(fileResponse.getOriginalFileUrl().toString(), fileResponse.getFileId());
        } catch (UploadFailureException e) {
            System.out.println("Upload failed :(");
            System.out.println(e.getMessage());
        }
        return null;
    }

}