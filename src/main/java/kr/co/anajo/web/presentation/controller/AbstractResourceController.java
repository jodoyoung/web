package kr.co.anajo.web.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import kr.co.anajo.core.IdGenerator;
import kr.co.anajo.web.component.resource.ResourceType;

public abstract class AbstractResourceController {

	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("id", IdGenerator.createUUID());

		this.doCreate(request, response);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:36006/create");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("id", (String) request.getAttribute("id")));
		urlParameters.add(new BasicNameValuePair("type", this.getResourceType().name()));
		urlParameters.add(new BasicNameValuePair("parentId", request.getHeader("X-Resource-Parent-ID")));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		client.execute(post);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.doUpdate(request, response);
	}

	public void read(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.doRead(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.doDelete(request, response);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:36006/create");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("id", request.getParameter("id")));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		client.execute(post);
	}

	public abstract ResourceType getResourceType();

	protected abstract void doCreate(HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected abstract void doRead(HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected abstract void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected abstract void doDelete(HttpServletRequest request, HttpServletResponse response) throws Exception;

}