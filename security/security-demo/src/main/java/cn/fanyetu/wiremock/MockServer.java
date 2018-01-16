package cn.fanyetu.wiremock;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * 配置wrieMoke服务器
 *
 * @author zhanghaonan
 * @date 2018/1/16
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        // 查找到wireMock服务
        configureFor(9092);
        // 清空之前的所有配置
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    /**
     * 模拟服务
     * @param url
     * @param filename
     * @throws IOException
     */
    private static void mock(String url, String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + filename + ".json");
        String content = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8);
        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
