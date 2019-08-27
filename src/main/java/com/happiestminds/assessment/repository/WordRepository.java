package com.happiestminds.assessment.repository;

import com.happiestminds.assessment.dao.Word;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends BaseRepository<Word,Long> {

    Optional<Word> findByQuickIndexAndWord(String quickIndex, String word);
    List<Word> findByQuickIndexAndWordStartsWith(String quickIndex,String workd);
}
