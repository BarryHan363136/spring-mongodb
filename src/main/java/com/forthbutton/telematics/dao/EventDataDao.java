package com.forthbutton.telematics.dao;

import com.forthbutton.telematics.entity.EventData;
import com.forthbutton.telematics.page.PageModel;
import com.mongodb.DBObject;


public interface EventDataDao {
    
    public PageModel<EventData> getEventDataList(PageModel<EventData> page, DBObject queryObject, String collectionName);
    
    public PageModel<EventData> getEventDataListDemo(PageModel<EventData> page, String collectionName);
    
}
