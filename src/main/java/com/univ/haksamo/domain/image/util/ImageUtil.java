package com.univ.haksamo.domain.image.util;

public class ImageUtil {
    public static String splitUrlForGetImageName(String imageUrl) {
        return imageUrl.split("/")[3];
    }
}
