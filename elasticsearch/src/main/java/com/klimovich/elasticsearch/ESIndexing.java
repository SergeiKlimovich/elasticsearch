package com.klimovich.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.klimovich.elasticsearch.entity.Person;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ESIndexing {

    private static final String HOST_NAME = "localhost";
    private static final String INDEX_NAME = "document";
    private static final Integer PORT = 9200;
    private static final String PATH = "C:\\MyCoding\\Java\\elasticsearch\\elasticsearch\\src\\main\\resources\\Input.csv";


    ElasticsearchClient client;

    @SneakyThrows
    public void initESTransportClient() {
        RestClient restClient = RestClient.builder(
                new HttpHost(HOST_NAME, PORT)).build();

        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        client = new ElasticsearchClient(transport);
    }

    @SneakyThrows
    public void CSVImport(boolean isHeaderIncluded) {
        File file = new File(PATH);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        if (isHeaderIncluded) {
            bufferedReader.readLine();
        }
        while ((line = bufferedReader.readLine()) != null) {
            String data[] = line.split(",");
            Person person = new Person(data[0], data[1], data[2]);
            client.index(i -> i.index(INDEX_NAME)
                    .id(person.getId())
                    .document(person)).id();
        }
        bufferedReader.close();
    }
}
