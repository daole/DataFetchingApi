package com.dreamdigitizers.datafetchingapis.repositories;

import com.dreamdigitizers.datafetchingapis.models.MusicZing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryMusicZing extends JpaRepository<MusicZing, String> {
}
