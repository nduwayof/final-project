package edu.mum.cs.restaurants.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Arrays;
import java.util.List;

import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;

/**
 * The type Cassandra configuration.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.contactpoints}")
    private String contactPoints;
    @Value("${spring.data.cassandra.port}")
    private int port;
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.data.cassandra.username}")
    private String userName;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }

    @Override
    public CassandraClusterFactoryBean cluster() {
        PlainTextAuthProvider authProvider = new PlainTextAuthProvider(userName, System.getenv("CASSANDRA_PASSWORD"));

        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();

        cluster.setJmxReportingEnabled(false);
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setAuthProvider(authProvider);
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        cluster.setReconnectionPolicy(new ConstantReconnectionPolicy(1000));

        return cluster;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keyspace).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true);

        return Arrays.asList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keyspace));
    }
}
