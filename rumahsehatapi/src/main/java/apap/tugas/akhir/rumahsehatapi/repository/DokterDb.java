package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.rumahsehatapi.models.users.DokterModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, String> {

}
