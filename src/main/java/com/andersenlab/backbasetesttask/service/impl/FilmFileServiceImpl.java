package com.andersenlab.backbasetesttask.service.impl;

import com.andersenlab.backbasetesttask.model.FileFilmModel;
import com.andersenlab.backbasetesttask.repository.FilmFileRepository;
import com.andersenlab.backbasetesttask.service.FilmFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmFileServiceImpl implements FilmFileService {

    public static final String ACADEMY_AWARDS_CSV = "/academy_awards.csv";
    private final FilmFileRepository repository;

    private final static Pattern PATTERN = Pattern.compile("^(\\d{4}).+(Best Picture)(.+)(YES|NO).+",
            Pattern.CASE_INSENSITIVE);

    @Override
    @Transactional
    public boolean rewriteAll() {
        try (InputStream in = getClass().getResourceAsStream(ACADEMY_AWARDS_CSV);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            repository.deleteAll();
            repository.saveAll(getListAward(reader));
        } catch (Exception e) {
            log.error("Error processing file with awards", e);
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileFilmModel> getByNameAndYear(String name, String year) {
        return repository.getByTitleAndYear(name, year);
    }

    private boolean convertAnswer(String answer) {
        return "YES".equals(answer);
    }

    private List<FileFilmModel> getListAward(BufferedReader reader) throws IOException {
        List<FileFilmModel> listAwards = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            Matcher matcher = PATTERN.matcher(line);
            boolean b = matcher.find();
            if (b) {
                listAwards.add(new FileFilmModel(null, matcher.group(1), matcher.group(3), convertAnswer(matcher.group(4))));
            }
            line = reader.readLine();
        }
        return listAwards;
    }
}
