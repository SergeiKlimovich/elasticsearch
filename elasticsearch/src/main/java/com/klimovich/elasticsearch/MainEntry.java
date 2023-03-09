package com.klimovich.elasticsearch;

public class MainEntry {
    public static void main(String[] args) {

        ESIndexing esIndexing = new ESIndexing();
        esIndexing.initESTransportClient();
        esIndexing.CSVImport(true);
    }
}
