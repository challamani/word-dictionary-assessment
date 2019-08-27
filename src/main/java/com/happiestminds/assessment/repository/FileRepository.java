package com.happiestminds.assessment.repository;

import com.happiestminds.assessment.dao.File;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends BaseRepository<File,Long> {
    Optional<File> findByName(String name);
}
