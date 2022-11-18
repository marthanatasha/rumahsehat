package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.repository.AppointmentDb;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    private AppointmentDb appointmentDb;

    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }
}