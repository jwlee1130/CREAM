package com.cream.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.ResourceBundle;



public class AWSService {
    private AmazonS3 s3Client;
    
    private static String accessKey = "s3 서버 종료";
    private static String secretKey = "s3 서버 종료";
	/*s3 쓸때 키 지금은 종료해서 주석 처리 
	 * private static String accessKey = System.getenv("AWS_ACCESS_KEY_ID"); //액세스키
	 * private static String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY"); //
	 * 스크릿 엑세스 키
	 */    private Regions clientRegion = Regions.AP_NORTHEAST_2; // 한국 리전
    private String bucket = "kosta-286-cream"; // 버킷 명

    // singleton 구현
    private static AWSService instance = null;

    public static AWSService getInstance() {
        if (instance == null) {
            instance = new AWSService();
        }
        return instance;
    }

    // AWS S3 client 생성 (v1)
    private AWSService() {
        createS3Client();
    }

    private void createS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(clientRegion)
            .build();
    }

    // 단일 파일 업로드 메서드
    public void upload(File file, String key) {
    	String url = "img/"+key;
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucket, url, file);
            s3Client.putObject(putObjectRequest);
            System.out.println(String.format("[%s] upload complete", key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // contentType과 contentLength 설정을 포함한 업로드 메서드 (v1에서는 contentLength를 따로 설정하지 않음)
    public void upload(File file, String key, String contentType, long contentLength) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucket, key, file);
            putObjectRequest.getMetadata().setContentType(contentType);

            s3Client.putObject(putObjectRequest);
            System.out.println(String.format("[%s] upload complete", key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
