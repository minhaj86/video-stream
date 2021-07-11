package com.stream.video.dao;

import java.io.Serializable;
import java.util.List;

import org.glassfish.hk2.utilities.reflection.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VideoDAO {

	public void addVideo(Video bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addVideo(session, bean);
		tx.commit();
		Transaction tx1 = session.beginTransaction();
		addVideoGenres(session, bean);
		tx1.commit();
		session.close();

	}
	
	private void addVideoGenres(Session session, Video bean) {
//		bean.getGenres().forEach(genre -> {
//			genre.setVideo(bean);
//			session.save(genre);
//		});
	}

	private void addVideo(Session session, Video bean) {
		Video video = new Video();
		video.setTitle(bean.getTitle());
		video.setDescription(bean.getDescription());
		Serializable id = session.save(video);
		System.out.println(id);
		System.out.println(video.getId());
	}

	public List<Video> getVideos() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Video");
		List<Video> Videos = query.list();
		session.close();
		return Videos;
	}

	public Video getVideo(int id) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Video where id = :id");
		query.setInteger("id", id);
		Video video = (Video) query.uniqueResult();
//		List<Video> Videos = query.list();
		session.close();
		return video;
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

	public int updateVideo(int id, Video bean) {
		if (id <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Video set title = :title, description=:description where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		query.setString("title", bean.getTitle());
		query.setString("description", bean.getDescription());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
}
