//package com.chenhz.elastic.config;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.chenhz.elastic")
//public class ElasticsearchConfig {
//
//    private final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);
//
//    /**
//     * elk集群地址
//     */
//    @Value("${elasticsearch.ip}")
//    private String hostName ;
//
//    /**
//     * 端口
//     */
//    @Value("${elasticsearch.port}")
//    private String port;
//
//    /**
//     * 集群名称
//     */
//    @Value("${elasticsearch.cluster.name}")
//    private String clusterName;
//
//    /**
//     * 连接池
//     */
//    @Value("${elasticsearch.pool}")
//    private String poolSize;
//
//    @Bean
//    public Client client(){
//        TransportClient transportClient = null;
//
//        Settings esSettings = Settings.builder().put("cluster.name",clusterName)
//                .put("client.transport.sniff",true)//增加嗅探机制，找到ES集群
//                .put("thread_pool.search.size",Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
//                .build();
//
//        try {
//            transportClient = new PreBuiltTransportClient(esSettings)
//                    .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(hostName),Integer.valueOf(port)));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            logger.error("elasticsearch TransportClient create error!!!", e);
//        }
//        return transportClient;
//
//    }
//
//}