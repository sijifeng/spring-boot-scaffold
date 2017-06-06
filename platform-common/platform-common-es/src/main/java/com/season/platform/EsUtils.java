package com.season.platform;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.*;


/**
 * Created by jiyc on 2017/6/5.
 */
public class EsUtils {
	private static final Logger logger = LoggerFactory.getLogger(EsUtils.class);

	private TransportClient client = null;

	private TransportClient getClient() {
		if (this.client != null) {
			return client;
		}
		try {
			Settings settings = Settings.builder().put("cluster.name", "kingnetdc-es").put("client.transport.sniff", true).build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.78.45"), 9300));
			return client;
		} catch (Exception e) {
			logger.error("get client error.", e);
		}
		return null;
	}

	private void closeClient() {
		try {
			if (client != null) {
				client.close();
				client = null;
			}
		} catch (Exception e) {
			logger.error("close client error.", e);
		}
	}


	public void index() throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("name", "广告信息11");
		infoMap.put("title", "我的广告22");
		infoMap.put("createTime", new Date());
		infoMap.put("count", 1022);
		IndexResponse indexResponse = getClient().prepareIndex("test", "info", "100").setSource(infoMap).execute().actionGet();
		System.out.println("id:" + indexResponse.getId());
	}

	public void get() throws Exception {
		GetResponse response = client.prepareGet("sxq", "user", "2")
				.execute().actionGet();
		System.out.println("response.getId():" + response.getId());
		System.out.println("response.getSourceAsString():" + response.getSourceAsString());
	}

	public void query() throws Exception {
		//term查询
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("age", 50) ;
		//range查询
		QueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gt(50);
		SearchResponse searchResponse = client.prepareSearch("sxq")
				.setTypes("user")
				.setQuery(rangeQueryBuilder)
				.addSort("age", SortOrder.DESC)
				.setSize(20)
				.execute()
				.actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查到记录数：" + hits.getTotalHits());
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {
				String name = (String) hit.getSource().get("name");
				Integer age = (Integer) hit.getSource().get("age");
				System.out.format("name:%s ,age :%d \n", name, age);
			}
		}
	}

	public void deleteIndex() {
		DeleteResponse response = client.prepareDelete("feng", "test", "1").execute().actionGet();
	}


	public void write() {
		int n = 0;
		getClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		Map<String, List<Object>> map = new HashMap<>();

		IndexRequestBuilder lrb = client.prepareIndex("index", "type",
				"idValue").setSource(map);
		bulkRequest.add(lrb);

		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			logger.error("写入ES错误  error:" + bulkResponse.getItems().toString() + ", 总数据条数：" + n);
		} else {
			logger.info("写入ES成功,总共保存数据条数：" + n);
		}

	}



}
