package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.rumahsehatapi.models.users.PasienModel;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {

}
