package edu.mum.cs.restaurants.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

/**
 * The type Cassandra configuration.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    private static final String KEYSPACE = "restaurants_keyspace";

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections
                .singletonList(CreateKeyspaceSpecification
                        .createKeyspace(KEYSPACE)
                        .with(KeyspaceOption.DURABLE_WRITES, true));
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Collections
                .singletonList(DropKeyspaceSpecification
                        .dropKeyspace(KEYSPACE));
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"edu.mum.cs.restaurants.models"};
    }
}
