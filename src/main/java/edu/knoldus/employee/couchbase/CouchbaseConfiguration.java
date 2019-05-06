package edu.knoldus.employee.couchbase;

import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration  {

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "Bucket1";
    }

    @Override
    protected String getUsername() {
        return "Administrator";
    }

    @Override
    protected String getBucketPassword() {
        return "Administrator";
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        return DefaultCouchbaseEnvironment.builder()
                .connectTimeout(20000)
                .build();
    }


}
