package com.stream.video.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.stream.video.dao.Utils;
import com.stream.video.dao.Video;
import com.stream.video.dao.VideoDAO;

@Path("/video")
public class VideoResource {
	static Logger logger = null;
	private void log(String msg) {
		if(VideoResource.logger == null) {
			VideoResource.logger = Logger.getLogger(VideoResource.class.getName());
		}
		VideoResource.logger.info(msg);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getVideos() {
		VideoDAO dao = new VideoDAO();
		List<Video> videos = dao.getVideos();
		return Utils.convertEntityListToJson(videos);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getVideo(@PathParam("id") int id) {
		VideoDAO dao = new VideoDAO();
		Video video = dao.getVideo(id);
		return video.toString();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addVideo(Video bean) {
		bean.setTitle(bean.getTitle());
		bean.setDescription(bean.getDescription());
		VideoDAO dao = new VideoDAO();
		dao.addVideo(bean);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateVideo(@PathParam("id") int id, Video video) {
		VideoDAO dao = new VideoDAO();
		int count = dao.updateVideo(id, video);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteVideo(@PathParam("id") int id) {
		VideoDAO dao = new VideoDAO();
		int count = dao.deleteVideo(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}
