package com.dreamdigitizers.datafetchingapis.repositories;

import com.dreamdigitizers.datafetchingapis.models.MusicNct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryMusicNct extends JpaRepository<MusicNct, String> {
}
