package com.itboy.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author wh
 * @date 2021年11月15日9:26
 */
@RequestMapping("/user")
@Controller
public class Usercontroller {
    /**
     * 跨服务器方式上传文件
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload03")
    private String fileupload03( MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传已执行");
        String path="http://localhost:8082/uploads/";
        String name = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        name = uuid + "_" + name;
        Client client = Client.create();
        WebResource webResource = client.resource(path + name);
        webResource.put(upload.getBytes());
        return "success";
    }
    /**
     * spring MVC方式文件上传
     *
     * @param request
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload02")
    private String fileupload02(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("spring MVC方式文件上传已执行");
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String name = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        name = uuid + "_" + name;
        upload.transferTo(new File(path, name));

        return "success";
    }

    /**
     * 传统方式文件上传
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload01")
    private String fileupload01(HttpServletRequest request) throws Exception {
        System.out.println("传统方式文件上传已执行");
        String path = request.getSession().getServletContext().getRealPath("/uploads").toUpperCase();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUplod = new ServletFileUpload(factory);
        List<FileItem> fileItems = fileUplod.parseRequest(request);
        for (FileItem items : fileItems) {
            if (items.isFormField()) {

            } else {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String name = items.getName();
                name = uuid + "_" + name;
                items.write(new File(path, name));
                items.delete();
            }
        }
        return "success";
    }
}
