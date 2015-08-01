package com.dimit;

import java.io.IOException;

import org.junit.Test;

import com.dimit.search.resolver.PathMatchingResourcePatternResolver;
import com.dimit.search.resolver.ResourcePatternResolver;
import com.dimit.search.resource.Resource;
import com.dimit.search.test.anno.Abc;
import com.dimit.search.util.ClassUtils;
import com.dimit.search.util.StringUtils;

public class SearchTest {

	/**
	 * 收索指定包下的类
	 */
	@Test
	public void test() {
		ResourcePatternResolver search = new PathMatchingResourcePatternResolver();
		String path = search.resolveBasePackage("com.**.test.filter");
		try {
			Resource[] resources = search.getResources(path);
			for(Resource r : resources) {
				String name = StringUtils.resolverpackage(r.getFile().getAbsolutePath());
				Class<?> clz = ClassUtils.forName(name, ClassUtils.getDefaultClassLoader());
				Abc abc = clz.getAnnotation(Abc.class);
				System.out.println(abc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (LinkageError e) {
			e.printStackTrace();
		} 
	}
	
	

}
