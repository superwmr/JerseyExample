package tw.com.codedata.jersey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.JSONP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import test.Config;
import test.RsSyncStatus;
import test.RsTransferRecords;
import test.TestCase;

/**
 * Servlet Filter implementation class CustomLoggingFilter
 */
public class CustomLoggingFilter extends LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		StringBuilder sb = new StringBuilder();
//		sb.append("User: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
//				: requestContext.getSecurityContext().getUserPrincipal());
		String path = requestContext.getUriInfo().getPath();
		sb.append("\n-Path:").append(path);
		sb.append("\n-Parameters:").append(getPathParameters(requestContext));
		sb.append("\n-Header:").append(requestContext.getHeaders());
		String body = getEntityBody(requestContext);
		sb.append("\n-Body:").append(body);
		//
		System.out.println("st========================================");
		System.out.println("HTTP REQUEST : " + sb.toString());

		if (Config.isRunTestCase) {
			if (path.toLowerCase().indexOf("sync_status") != -1) {
				if (isLogoutFinish(body))
					TestCase.putNextCommand();
			} else if (path.toLowerCase().indexOf("transfer_records") != -1) {
				calSuccessRate(body);
			}
		}
	}

	private static int success = 0;
	private static int total = 0;

	private void calSuccessRate(String body) {
		Gson g = new GsonBuilder().create();
		RsTransferRecords rsTransferRecords = g.fromJson(body, RsTransferRecords.class);

		total++;

		if (rsTransferRecords.getError_code().equals("00")) {
			success++;
		}

		System.out.println("第" + total + "次轉帳。");
		String rate = new DecimalFormat("0.00").format((((float) success / (float) total) * 100));
		System.out.println("轉帳成功率: " + success + " / " + total + " = " + rate + "%");
	}

	private boolean isLogoutFinish(String strJson) {

		Gson g = new GsonBuilder().create();
		RsSyncStatus rsSyncStatus = g.fromJson(strJson, RsSyncStatus.class);

		return rsSyncStatus.getStatus_type().equals("0");
	}

	private String getPathParameters(ContainerRequestContext requestContext) {

		MultivaluedMap<String, String> map = requestContext.getUriInfo().getQueryParameters();
		List<String> keys = new ArrayList(map.keySet());
		List<String> strResult = new ArrayList();
		for (int i = 0; i < keys.size(); i++) {
			strResult.add(keys.get(i) + "=" + map.getFirst(keys.get(i)));
		}

		return strResult.toString();
	}

	private String getEntityBody(ContainerRequestContext requestContext) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = requestContext.getEntityStream();

		final StringBuilder b = new StringBuilder();
		try {
			ReaderWriter.writeTo(in, out);

			byte[] requestEntity = out.toByteArray();
			if (requestEntity.length == 0) {
				b.append("").append("\n");
			} else {
				b.append(new String(requestEntity)).append("\n");
			}
			requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

		} catch (IOException ex) {
			// Handle logging error
		}
		return b.toString();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("\n-Header:").append(responseContext.getHeaders());
		sb.append("\n-Body:").append(responseContext.getEntity());
		System.out.println("HTTP RESPONSE :" + sb.toString());
		System.out.println("end========================================");
	}
}
