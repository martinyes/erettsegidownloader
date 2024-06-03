package com.github.martin;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class Downloader {

    private static final String BASE_URL = "https://dload-oktatas.educatio.hu/erettsegi/feladatok_";
    private static final Function<InputData, String> LEVEL_FUNC = (inputData -> inputData.isAdvancedLvl() ? "emelt" : "kozep");
    private static final String[] SEASONS = {"tavasz", "osz"};
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    public void downloadAll(InputData data) {
        var subject = data.getSubject().id();
        var path = data.getDownloadPath();

        for (int year = 2013; year <= 2022; year++)
            for (String season : SEASONS) download(season, year, LEVEL_FUNC.apply(data), subject, path);
    }

    public void downloadSingle(InputData data) {
        var subject = data.getSubject().id();
        var path = data.getDownloadPath();
        var year = data.getYear();
        var season = data.getSeason();

        download(season, year, LEVEL_FUNC.apply(data), subject, path);
    }

    private void download(String season, int year, String level, String subject, String pathToSave) {
        var month = season.equals("tavasz") ? "maj" : "okt";
        var url = BASE_URL + year + season + "_" + level + "/"
                + level.charAt(0) + subject + "_" + Integer.toString(year).substring(2)
                + month + "_fl.pdf";
        var fileName = subject + "_" + year + "_" + month + "_" + level + ".pdf";
        var file = new File(pathToSave, fileName);
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = HTTP_CLIENT.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                FileUtils.copyInputStreamToFile(entity.getContent(), file);
                EntityUtils.consume(entity);
                System.out.println("Sikeres letöltés: " + url);
            }
        } catch (IOException e) {
            System.err.println("Hiba történt a letöltés során: " + url);
            e.printStackTrace();
        }
    }
}