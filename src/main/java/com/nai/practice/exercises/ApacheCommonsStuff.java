
package com.nai.practice.exercises;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Andras_Istvan_Nagy on 11/21/2016.
 */
public class ApacheCommonsStuff {
    public static void main(String[] args) throws Exception {
        String fileSep = System.getProperty("file.separator");
        String filePath =
                StringUtils.joinWith(fileSep, System.getProperty("user.dir"), "src", "main", "resources", "whatever");
        URL url = new File(filePath).toURI().toURL();
        System.out.println(filePath);
        System.out.println(url);

        String whatever = null;
        try (InputStream is = url.openStream()) {
            whatever = IOUtils.toString(is);
        }

        System.out.println(whatever);


    }
}
