package br.com.kaindall.products.application.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${aws.localstack}")
    private String sqsUrl;

    AwsBasicCredentials credentials = AwsBasicCredentials.create("qualquer-uma", "qualquer-uma");

    AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .credentialsProvider(provider)
                .endpointOverride(URI.create(sqsUrl))
                .region(Region.SA_EAST_1)
                .build();
    }
}
