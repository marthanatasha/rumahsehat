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

    public AppointmentModel getAppointmentById(String id) {
        return appointmentDb.findById(id).get();
    }

    public AppointmentModel addAppointment(AppointmentModel appointment) {
        // set default values
        int count = getListAppointment().size();
        appointment.setKode("APT-" + (count+1));
        appointment.setIsDone(false);

        // save
        return appointmentDb.save(appointment);
    }

    public AppointmentModel updateAppointment(AppointmentModel appointment) {
        appointmentDb.save(appointment);
        return appointment;
    }

    public AppointmentModel deleteAppointment(AppointmentModel appointment) {
        appointmentDb.delete(appointment);
        return appointment;
    }
}
