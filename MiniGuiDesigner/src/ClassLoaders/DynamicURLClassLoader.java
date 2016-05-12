package ClassLoaders;


import java.net.URL;
import java.net.URLClassLoader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tifuivali
 */
public class DynamicURLClassLoader extends URLClassLoader{

public DynamicURLClassLoader(URLClassLoader classLoader) {
    super(classLoader.getURLs());

}

public DynamicURLClassLoader(URL[] urls)
{
    super(urls);
}

@Override
public void addURL(URL url) {
    super.addURL(url);
}

}
