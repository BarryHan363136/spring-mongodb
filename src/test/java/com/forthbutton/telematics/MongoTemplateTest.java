package com.forthbutton.telematics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forthbutton.telematics.dao.EventDataDao;
import com.forthbutton.telematics.entity.Customer;
import com.forthbutton.telematics.entity.EventData;
import com.forthbutton.telematics.page.PageModel;
import com.forthbutton.telematics.page.Pagination;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 单元测试
 * @author hts
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring.xml")
public class MongoTemplateTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MongoTemplateTest.class);
    
    private static final String collectionName = "vehicle_position";
    
    @Autowired 
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private EventDataDao eventDataDao;
    
    @Ignore
    @Test
    public void testSave(){
        List<EventData> list = new ArrayList<EventData>();
        for(int i=1;i<10001;i++){
            EventData data = new EventData();
            data.setSuid("TU000"+i);
            data.setReport_powerfailure(i);
            data.setReport_undervoltage(i);
            data.setReport_powerfailure_recovery(i);
            data.setReport_undervoltage_recovery(i);
            data.setReport_speeding(i);
            data.setReport_dismantle(i);
            data.setReport_bluetooth(i);
            data.setBluetooth_state(i);
            data.setAcc_state(i);
            data.setGps_fixed_position(i);
            data.setLatitude(100.123456);
            data.setLongitude(200.123456);
            data.setHeight((short) i);
            data.setSpeed((short) i);
            data.setDirection((short) i);
            data.setReport_time(new Date());
            data.setCreate_time(new Date());
            list.add(data);
        }
        mongoTemplate.insert(list, EventData.class);
        //mongoTemplate.insert(data);
        logger.info("<===========新增完成==================>");
    }
    
    @Ignore
    @Test
    public void testSave2(){
        Customer c = new Customer();
        c.setFirstName("wu");
        c.setLastName("wei");
        mongoTemplate.insert(c);  
    }
    
    @Ignore
    @Test
    public void testQuery(){
        Query query = new Query();
        //query.addCriteria(Criteria.where("suid").is("TU000100"));
        query.addCriteria(Criteria.where("suid").gte("TU000100"));
        query.limit(100);
        List<EventData> list = mongoTemplate.find(query, EventData.class);
        for(EventData data : list){
            logger.info("===========>suid:"+data.getSuid());
        }
    }
    
    /*
     * skip方法是跳过条数，而且是一条一条的跳过，如果集合比较大时（如书页数很多）skip会越来越慢, 需要更多的处理器(CPU)，这会影响性能。
     * */
    @Ignore
    @Test
    public void testQuerySkipLimit(){
        int pageNo = 2;
        int pageSize = 20;
        Query query = new Query();
        query.addCriteria(Criteria.where("height").gt(1000));
        
        long totalCount = mongoTemplate.count(query, EventData.class);
        
        Pagination<EventData> page = new Pagination<EventData>(pageNo, pageSize, totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(pageSize);// 从skip开始,取多少条记录
        List<EventData> datas = mongoTemplate.find(query, EventData.class);
        page.setDatas(datas);
        for(EventData data : datas){
            logger.info("=============================>height:"+data.getHeight());
        }
    }
    
    @Ignore
    @Test
    public void testQueryDBCourseLimit(){
        int pageNo = 2;
        int pageSize = 20;
        PageModel<EventData> page = new PageModel<EventData>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        DBObject queryObject = new BasicDBObject();
        queryObject.put("suid", "TU00010000");
        PageModel<EventData> pageList = eventDataDao.getEventDataList(page, queryObject, collectionName);
        if(pageList!=null){
            List<EventData> datas = pageList.getDatas();
            for(EventData data : datas){
                logger.info("=======================>Suid:"+data.getSuid());
                logger.info("=======================>Height:"+data.getHeight());
            }
            logger.info("=======================>结果集总条数为:"+pageList.getRowCount());
        }
    }
    
    @Ignore
    @Test
    public void testQueryDBCourseLimitDemo(){
        int pageNo = 1;
        int pageSize = 20;
        PageModel<EventData> page = new PageModel<EventData>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        DBObject queryObject = new BasicDBObject();
        queryObject.put("suid", "TU00010000");
        PageModel<EventData> pageList = eventDataDao.getEventDataListDemo(page, collectionName);
        if(pageList!=null){
            List<EventData> datas = pageList.getDatas();
            for(EventData data : datas){
                logger.info("=======================>Suid:"+data.getSuid());
                logger.info("=======================>Height:"+data.getHeight());
            }
            logger.info("=======================>结果集总条数为:"+pageList.getRowCount());
        }
    }

}
