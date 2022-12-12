import 'dart:convert';
import 'dart:developer';
import 'package:flutter/material.dart';
import 'package:rumahsehat_flutter/pages/list_tagihan.dart';
import 'package:rumahsehat_flutter/pages/detail_resep.dart';
import 'package:rumahsehat_flutter/pages/view_profile_user.dart';
import 'package:rumahsehat_flutter/pages/viewall_appointment.dart';
import 'package:rumahsehat_flutter/pages/view_profile_user.dart';
import 'package:rumahsehat_flutter/pages/form_registrasi_pasien.dart';

import 'form_create_appointment.dart';

// TODO: Ini dummy. Kalo mau nambah nav ke fitur lain, tambahin button baru aja (yang widget ElevatedButton)
class HomeDummy extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Rumah Sehat'),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: SizedBox(
            width: double.infinity,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                const SizedBox(
                  height: 40,
                ),
                const Text(
                  'Selamat Datang di',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(
                  height: 12,
                ),
                const Text(
                  'Rumah Sehat',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 36,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(
                  height: 24,
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return FormRegistrasiPasien();
                    }));
                  },
                  child: const Text('Registrasi akun'),
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return ViewUserProfile(
                        kodePasien: "2",
                      );
                    }));
                  },
                  child: const Text('Lihat Profile User'),
                ),
                ElevatedButton(
                  onPressed: () {
                    log("message");
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return const FormCreateAppointment();
                    }));
                  },
                  child: const Text('Buat Appointment'),
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return ViewAllAppointment();
                    }));
                  },
                  child: const Text('Lihat Semua Appointment'),
                ),
                const SizedBox(
                  height: 40,
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return ViewAllTagihan();
                    }));
                  },
                  child: const Text('Detail Tagihan'),
                ),
                const SizedBox(
                  height: 40,
                ),
                // ElevatedButton(
                //   onPressed: () {
                //     Navigator.push(context,
                //         MaterialPageRoute(builder: (context) {
                //       return ViewDetailResep();
                //     }));
                //   },
                //   // hapus karna harusnya tombolnya ga ada di home, ini cm buat test
                //   child: const Text('Lihat Detail Resep'),
                // ),
                // const SizedBox(
                //   height: 40,
                // ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
