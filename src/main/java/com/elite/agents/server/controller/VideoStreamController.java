package com.elite.agents.server.controller;

import com.elite.agents.server.service.VideoStreamService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoStreamController {

    private final VideoStreamService videoStreamService;

    @Autowired
    public VideoStreamController(
            VideoStreamService videoStreamService) {
        this.videoStreamService = videoStreamService;
    }

    @GetMapping("/stream/{fileType}/{fileName}")
    public Mono<ResponseEntity<byte[]>> streamVideo(
            @RequestHeader(value = "Range", required = false)
            String httpRangeList,
            @PathVariable("fileType")
            String fileType,
            @PathVariable("fileName")
            String fileName) {
        return Mono.just(videoStreamService.prepareContent(fileName, fileType, httpRangeList));
    }

    @GetMapping("/stream/url/{fileType}")
    public Mono<ResponseEntity<byte[]>> streamVideoFromUrl(
            @RequestHeader(value = "Range", required = false)
            String httpRangeList,
            @PathVariable("fileType")
            String fileType,
            @RequestParam("url")
            String url) throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString(), "." + fileType);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }

        return Mono.just(videoStreamService.prepareContent(file.getAbsolutePath(), fileType, httpRangeList));
    }
}
