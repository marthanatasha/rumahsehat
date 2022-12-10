package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.rumahsehatapi.models.JumlahModel;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, String> {

}
