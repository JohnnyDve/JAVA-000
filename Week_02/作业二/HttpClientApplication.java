import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @Description 打印请求内容
 * @Package cn.icting.client
 * @Author yanweiqiang
 * @Date 2020/10/28 20:45
 */
public class HttpClientApplication {
    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            httpResponse.getEntity().writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
