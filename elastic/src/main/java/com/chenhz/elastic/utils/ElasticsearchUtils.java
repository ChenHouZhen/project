package com.chenhz.elastic.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ElasticsearchUtils{
    private static final Logger log = LoggerFactory.getLogger(ElasticsearchUtils.class);

    @Autowired
    private TransportClient transportClient;

    private static TransportClient client;

    //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    @PostConstruct
    public void init(){
        client = transportClient;
    }

    /**
     * 创建索引
     */
    public static boolean createIndex(String index){
//        if (!isIndexExist(index)){
//            log.info("Index is not exits!");
//        }

        CreateIndexResponse indexResponse = client.admin()
                .indices()
                .prepareCreate(index)
                .execute()
                .actionGet();
        log.info("执行建立成功？{}",indexResponse.isAcknowledged());
        return indexResponse.isShardsAcked();
    }

    /**
     * 判断索引是否存在
     * @param index
     * @return
     */
    private static boolean isIndexExist(String index) {
        IndicesExistsResponse inExistsResponse = client.admin()
                .indices()
                .exists(new IndicesExistsRequest(index))
                .actionGet();
//        if (inExistsResponse.isExists()){
//            log.info("Index {} is exist!",index);
//        } else {
//            log.info("Index is not exist! ",index);
//        }
        log.info("Index:{} is exist? {}",index,inExistsResponse.isExists());
        return inExistsResponse.isExists();
    }

    /**
     * 删除索引
     */
    public static boolean deleteIndex(String index){
        DeleteIndexResponse dResponse = client.admin()
                .indices()
                .prepareDelete(index)
                .execute()
                .actionGet();

        log.info("delete index:{}  is successful? " ,index,dResponse.isAcknowledged());
        return dResponse.isAcknowledged();

    }

    /**
     * 添加数据 指定ID
     */
    public static String addData(JSONObject jsonObject,String index,String type,String id){
        IndexResponse response = client.prepareIndex(index,type,id)
                .setSource(jsonObject)
                .get();
        log.info("addData response status:{},id:{}",response.status().getStatus(),response.getId());

        return response.getId();
    }

    public static String addData(JSONObject jsonObject,String index,String type){
        return addData(jsonObject,index,type,UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
    }


    /**
     * 更新数据
     */
    public static void updateDataById(JSONObject jsonObject ,String index,String type,String id){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(jsonObject);
        client.update(updateRequest);
    }

    /**
     * 通过ID获取数据
     * @param fieles 以逗号分隔
     */
    public static Map<String,Object> searchDataById(String index ,String type,String id,String fieles){
        GetRequestBuilder getRequestBuilder = client.prepareGet(index,type,id);
        if (StringUtils.isNotEmpty(fieles)){
            getRequestBuilder.setFetchSource(fieles.split(","),null);
        }
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        return getResponse.getSource();
    }

    /**
     * 使用分词查询
     *
     * @param index     索引名称
     * @param type      类型名称,可传入多个type逗号分隔
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param size      文档大小限制
     * @param matchStr  过滤条件（xxx=111,aaa=222）
     * @return
     */
    public static List<Map<String,Object>> searchListData(String index
            , String type
            , long startTime
            , long endTime
            , Integer size
            , String matchStr){
        return searchListData(index,type,startTime,endTime,size,null,null,false,null,matchStr);
    }

    /**
     * 使用分词查询
     *
     * @param index    索引名称
     * @param type     类型名称,可传入多个type逗号分隔
     * @param size     文档大小限制
     * @param fields   需要显示的字段，逗号分隔（缺省为全部字段）
     * @param matchStr 过滤条件（xxx=111,aaa=222）
     * @return
     */
    public static List<Map<String, Object>> searchListData(String index
            , String type
            , Integer size
            , String fields
            , String matchStr) {
        return searchListData(index, type, 0, 0, size, fields, null, false, null, matchStr);
    }

    /**
     * 使用分词查询
     *
     * @param index       索引名称
     * @param type        类型名称,可传入多个type逗号分隔
     * @param size        文档大小限制
     * @param fields      需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField   排序字段
     * @param matchPhrase true 使用，短语精准匹配
     * @param matchStr    过滤条件（xxx=111,aaa=222）
     * @return
     */
    public static List<Map<String, Object>> searchListData(String index
            , String type
            , Integer size
            , String fields
            , String sortField
            , boolean matchPhrase
            , String matchStr) {
        return searchListData(index, type, 0, 0, size, fields, sortField, matchPhrase, null, matchStr);
    }


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称,可传入多个type逗号分隔
     * @param size           文档大小限制
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchPhrase    true 使用，短语精准匹配
     * @param highlightField 高亮字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
    public static List<Map<String, Object>> searchListData(String index
            , String type, Integer size
            , String fields
            , String sortField
            , boolean matchPhrase
            , String highlightField
            , String matchStr) {
        return searchListData(index, type, 0, 0, size, fields, sortField, matchPhrase, highlightField, matchStr);
    }

    public static List<Map<String,Object>> searchListData(String index
            , String type
            , long startTime
            , long endTime
            , Integer size
            , String fields
            , String sortField
            , boolean matchPhrase
            , String highlightField
            , String matchStr){

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (StringUtils.isNotEmpty(type)){
            searchRequestBuilder.setTypes(type.split(","));
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (startTime > 0 && endTime > 0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("timestamp")
            .format("epoch_mills")
            .from(startTime)
            .to(endTime)
            .includeLower(true)
            .includeUpper(true));
        }

        // 搜索的字段
        if (StringUtils.isNotEmpty(matchStr)){
            for (String s : matchStr.split(",")){
                String[] ss = s.split("=");
                if (ss.length > 1){
                    if (matchPhrase == Boolean.TRUE){
                        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(s.split("=")[0],s.split("=")[1]));
                    }else {
                        boolQueryBuilder.must(QueryBuilders.matchQuery(s.split("=")[0],s.split("=")[1]));
                    }
                }
            }
        }

        if (StringUtils.isNotEmpty(highlightField)){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            //highlightBuilder.preTags("<span style='color:red' >");//设置前缀
            //highlightBuilder.postTags("</span>");//设置后缀

            // 设置高亮字段
            highlightBuilder.field(highlightField);
            searchRequestBuilder.highlighter(highlightBuilder);
        }

        searchRequestBuilder.setQuery(boolQueryBuilder);

        if (StringUtils.isNotEmpty(fields)){
            searchRequestBuilder.setFetchSource(fields.split(","),null);
        }
        searchRequestBuilder.setFetchSource(true);

        if (StringUtils.isNotEmpty(sortField)){
            searchRequestBuilder.addSort(sortField,SortOrder.DESC);
        }

        if (size != null && size > 0){
            searchRequestBuilder.setSize(size);
        }

        log.info("\n{}",searchRequestBuilder);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;

        log.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);


        // TODO:没写完，还要继续
        if (searchResponse.status().getStatus() == 200){
//            return
        }

        return null;

    }

}
