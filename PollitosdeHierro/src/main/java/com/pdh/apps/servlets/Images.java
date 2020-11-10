/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.servlets;

import com.pdh.apps.managed.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.SystemUtils;
import static org.apache.log4j.AsyncAppender.DEFAULT_BUFFER_SIZE;
import org.apache.log4j.Logger;

/**
 *
 * @author leaca
 */
@WebServlet(name = "Images", urlPatterns = {"/production/*"})
public class Images extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Images.class);
    private static final long serialVersionUID = 1L;
    private String imagePath = "";

    @Override
    public void init() throws ServletException {
        //init
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get requested image by path info.
        String requestedImage = request.getPathInfo();

        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        imagePath = "";
        if (SystemUtils.IS_OS_WINDOWS) {
            imagePath = Constants.WINDOWS;
        }
        imagePath = imagePath.concat("/opt/apps/webfiles" + request.getContextPath() + "/production/" + requestedImage);

        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(URLDecoder.decode(imagePath, "UTF-8"));

        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

        // Prepare streams.
        try (FileInputStream fileInputStream = new FileInputStream(image);
                BufferedInputStream input = new BufferedInputStream(fileInputStream, DEFAULT_BUFFER_SIZE);
                BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE)) {
            // Open streams.

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
