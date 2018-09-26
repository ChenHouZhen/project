package com.chenhz.tpos.controller;

import com.chenhz.common.entity.R;
import com.chenhz.tpos.item.solr.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@Api(tags = "Solr 操作接口")
@RequestMapping("/solr")
public class SolrController {

    private static final String DEFAULT_CORE = "test";

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SolrClient solrClient;

    @PostMapping("/add")
    @ApiOperation("增加")
    public R add() throws IOException, SolrServerException {
        Product product = new Product();
        product.setId("1");
        product.setName("陈某某");
        product.setPrice(1.23f);
        product.setFeatures(Arrays.asList("Java","Linux","Mysql","Python"));
        UpdateResponse updateResponse = solrClient.addBean(DEFAULT_CORE,product);
        log.debug("updateResponse.getRequestUrl() :"+updateResponse.getRequestUrl());
        log.debug("updateResponse.getQTime() :"+updateResponse.getQTime());
        log.debug("updateResponse.getResponse(): "+updateResponse.getResponse());
        log.debug("updateResponse.getStatus(): "+updateResponse.getStatus());
        log.debug("updateResponse.getResponseHeader(): "+updateResponse.getResponseHeader());
        return R.ok().put("response",updateResponse);
    }
}
