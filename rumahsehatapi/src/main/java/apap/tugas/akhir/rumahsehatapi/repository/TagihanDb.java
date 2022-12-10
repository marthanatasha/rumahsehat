package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.rumahsehatapi.models.TagihanModel;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {

}
