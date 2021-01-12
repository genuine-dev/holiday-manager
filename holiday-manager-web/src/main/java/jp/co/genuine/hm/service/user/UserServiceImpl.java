package jp.co.genuine.hm.service.user;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.genuine.hm.model.user.RegisterUserRequest;
import jp.co.genuine.hm.model.user.UserList;

@Service
public class UserServiceImpl implements UserService {
	CloseableHttpClient client = HttpClients.createDefault();
	ObjectMapper mapper = new ObjectMapper();
	String contentType = "content-type";
	String headerValue = "appication/json; charset=UTF-8";
	static String API_ROOT = "http://localhost:8082/";


	@Override
	public UserList getUserList() throws IOException {
		String url = API_ROOT + "user";
		UserList result = null;

		HttpGet request = new HttpGet(url);
		request.addHeader(contentType, headerValue);
		// HTTPリクエストを実行します。 HTTPステータスが200の場合は取得したHTMLを表示します。
		try (CloseableHttpResponse response = client.execute(request);) {
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String jsonString = EntityUtils.toString(response.getEntity());
				result = mapper.readValue(jsonString, UserList.class);

			} else {
				System.out.println("200以外のステータスコードが返却されました。");
			}
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	@Override
	public void postUser(RegisterUserRequest parameter) {
		String url = API_ROOT + "user/register";
		String json;
		try {
			json = mapper.writeValueAsString(parameter);
			StringEntity entity = new StringEntity(json, "UTF-8");
			HttpPost request = new HttpPost(url);
			request.addHeader(contentType, headerValue);
			request.setEntity(entity);
			try (CloseableHttpResponse response = client.execute(request)) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					System.out.println(EntityUtils.toString(response.getEntity()));
				} else {
					System.out.println("200以外のステータスコードが返却されました。");
				}
			} catch (IOException e){

			}
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
