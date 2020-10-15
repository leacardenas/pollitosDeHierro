/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.util;

/**
 *
 * @author ToursysDevelopers
 */
public final class Utils {


    public Utils() {
        //Constructor
    }

//    public static String convertImage(String image, String companyName) {
//        if (ToursysConstants.S3) {
//            return getS3UrlObject(image, companyName);
//        } else {
//            return "/production/" + companyName + "/Images/" + image;
//        }
//
//    }
//
//    public static String convertImageJasperReport(String image, String companyName) {
//        if (ToursysConstants.S3) {
//            return getS3UrlObject(image, companyName);
//        } else {
//            return generatedImageUrl(image, companyName, Boolean.TRUE);
//        }
//    }
//
//    public static String convertObjectUtils(String image) {
//        if (ToursysConstants.S3) {
//            return getS3UrlObject(image, null);
//        } else {
//            String destPath = "";
//            if (SystemUtils.IS_OS_WINDOWS) {
//                destPath = ToursysConstants.WINDOWS;
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
//        if (ToursysConstants.S3) {
//            String key_name = generatedImageUrl(fileName, companyName, Boolean.TRUE);
//            try {
//                S3.putObject(ToursysConstants.S3_BUCKET, key_name, Utils.createTempFile(uploadedFile));
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
//        if (ToursysConstants.S3) {
//            String key_name = generatedFileUrl(fileName, companyName, Boolean.TRUE);
//            try {
//                S3.putObject(ToursysConstants.S3_BUCKET, key_name, Utils.createTempFile(uploadedFile));
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
//        if (ToursysConstants.S3) {
//            String key_name = generatedFileUrl(fileName, companyName, true);
//            try {
//                return S3.getObject(ToursysConstants.S3_BUCKET, key_name).getObjectContent().getDelegateStream();
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
//        if (ToursysConstants.S3) {
//            String key_name = generatedImageUrl(fileName, companyName, true);
//            try {
//                return S3.getObject(ToursysConstants.S3_BUCKET, key_name).getObjectContent().getDelegateStream();
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
//        if (ToursysConstants.S3) {
//            return ToursysConstants.getS3_PATH() + "/" + companyName + "/" + ToursysConstants.FILES + "/" + (withFile ? fileName : "");
//        } else {
//            return generateFilesLocation(companyName, ToursysConstants.FILES, fileName, withFile);
//        }
//
//    }
//
//    private static String generatedImageUrl(String fileName, String companyName, Boolean withFile) {
//        if (ToursysConstants.S3) {
//            return ToursysConstants.getS3_PATH() + "/" + companyName + "/" + ToursysConstants.IMAGES + "/" + (withFile ? fileName : "");
//        } else {
//            return generateFilesLocation(companyName, ToursysConstants.IMAGES, fileName, withFile);
//        }
//    }
//
//    public static String generateFilesLocation(String companyName, String type, String fileName, Boolean withFile) {
//        String destPath = "";
//        if (SystemUtils.IS_OS_WINDOWS) {
//            destPath = ToursysConstants.WINDOWS;
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
//            key_name = ToursysConstants.S3_PATH + "utils/" + fileName;
//        }
//
//        try {
//            String url = S3.generatePresignedUrl(new GeneratePresignedUrlRequest(ToursysConstants.S3_BUCKET, key_name)
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
//        return FileUtils.getFile(generateFilesLocation(companyName, ToursysConstants.FILES, fileName, false), fileName);
//    }
//
//    public static String getDestination(String companyName) {
//        return generateFilesLocation(companyName, ToursysConstants.IMAGES, null, false);
//    }
//
//    public static void deleteFile(String fileName, String companyName) throws IOException {
//        if (ToursysConstants.S3) {
//            try {
//                S3.deleteObject(ToursysConstants.S3_BUCKET, generatedFileUrl(fileName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                S3.deleteObject(ToursysConstants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                S3.copyObject(ToursysConstants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE), ToursysConstants.S3_BUCKET, generatedImageUrl(newName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                S3.copyObject(ToursysConstants.S3_BUCKET, generatedImageUrl(fileName, originCompanyName, Boolean.TRUE), ToursysConstants.S3_BUCKET, generatedImageUrl(newName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                return S3.doesObjectExist(ToursysConstants.S3_BUCKET, generatedImageUrl(fileName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                return S3.doesObjectExist(ToursysConstants.S3_BUCKET, generatedFileUrl(fileName, companyName, Boolean.TRUE));
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
//        if (ToursysConstants.S3) {
//            try {
//                S3.copyObject(ToursysConstants.S3_BUCKET, generatedFileUrl(fileName, originCompanyName, Boolean.TRUE), ToursysConstants.S3_BUCKET, generatedFileUrl(newName, companyName, Boolean.TRUE));
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
//
//    public static String modifyHtmlForJasper(String text) {
//        if (text != null && !"".equals(text)) {
//
//            String str = text
//                    .replace("<strong>", "htmlstrong")
//                    .replace("</strong>", "htmlclosestrong")
//                    .replace("</p>", "htmlbr")
//                    .replace("<em>", "htmli")
//                    .replace("</em>", "htmcloseli")
//                    .replace("<sup>", "htmlsup")
//                    .replace("</sup>", "htmlclosesup")
//                    .replace("<sub>", "htmlsub")
//                    .replace("</sub>", "htmlclosesub")
//                    .replace("<li>", "htmlli")
//                    .replace("</li>", "htmlcloseli")
//                    .replace("<ul>", "htmlul")
//                    .replace("</ul>", "htmlcloseul")
//                    .replace("<br />", "htmlbr")
//                    .replace("&nbsp;", " ");
//            
//            Whitelist wl = Whitelist.none();
//            wl.addTags("a");
//            wl.addAttributes("a", "href");
//            wl.addProtocols("a", "href", "ftp", "http", "https", "mailto");
//
//            str = Jsoup.clean(str, wl);
//
//            str = str
//                    .replace("htmlstrong", "<b>")
//                    .replace("htmlclosestrong", "</b>")
//                    .replace("htmlbr", "<br />")
//                    .replace("htmli", "<i>")
//                    .replace("htmcloseli", "</i>")
//                    .replace("htmlsup", "<sup>")
//                    .replace("htmlclosesup", "</sup>")
//                    .replace("htmlsub", "<sub>")
//                    .replace("htmlclosesub", "</sub>")
//                    .replace("htmlli", "<li>")
//                    .replace("htmlcloseli", "</li>")
//                    .replace("htmlul", "<ul>")
//                    .replace("htmlcloseul", "</ul>");
//
//            return str;
//        }
//        return null;
//    }
//
//    public static boolean uploadImageFromWetu(File tempFile, String companyName, String fileName) throws IOException {
//        String key_name = generatedImageUrl(fileName, companyName, Boolean.TRUE);
//        try {
//            S3.putObject(ToursysConstants.S3_BUCKET, key_name, tempFile);
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            return false;
//        } catch (IllegalArgumentException e) {
//            JsfUtil.addErrorMessage(JsfUtil.getMessage("ERROR.UPLOAD.FILE.LENGTH.NAME"));
//            return false;
//        }
//        return true;
//    }
//
//    public static String AWSTranslate(String text, String languageFrom, String languageTo) {
//        String translatedText = "";
//        try {
//            AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
//            AmazonTranslate translate = AmazonTranslateClient.builder()
//                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
//                    .withRegion(REGION)
//                    .build();
//            TranslateTextRequest request = new TranslateTextRequest()
//                    .withText(text)
//                    .withSourceLanguageCode(languageFrom)
//                    .withTargetLanguageCode(languageTo);
//            TranslateTextResult result = translate.translateText(request);
////            System.out.println(result.getTranslatedText());
//            translatedText = result.getTranslatedText();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return translatedText;
//    }
}
