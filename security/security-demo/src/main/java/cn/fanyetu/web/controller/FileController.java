package cn.fanyetu.web.controller;

import cn.fanyetu.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhanghaonan
 * @date 2018/1/16
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "F:/";

    /**
     * 上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        // 这里存入fastDFS等
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");

        // 将传入的文件写入本地文件
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 下载
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/{id}")
    public void download(@PathVariable("id") String id, HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        // jdk1.7 将流写在try ()中，jdk会自动帮我们关闭，不需要写finally
        try (
                InputStream inputStream = new FileInputStream(new File(folder + id + ".txt"));
                OutputStream outputStream = response.getOutputStream();
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename-test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}
