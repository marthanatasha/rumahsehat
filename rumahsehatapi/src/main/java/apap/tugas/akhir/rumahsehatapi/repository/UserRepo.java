package apap.tugas.akhir.rumahsehatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.tugas.akhir.rumahsehatapi.models.users.UserModel;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByEmail(String email);
}