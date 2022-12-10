package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.rumahsehatapi.models.users.AdminModel;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, String> {
    AdminModel findByUsername(String username);
}
