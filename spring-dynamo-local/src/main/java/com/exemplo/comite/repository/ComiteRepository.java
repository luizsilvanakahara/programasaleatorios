package com.exemplo.comite.repository;

import com.exemplo.comite.model.Comite;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

@Repository
public class ComiteRepository {

    private final DynamoDbTable<Comite> table;

    public ComiteRepository(DynamoDbEnhancedClient client) {
        this.table = client.table("Comite", TableSchema.fromBean(Comite.class));
    }

    public void save(Comite comite) {
        table.putItem(comite);
    }
}
