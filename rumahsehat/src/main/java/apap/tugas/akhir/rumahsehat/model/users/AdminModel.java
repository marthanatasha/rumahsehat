package apap.tugas.akhir.rumahsehat.model.users;

import java.io.Serializable;

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
