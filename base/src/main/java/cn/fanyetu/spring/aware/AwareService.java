package cn.fanyetu.spring.aware;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * ʵ��spring��awareϵ�нӿڣ�ʵ��ע��spring��������Ĺ���
 * 
 * ��spring�н���ϵĹ�ϵ
 * 
 * @author zhanghaonan
 * 
 */
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

	private String beanName;
	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader arg0) {
		this.resourceLoader = arg0;
	}

	@Override
	public void setBeanName(String arg0) {
		this.beanName = arg0;
	}

	public void outputResult() {
		System.out.println("Bean������Ϊ:" + beanName);
		Resource resource = resourceLoader.getResource("classpath:cn/fanyetu/spring/aware/test.txt");
		
		try {
			System.out.println("ResourceLoader���ص��ļ�����Ϊ��"+IOUtils.toString(resource.getInputStream()));
		} catch (IOException e) {
			System.out.println("io�쳣");
		}
	}

}
