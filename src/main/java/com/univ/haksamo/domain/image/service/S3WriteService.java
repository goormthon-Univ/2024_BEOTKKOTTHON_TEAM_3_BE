package com.univ.haksamo.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class S3WriteService {
    private final S3Operator s3Operator;
    private static final String DIR_NAME = "HAKSAMO_DIR";

    public String saveImageToS3(MultipartFile image) throws IOException {
        return s3Operator.upload(image, DIR_NAME);
    }
}
