package cn.fanyetu.bootdemo.importd;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhanghaonan
 * @date 2018/8/16
 */
public class ImportBeanRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder beanDefDinosaur = BeanDefinitionBuilder.rootBeanDefinition(Dinosaur.class);
        beanDefinitionRegistry.registerBeanDefinition("dinosaur", beanDefDinosaur.getBeanDefinition());

        BeanDefinitionBuilder beanDefPhoenix = BeanDefinitionBuilder.rootBeanDefinition(Phoenix.class);
        beanDefinitionRegistry.registerBeanDefinition("phoenix", beanDefPhoenix.getBeanDefinition());
    }
}
