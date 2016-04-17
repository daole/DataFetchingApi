package com.dreamdigitizers.datafetchingapis.controllers.repositories.zing;

import com.dreamdigitizers.datafetchingapis.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMusicZingRepository extends JpaRepository<Song, String> {
}
