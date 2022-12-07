import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:rumahsehat_flutter/pages/viewall_appointment.dart';

import 'form_create_appointment.dart';

// TODO: Ini dummy. Kalo mau nambah nav ke fitur lain, tambahin button baru aja (yang widget ElevatedButton)
class HomeDummy extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold (
      appBar: AppBar(
        title: const Text('Rumah Sehat'),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: SizedBox (
            width: double.infinity,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget> [
                const SizedBox(height: 40,),
                const Text(
                  'Selamat Datang di',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 12,),
                const Text(
                  'Rumah Sehat',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 36,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 24,),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) {
                        return const FormCreateAppointment();
                      })
                    );
                  },
                  child: const Text('Buat Appointment'),
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) {
                          return ViewAllAppointment();
                        })
                    );
                  },
                  child: const Text('Lihat Semua Appointment'),
                ),
                const SizedBox(height: 40,),
              ],
            ),
          ),
        ),
      ),
    );
  }
}