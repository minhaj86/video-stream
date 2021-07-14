package com.stream.video.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VideoDAO {
	static Logger logger = null;
	private void log(String msg) {
		if(VideoDAO.logger == null) {
			 VideoDAO.logger = Logger.getLogger(VideoDAO.class.getName());
		}
		VideoDAO.logger.info(msg);
	}
	public void addVideo(Video bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addVideo(session, bean);
		addVideoGenres(session, bean);
		tx.commit();
		session.close();

	}
	
	private void addVideoGenres(Session session, Video bean) {
		bean.getGenres().forEach(genre -> {
			log("Video id"+bean.getId());
			genre.setVideo(bean);
			session.save(genre);
		});
	}
	
	private void addVideo(Session session, Video bean) {
		Serializable id = session.save(bean);
		log("Added video with ID="+id);
		log("ID found in bean: "+bean.getId());
	}

	public List<Video> getVideos() {
		log("Videos JSON: ");
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Video");
		List<Video> videos = null ;
		try {
			videos = query.list();
			log("Videos JSON: " + Utils.convertEntityListToJson(videos));
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return videos;
	}

	public Video getVideo(int id) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Video where id = :id");
		query.setInteger("id", id);
		Video video = (Video) query.uniqueResult();
		log(video.toString());
		session.close();
		return video;
	}

	private Set<VideoGenre> getVideoGenres(Session session, int videoId) {
		Query query = session.createQuery("from VideoGenre where video_id = :id");
		query.setInteger("id", videoId);
		List<VideoGenre> videoGenres = query.list();
		return (Set<VideoGenre>) videoGenres;
	}

	public int deleteVideo(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Video where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
	
	public void deleteVideoGenres(Session session, int videoId) {
		String deleteVideoGenreHQL = "delete from VideoGenre where video_id = :id";
		Query deleteVideoGenreQuery = session.createQuery(deleteVideoGenreHQL);
		deleteVideoGenreQuery.setInteger("id", videoId);
		int rowCountDeleteVideoGenre = deleteVideoGenreQuery.executeUpdate();
		System.out.println("Rows affected: " + rowCountDeleteVideoGenre);

	}

	public int updateVideo(int id, Video bean) {
		if (id <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String updateVideoHQL = "update Video set title = :title, description=:description where id = :id";
		Query updateVideoQuery = session.createQuery(updateVideoHQL);
		updateVideoQuery.setInteger("id", id);
		updateVideoQuery.setString("title", bean.getTitle());
		updateVideoQuery.setString("description", bean.getDescription());
		int rowCount = updateVideoQuery.executeUpdate();
		deleteVideoGenres(session,id);
		bean.setId(id);
		addVideoGenres(session, bean);
		tx.commit();
		session.close();
		return rowCount;
	}
}
