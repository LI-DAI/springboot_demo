/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @author lidai
 * @date 2019/4/23 13:48
 */
public class FileUtils {

    private static final String PATH = "E://test//com//hiynn//";
    private static final String HTTPS_FTL = "";
    private static final String HTTP_FTL = "";
    private static final String WEBSERVICE_FTL = "";
    private static final String DEMO_FTL = "test.ftl";
    private static final String API_DOCUMENT_FTL = "";

    public static void generatorToJava(int type, Object data, String fileName) throws IOException, TemplateException {
        String path = PATH + File.separator + fileName;
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        Template template = getTemplate(type);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)), 2048)) {
            //写文件
            template.process(data, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取模板
     *
     * @param type
     * @return
     * @throws IOException
     */
    public static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarkConfigUtils.TYPE_HTTP:
                return FreemarkConfigUtils.getInstance().getTemplate(HTTP_FTL);
            case FreemarkConfigUtils.TYPE_HTTPS:
                return FreemarkConfigUtils.getInstance().getTemplate(HTTPS_FTL);
            case FreemarkConfigUtils.TYPE_WEBSERVICE:
                return FreemarkConfigUtils.getInstance().getTemplate(WEBSERVICE_FTL);
            case FreemarkConfigUtils.TYPE_API_DOCUMENT:
                return FreemarkConfigUtils.getInstance().getTemplate(API_DOCUMENT_FTL);
            case FreemarkConfigUtils.TYPE_DEMO:
                return FreemarkConfigUtils.getInstance().getTemplate(DEMO_FTL);
            default:
                return null;
        }
    }
}

