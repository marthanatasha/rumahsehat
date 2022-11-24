package apap.tugas.akhir.rumahsehat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import apap.tugas.akhir.rumahsehat.model.DTO.AppointmentDTO;
import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.repository.AppointmentDb;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    private AppointmentDb appointmentDb;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private PasienService pasienService;

    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    public AppointmentModel getAppointmentById(String id) {
        return appointmentDb.findById(id).get();
    }

    public AppointmentModel addAppointment(AppointmentDTO appointmentDTO) {
        System.out.println("masuk service"); // TODO: debug

        // get Dokter & Pasien objects
        DokterModel chosenDokter = dokterService.getDokterById(appointmentDTO.getDokterId());
        PasienModel registeredPasien = pasienService.getPasienById(appointmentDTO.getPasienId());

        // create new appointment
        AppointmentModel newAppointment = new AppointmentModel();

        // set default values
        int count = getListAppointment().size();
        newAppointment.setKode("APT-" + (count+1));
        newAppointment.setIsDone(false);
        newAppointment.setWaktuAwal(appointmentDTO.getWaktuAwal());
        newAppointment.setDokter(chosenDokter);
        newAppointment.setPasien(registeredPasien);

        System.out.println("set default values"); // TODO: debug
        System.out.println(newAppointment.getWaktuAwal()); // TODO: debug
        System.out.println(newAppointment.getDokter().getId()); // TODO: debug
        System.out.println(newAppointment.getPasien().getId()); // TODO: debug

        // save
        return appointmentDb.save(newAppointment);
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
