package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FileFilmModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmFileRepositoryTest {

    @Autowired
    private FilmFileRepository repository;

    @ParameterizedTest
    @CsvSource({"wings,1927", "WINGS,1927", "WiNgS,1927", "Wings,1927"})
    @Transactional(readOnly = true)
    public void checkFindEntity(String title, String year) {
        List<FileFilmModel> films = repository.getByTitleAndYear(title, year);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(films.size(), 1);
            Assertions.assertTrue(films.get(0).isWin());
            Assertions.assertEquals(films.get(0).getFilmYear(), "1927");
            Assertions.assertEquals(films.get(0).getDescription(), ",Paramount Famous Lasky,Wings,");
            Assertions.assertNotNull(films.get(0).getId());
        });
    }

    @Test
    @Transactional
    @Rollback(true)
    public void checkDeleteAll() {
        repository.deleteAll();
        List<FileFilmModel> all = repository.findAll();
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void checkAddAll() {
        List<FileFilmModel> fileFilmModels = List.of(new FileFilmModel(null, "2010", "first", false),
                new FileFilmModel(null, "2010", "second", true)
        );
        repository.deleteAll();
        List<FileFilmModel> all = repository.saveAll(fileFilmModels);
        Assertions.assertEquals(all.size(), 2);
    }
}