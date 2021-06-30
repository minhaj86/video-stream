package com.stream.video.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VideoDAO {
      
      public void addVideo(Video bean){
        Session session = SessionUtil.getSession();    
        Transaction tx = session.beginTransaction();
        addVideo(session,bean);    
        tx.commit();
        session.close();
        
      }
      
      private void addVideo(Session session, Video bean){
        Video Video = new Video();
        
        Video.setName(bean.getName());
        Video.setType(bean.getType());
        
        session.save(Video);
      }
      
      public List<Video> getVideos(){
        Session session = SessionUtil.getSession();  
        Query query = session.createQuery("from Video");
        List<Video> Videos =  query.list();
            session.close();
        return Videos;
      }

      public int deleteVideo(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Video where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
      }
      
      public int updateVideo(int id, Video emp){
         if(id <=0)  
             return 0;  
         Session session = SessionUtil.getSession();
          Transaction tx = session.beginTransaction();
          String hql = "update Video set name = :name, age=:age where id = :id";
          Query query = session.createQuery(hql);
          query.setInteger("id",id);
          query.setString("name",emp.getName());
          query.setInteger("age",emp.getType());
          int rowCount = query.executeUpdate();
          System.out.println("Rows affected: " + rowCount);
          tx.commit();
          session.close();
          return rowCount;
      }
}
