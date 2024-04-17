package com.oktay.spacex.api.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*
 *  Created by oktayuyar on 16.04.2024
 */
public class ReadWriteValues {

    String path = new File("src/test/resources/capsule.properties").getAbsolutePath();
    File file = new File(path);
    Properties properties = new Properties();

    public ReadWriteValues() {
        try {
            properties.load(FileUtils.openInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData() throws IOException {
        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();

        FileWriter fos = null;
        try {
            fos = new FileWriter(file);
            properties.store(fos, "property edit");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try {
            writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
