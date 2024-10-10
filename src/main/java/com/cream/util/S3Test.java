package com.cream.util;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // AWS 자격 증명
        final String accessKey =  System.getenv("AWS_ACCESS_KEY_ID"); // 여기에 액세스 키 입력
        final String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");	 // 여기에 비밀 키 입력
        final String bucketName = "kosta-286-cream"; // S3 버킷 이름 입력
        final String filePath = "C:\\Edu/윤성바오.jpg"; // 업로드할 파일 경로 입력
        final String keyName = "img/윤성바오1.jpg"; // S3에서 사용할 파일 이름

        // S3 클라이언트 생성
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2) // 한국 리전
                .build();

        // 파일 업로드
		/*
		 * try { File file = new File(filePath); PutObjectRequest putRequest = new
		 * PutObjectRequest(bucketName, keyName, file); s3Client.putObject(putRequest);
		 * System.out.println("File uploaded successfully to S3: " + keyName); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
        String region = "ap-northeast-2"; // AWS 리전
        //인코딩 안하면 url 안됨
        String encodedFileName = URLEncoder.encode("윤성바오1.jpg", StandardCharsets.UTF_8.toString());
        String imageUrl = "https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/" + encodedFileName;
        try {
            // 이미지 로드
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);

            // 이미지 표시
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
