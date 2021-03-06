package com.wangl;


import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Created by seentech on 2017/1/19.
 */
public class Application {

    public static void main(String[] args){
        TransportClient client;
        try {
            client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

            //创建数据
            String json = "{" +
                    "\"user\":\"kimchy\"," +
                    "\"postDate\":\"2013-01-30\"," +
                    "\"message\":\"trying out Elasticsearch\"" +
                    "}";
            IndexResponse responseIndex = client.prepareIndex("twitter", "tweet", "1")
                    .setSource(json)
                    .get();

            //搜索数据
            GetResponse responseGet = client.prepareGet("twitter", "tweet", "1").execute().actionGet();
            //输出结果
            System.out.println(responseGet.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catche UnknownHostException");
            return ;
        }
        finally {
            System.out.println("finally UnknownHostException");
        }

        System.out.println("Still excute");



    }
}
