/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.pdh.apps.managed.Constants;
import com.pdh.apps.managed.UserLoginManaged;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author leaca
 */
public final class Utils {

//    private static final AmazonS3 S3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(Constants.S3_ACCESS_KEY, Constants.S3_SECRET_KEY))).withRegion(Regions.US_EAST_1).withPathStyleAccessEnabled(true).build();
//    private static final String REGION = "us-east-1";
//
//    public Utils() {
//        //Constructor
//    }
//
//    public static String convertImage(String image, String companyName) {
//        if (Constants.S3) {
//            return getS3UrlObject(image, companyName);
//        } else {
//            return "/production/" + companyName + "/Images/" + image;
//        }
//
//    }
//
//    public static String convertImageJasperReport(String image, String companyName) {
//        if (Constants.S3) {
//            return getS3UrlObject(image, companyName);
//        } else {
//            return generatedImageUrl(image, companyName, Boolean.TRUE);
//        }
//    }
//
//    public static String convertObjectUtils(String image) {
//        if (Constants.S3) {
//            return getS3UrlObject(image, null);
//        } else {
//            String destPath = "";
//            if (SystemUtils.IS_OS_WINDOWS) {
//                destPath = Constants.WINDOWS;
//            }
//            return destPath.concat("/opt/apps/webfiles/" + JsfUtil.getContextPath() + "/production/utils/" + image);
//        }
//
//    }
//
//    public static String sha1Convert(String password) {
//        try {
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//            crypt.reset();
//            crypt.update(password.getBytes("UTF-8"));
//            return byteToHex(crypt.digest());
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
//            Logger.getLogger(UserLoginManaged.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    //This method is used for the Hash
//    private static String byteToHex(final byte[] hash) {
//        String result;
//        try (Formatter formatter = new Formatter()) {
//            for (byte b : hash) {
//                formatter.format("%02x", b);
//            }
//            result = formatter.toString();
//        }
//        return result;
//    }
//
//    public static boolean uploadImage(UploadedFile uploadedFile, String companyName, String fileName) throws IOException {
//        if (Constants.S3) {
//            String key_name = generatedImageUrl(fileName, companyName, Boolean.TRUE);
//            try {
//                S3.putObject(Constants.S3_BUCKET, key_name, Utils.createTempFile(uploadedFile));
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return false;
//            } catch (IllegalArgumentException e) {
//                JsfUtil.addErrorMessage(JsfUtil.getMessage("ERROR.UPLOAD.FILE.LENGTH.NAME"));
//                return false;
//            }
//            return true;
//        } else {
//            InputStream inputStream = uploadedFile.getInputstream();
//            File destFile = new File(generatedImageUrl(fileName, companyName, Boolean.TRUE));
//            FileUtils.copyInputStreamToFile(inputStream, destFile);
//            return true;
//        }
//    }
//
//    public static boolean uploadFile(UploadedFile uploadedFile, String companyName, String fileName) throws IOException {
//        if (Constants.S3) {
//            String key_name = generatedFileUrl(fileName, companyName, Boolean.TRUE);
//            try {
//                S3.putObject(Constants.S3_BUCKET, key_name, Utils.createTempFile(uploadedFile));
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return false;
//            } catch (IllegalArgumentException e) {
//                JsfUtil.addErrorMessage(JsfUtil.getMessage("ERROR.UPLOAD.FILE.LENGTH.NAME"));
//                return false;
//            }
//            return true;
//        } else {
//            InputStream inputStream = uploadedFile.getInputstream();
//            File destFile = new File(generatedFileUrl(fileName, companyName, Boolean.TRUE));
//            FileUtils.copyInputStreamToFile(inputStream, destFile);
//            return true;
//        }
//    }
//
//    public static File createTempFile(UploadedFile uploadedFile) throws FileNotFoundException, IOException {
//        if (uploadedFile != null) {
//            File tempFile = File.createTempFile(FilenameUtils.getBaseName(uploadedFile.getFileName()), "." + FilenameUtils.getExtension(uploadedFile.getFileName()));
//            tempFile.deleteOnExit();
//            FileOutputStream out = new FileOutputStream(tempFile);
//            IOUtils.copy(uploadedFile.getInputstream(), out);
//            return tempFile;
//        }
//        return null;
//    }
//
//    public static StreamedContent getFile(String fileName, String companyName) throws IOException {
//        return new DefaultStreamedContent(getFileInputStream(fileName, companyName), "", fileName);
//    }
//
//    public static StreamedContent getImage(String imageName, String companyName) throws IOException {
//        return new DefaultStreamedContent(getImageInputStream(imageName, companyName), "", imageName);
//    }
//
//    public static InputStream getFileInputStream(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            String key_name = generatedFileUrl(fileName, companyName, true);
//            try {
//                return S3.getObject(Constants.S3_BUCKET, key_name).getObjectContent().getDelegateStream();
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return null;
//            }
//        } else {
//            File file = FileUtils.getFile(generatedFileUrl(fileName, companyName, false), fileName);
//            if (file.exists()) {
//                InputStream stream = new FileInputStream(file);
//                return stream;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    public static InputStream getImageInputStream(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            String key_name = generatedImageUrl(fileName, companyName, true);
//            try {
//                return S3.getObject(Constants.S3_BUCKET, key_name).getObjectContent().getDelegateStream();
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return null;
//            }
//        } else {
//            File file = FileUtils.getFile(generatedImageUrl(fileName, companyName, false), fileName);
//            InputStream stream = new FileInputStream(file);
//            return stream;
//        }
//    }
//
//    private static String generatedFileUrl(String fileName, String companyName, Boolean withFile) {
//        if (Constants.S3) {
//            return Constants.getS3_PATH() + "/" + companyName + "/" + Constants.FILES + "/" + (withFile ? fileName : "");
//        } else {
//            return generateFilesLocation(companyName, Constants.FILES, fileName, withFile);
//        }
//
//    }
//
//    private static String generatedImageUrl(String fileName, String companyName, Boolean withFile) {
//        if (Constants.S3) {
//            return Constants.getS3_PATH() + "/" + companyName + "/" + Constants.IMAGES + "/" + (withFile ? fileName : "");
//        } else {
//            return generateFilesLocation(companyName, Constants.IMAGES, fileName, withFile);
//        }
//    }
//
//    public static String generateFilesLocation(String companyName, String type, String fileName, Boolean withFile) {
//        String destPath = "";
//        if (SystemUtils.IS_OS_WINDOWS) {
//            destPath = Constants.WINDOWS;
//        }
//
//        destPath += "/opt/apps/webfiles/" + JsfUtil.getContextPath() + "/production/" + companyName + "/" + type + "/" + (withFile ? fileName : "");
//        return destPath;
//    }
//
//    public static String getS3UrlObject(String fileName, String companyName) {
//        // Set the presigned URL to expire after one hour.
//        java.util.Date expiration = new java.util.Date();
//        long expTimeMillis = System.currentTimeMillis() + 3600000;
//        expiration.setTime(expTimeMillis);
//
//        String key_name;
//        if (companyName != null) {
//            key_name = generatedImageUrl(fileName, companyName, Boolean.TRUE);
//        } else {
//            key_name = Constants.S3_PATH + "utils/" + fileName;
//        }
//
//        try {
//            String url = S3.generatePresignedUrl(new GeneratePresignedUrlRequest(Constants.S3_BUCKET, key_name)
//                    .withMethod(HttpMethod.GET)
//                    .withExpiration(expiration)).toString();
//            return url;
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            return "";
//        }
//    }
//
//    public static File getFileEmail(String fileName, String companyName) throws IOException {
//        return FileUtils.getFile(generateFilesLocation(companyName, Constants.FILES, fileName, false), fileName);
//    }
//
//    public static String getDestination(String companyName) {
//        return generateFilesLocation(companyName, Constants.IMAGES, null, false);
//    }
//
//    public static void deleteFile(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            try {
//                S3.deleteObject(Constants.S3_BUCKET, generatedFileUrl(fileName, companyName, Boolean.TRUE));
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//            }
//        } else {
//            Path path = Paths.get(generatedFileUrl(fileName, companyName, Boolean.FALSE) + fileName);
//            Files.deleteIfExists(path);
//        }
//    }
//
//    public static void deleteImage(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            try {
//                S3.deleteObject(Constants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE));
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//            }
//        } else {
//            Path path = Paths.get(generatedImageUrl(fileName, companyName, Boolean.FALSE) + fileName);
//            Files.deleteIfExists(path);
//        }
//    }
//
//    public static String copyImage(String fileName, String companyName, String concatName) throws IOException {
//        String newName = concatName + "_" + fileName;
//        if (Constants.S3) {
//            try {
//                S3.copyObject(Constants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE), Constants.S3_BUCKET, generatedImageUrl(newName, companyName, Boolean.TRUE));
//                return newName;
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return null;
//            }
//        } else {
//            String destPath = generatedImageUrl(fileName, companyName, Boolean.FALSE);
//
//            File file = FileUtils.getFile(destPath, fileName);
//            File file2 = new File(destPath + newName);
//            if (file.exists()) {
//                FileUtils.copyFile(file, file2);
//                return newName;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    public static String copyImageTransfer(String fileName, String originCompanyName, String companyName, String concatName) throws IOException {
//        String newName = concatName + "_" + fileName;
//        if (Constants.S3) {
//            try {
//                S3.copyObject(Constants.S3_BUCKET, generatedImageUrl(fileName, originCompanyName, Boolean.TRUE), Constants.S3_BUCKET, generatedImageUrl(newName, companyName, Boolean.TRUE));
//                return newName;
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return null;
//            }
//        } else {
//            String originPath = generatedImageUrl(fileName, originCompanyName, Boolean.FALSE);
//            String destinationPath = generatedImageUrl(newName, companyName, Boolean.FALSE);
//            File file = FileUtils.getFile(originPath, fileName);
//            File file2 = new File(destinationPath + newName);
//            if (file.exists()) {
//                FileUtils.copyFile(file, file2);
//                return newName;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    public static Boolean findImage(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            try {
//                return S3.doesObjectExist(Constants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE));
//            } catch (SdkClientException e) {
//                System.err.println(e.getCause());
//                return false;
//            }
//        } else {
//            File file = FileUtils.getFile(generatedImageUrl(fileName, companyName, Boolean.FALSE), fileName);
//            return file.exists();
//        }
//    }
//
//    public static Boolean findFile(String fileName, String companyName) throws IOException {
//        if (Constants.S3) {
//            try {
//                return S3.doesObjectExist(Constants.S3_BUCKET, generatedFileUrl(fileName, companyName, Boolean.TRUE));
//            } catch (SdkClientException e) {
//                System.err.println(e.getCause());
//                return false;
//            }
//        } else {
//            File file = FileUtils.getFile(generatedFileUrl(fileName, companyName, Boolean.FALSE), fileName);
//            return file.exists();
//        }
//    }
//
//    public static String copyFileTransfer(String fileName, String originCompanyName, String companyName, String concatName) throws IOException {
//        String newName = concatName + "_" + fileName;
//        if (Constants.S3) {
//            try {
//                S3.copyObject(Constants.S3_BUCKET, generatedFileUrl(fileName, originCompanyName, Boolean.TRUE), Constants.S3_BUCKET, generatedFileUrl(newName, companyName, Boolean.TRUE));
//                return newName;
//            } catch (AmazonServiceException e) {
//                System.err.println(e.getErrorMessage());
//                return null;
//            }
//        } else {
//            String originPath = generatedFileUrl(fileName, originCompanyName, Boolean.FALSE);
//            String destinationPath = generatedFileUrl(newName, companyName, Boolean.FALSE);
//            File file = FileUtils.getFile(originPath, fileName);
//            File file2 = new File(destinationPath + newName);
//            if (file.exists()) {
//                FileUtils.copyFile(file, file2);
//                return newName;
//            } else {
//                return null;
//            }
//        }
//    }

}
