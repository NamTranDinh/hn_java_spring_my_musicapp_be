package com.aptech.mymusic.config;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConfig {

    private final Resource.Type systemResourceType = Resource.Type.LOCAL;

    @Value("${cloud.local.resource.path}")
    private String localResourcePath;
    @Value("${cloud.local.resource.suffix}")
    private String localResourceSuffix;

    @Value("${cloud.gcp.project.id}")
    private String gcpProjectId;
    @Value("${cloud.gcp.bucket.name}")
    private String gcpBucketName;
    @Value("${cloud.gcp.credentials-path}")
    private String gcpCredentialsPath;

    @Value("${cloud.aws.bucket.name}")
    private String awsBucketName;
    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;
    @Value("${cloud.aws.region.static}")
    private String awsRegionName;

    ///////////////////////////////////////////////////////////////////////////
    // Getter
    ///////////////////////////////////////////////////////////////////////////

    public Resource.Type getSystemResourceType() {
        return systemResourceType;
    }

    public String getLocalResourcePath() {
        return localResourcePath;
    }

    public String getLocalResourceSuffix() {
        return localResourceSuffix;
    }

    public String getGcpProjectId() {
        return gcpProjectId;
    }

    public String getGcpBucketName() {
        return gcpBucketName;
    }

    public String getGcpCredentialsPath() {
        return gcpCredentialsPath;
    }

    public String getAwsBucketName() {
        return awsBucketName;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public String getAwsRegionName() {
        return awsRegionName;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Singleton
    ///////////////////////////////////////////////////////////////////////////

    private static ResourceConfig instance;

    public static ResourceConfig getInstance() {
        return instance;
    }

    ResourceConfig() {
        instance = this;
    }

}
