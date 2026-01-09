import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class CriarTabelaComite {
    public static void main(String[] args) {
        DynamoDbClient dynamoDb = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .credentialsProvider(
                        software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
                                software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("fake", "fake")
                        )
                )
                .build();

        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Comite")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)  // Partition key
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("id")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();

        dynamoDb.createTable(request);
        System.out.println("Tabela 'Comite' criada com sucesso!");
    }
}
