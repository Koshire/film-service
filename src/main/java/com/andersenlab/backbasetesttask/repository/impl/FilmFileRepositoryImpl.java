package com.andersenlab.backbasetesttask.repository.impl;

import com.andersenlab.backbasetesttask.model.FileFilmModel;
import com.andersenlab.backbasetesttask.repository.FullTextFilmFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class FilmFileRepositoryImpl implements FullTextFilmFileRepository {

    private final EntityManager em;

    @Override
    public List<FileFilmModel> getByTitleAndYear(String title, String year) {
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(em);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(FileFilmModel.class)
                .get();

        Query dscrQuery = qb.phrase()
                .onField("description")
                .sentence(title)
                .createQuery();


        BooleanJunction finalQuery = qb.bool()
                .should(dscrQuery);

        if (Objects.nonNull(year)) {
            finalQuery.should(qb.simpleQueryString()
                    .onField("description")
                    .matching(year)
                    .createQuery());
        }

        FullTextQuery fullTextQuery = fullTextEntityManager
                .createFullTextQuery(finalQuery.createQuery(), FileFilmModel.class);

    return (List<FileFilmModel>) fullTextQuery.getResultList();
    }
}
