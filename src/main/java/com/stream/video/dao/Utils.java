package com.stream.video.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils{
	public static <T> String convertEntityListToJson(List<T> entity) {
		String jsonHeader = "[\n";
		String jsonTrailer = "\n]";
		String jsonBody = "";
		for(int i=0;i<entity.size();i++) {
			if(i==0)
				jsonBody += entity.get(i).toString();
			else
				jsonBody += (",\n"+entity.get(i).toString());
		}
		return jsonHeader + jsonBody + jsonTrailer;
	}

}
