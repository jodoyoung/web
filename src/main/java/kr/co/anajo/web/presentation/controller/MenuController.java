package kr.co.anajo.web.presentation.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.anajo.web.component.resource.ResourceType;

public class MenuController extends AbstractResourceController {

	@Override
	public ResourceType getResourceType() {
		return ResourceType.MENU;
	}

	@Override
	public void doCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = IOUtils.toString(request.getInputStream());
		JsonParser parser = new JsonParser();
		JsonObject params = parser.parse(json).getAsJsonObject();
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:36007/create");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("id", (String) request.getAttribute("id")));
		urlParameters.add(new BasicNameValuePair("title", params.get("title").getAsString()));
		urlParameters.add(new BasicNameValuePair("visibility", params.get("visibility").getAsString()));
		urlParameters.add(new BasicNameValuePair("link", params.get("link").getAsString()));
		urlParameters.add(new BasicNameValuePair("order", params.get("order").getAsString()));
		post.setEntity(new UrlEncodedFormEntity(urlParameters, "utf-8"));

		client.execute(post);
	}

	@Override
	protected void doRead(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();

		URIBuilder builder = new URIBuilder("http://localhost:36007/read");
		builder.setParameter("id", request.getParameter("id"));

		HttpGet get = new HttpGet(builder.build());
		HttpResponse result = client.execute(get);

		String content = EntityUtils.toString(result.getEntity());

		response.setHeader("Content-Type", "application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(content);
		out.close();
	}

	@Override
	protected void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

}