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
import javax.ws.rs.core.Response;

import com.stream.video.dao.Video;
import com.stream.video.dao.VideoDAO;

@Path("/video")
public class VideoResource {

	@GET
	@Produces("application/json")
	public List<Video> getVideo() {
		VideoDAO dao = new VideoDAO();
		List<Video> videos = dao.getVideos();
		return videos;
	}

	@POST
//	@Path("/create")
	@Consumes("application/json")
	public Response addVideo(Video bean) {
		bean.setTitle(bean.getTitle());
		bean.setDescription(bean.getDescription());
		VideoDAO dao = new VideoDAO();
		dao.addVideo(bean);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateVideo(@PathParam("id") int id, Video emp) {
		VideoDAO dao = new VideoDAO();
		int count = dao.updateVideo(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteVideo(@PathParam("id") int id) {
		VideoDAO dao = new VideoDAO();
		int count = dao.deleteVideo(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}
