package com.forthbutton.telematics.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.forthbutton.telematics.dao.EventDataDao;
import com.forthbutton.telematics.entity.EventData;
import com.forthbutton.telematics.page.PageModel;
import com.google.code.morphia.Morphia;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository("eventDataDao")
public class EventDataDaoImpl implements EventDataDao {
    
    private static final Logger logger = LoggerFactory.getLogger(EventDataDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    private Morphia morphia;
    
    public EventDataDaoImpl() {
        morphia = new Morphia();
        morphia.map(EventData.class);
    }

    @Override
    public PageModel<EventData> getEventDataList(PageModel<EventData> page, DBObject queryObject,
            String collectionName) {

        DBObject filterDBObject = new BasicDBObject();
        // filterDBObject.put("suid", "TU00010000");
        // filterDBObject.put("cname", 1);
        // filterDBObject.put("onumber", 1);
        // List<EventData> list = mongoTemplate.find(query, EventData.class);
        DBCursor dbCursor =
                mongoTemplate.getCollection(collectionName).find(queryObject, filterDBObject);
        // 排序
        // DBObject sortDBObject = new BasicDBObject();
        // sortDBObject.put("report_time", 1);
        // dbCursor.sort(sortDBObject);
        // 分页查询
        dbCursor.skip(page.getSkip()).limit(page.getPageSize());

        // 总数
        int count = dbCursor.count();
        // 循环指针
        List<EventData> datas = new ArrayList<EventData>();
        while (dbCursor.hasNext()) {
            //datas.add(morphia.fromDBObject(EventData.class, dbCursor.next()));
        }

        page.setRowCount(count);
        page.setDatas(datas);
        return page;
    }

    /*
     * mongodb分页查询demo示例
     * 
     * */
    @Override
    public PageModel<EventData> getEventDataListDemo(PageModel<EventData> page,
            String collectionName) {
        /* 查询条件设置 */
        DBObject queryObject = new BasicDBObject();
        /* suid=TU00010000 */
        //queryObject.put("suid", "TU00010000");
        /* height大于2小于5 */
        queryObject.put("height", new BasicDBObject("$gt", 0).append("$lt", 50));
        
        /* 过滤返回对象 */
        DBObject filterDBObject = new BasicDBObject();
        /*
         * suid设置为-1时suid返回null,其他字段值正常返回
         * suid设置为1时只返回suid的值,其他字段值返回null
         * suid设置为0时suid返回null,其他字段值正常返回
         * */
        //filterDBObject.put("suid", 1);

        /* 排序 */
        DBObject sortDBObject = new BasicDBObject();
        /*-1: desc; 1: asc */
        sortDBObject.put("height", -1);
        
        DBCursor dbCursor =
                mongoTemplate.getCollection(collectionName).find(queryObject, filterDBObject)
                        .sort(sortDBObject).skip(page.getSkip()).limit(page.getPageSize());

        // 总数
        int count = dbCursor.count();
        logger.info("=================>结果集总条数为:"+count);
        // 循环指针
        List<EventData> datas = new ArrayList<EventData>();
        while (dbCursor.hasNext()) {
            datas.add(morphia.fromDBObject(EventData.class, dbCursor.next()));
        }
        page.setRowCount(count);
        page.setDatas(datas);
        return page;
    }

}
