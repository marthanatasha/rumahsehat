package apap.tugas.akhir.rumahsehatapi.models.users;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "admin")

public class AdminModel extends UserModel {

}
