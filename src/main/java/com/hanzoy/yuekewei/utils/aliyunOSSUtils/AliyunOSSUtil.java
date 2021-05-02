package com.hanzoy.yuekewei.utils.aliyunOSSUtils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.hanzoy.yuekewei.config.AliyunOSSConfigConstant;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class AliyunOSSUtil {
    private static String FILE_URL;
    private static String bucketName = AliyunOSSConfigConstant.BUCKE_NAME;
    private static String endpoint = AliyunOSSConfigConstant.END_POINT;
    private static String accessKeyId = AliyunOSSConfigConstant.AccessKey_ID;
    private static String accessKeySecret = AliyunOSSConfigConstant.AccessKey_Secret;
    private static String fileHost = AliyunOSSConfigConstant.FILE_HOST;
    private static String localFileName = AliyunOSSConfigConstant.LOCAL_FILE;

    /**
     * 上传图片
     * @param file
     * @return
     */
    public static String upLoad(File file){
        // 默认值：true
        boolean isImage = true;
        // 判断所要上传的是否是图片，图片可以预览，其他不支持预览
        try {
            Image image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------OSS文件上传开始--------" + file.getName());

        // 判断文件
        if(file == null){
            return null;
        }

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
            // 判断容器是否存在，不存在创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            // 设置文件路径和名称
            String fileUrl = fileHost + "/" + (UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            if (isImage) {
                FILE_URL = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            } else {
                FILE_URL = "非图片，不可预览，文件路径为：" + fileUrl;
            }

            // 上传图片
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));

            // 设置权限（公开读）
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                System.out.println("------OSS文件上传成功--------" + file.getName());
            }
        } catch (OSSException oe) {
            System.out.println(oe.getMessage());
        } catch (ClientException ce) {
            System.out.println(ce.getErrorMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return  FILE_URL;
    }

    /**
     * 下载图片
     * @param objectName
     */
    public static void downloadFile(String objectName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地，如果本地存在会被覆盖，不存在就创建
        String fielName = objectName.substring(objectName.indexOf("-")+1,objectName.length());
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFileName + fielName));

        // 关闭ossClient
        ossClient.shutdown();
    }

    /**
     * 浏览图片
     * @return
     */
    public static List<Map<String, Object>> listFile() {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //列出文件
        ObjectListing listing = ossClient.listObjects(new ListObjectsRequest(bucketName).withPrefix("test/"));

        //遍历所有文件
        List<OSSObjectSummary> ossObjectSummaryList = listing.getObjectSummaries();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < ossObjectSummaryList.size(); i ++ ) {
            //去除第一个文件夹名称
            if (i >= 1){
                Map<String, Object> map = new HashMap<>();
                map.put("url", "https://" + bucketName + "." + endpoint + "/" + ossObjectSummaryList.get(i).getKey());
                map.put("fileName", ossObjectSummaryList.get(i).getKey());
                list.add(map);
            }
        }
        //关闭ossClient
        ossClient.shutdown();
        return list;
    }

    /**
     * 删除文件
     * @param objectName
     */
    public static void deleteFile(String objectName){
        //创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //删除文件
        ossClient.deleteObject(bucketName, objectName);

        //关闭OSSClient
        ossClient.shutdown();
    }

}
