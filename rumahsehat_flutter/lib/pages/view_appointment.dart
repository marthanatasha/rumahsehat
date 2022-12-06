import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumahsehat_flutter/DTO/GetDetailAppointmentDTO.dart';

import 'package:http/http.dart' as http;

class ViewAppointment extends StatelessWidget {
  late final String kodeApt;
  ViewAppointment({required this.kodeApt});

  late GetDetailAppointmentDTO aptDetails;

  // Function to get appointment details
  Future getDetailAppointment(String kodeApt) async {
    var response = await http.get(Uri.parse('http://10.0.2.2:8080/api/v1/appointment/detail/$kodeApt'));
    var jsonData = jsonDecode(response.body);

    if (response.statusCode == 200) {
      var raw = jsonData["waktuAwal"].split('T');
      String kodeTagihan = "";
      if (jsonData["tagihan"] != null) {
        kodeTagihan = jsonData["tagihan"]["kode"];
      } else {
        kodeTagihan = "NO TAGIHAN";
      }
      aptDetails = GetDetailAppointmentDTO(jsonData["kode"], jsonData["isDone"], raw[0], raw[1], jsonData["dokter"]["nama"], kodeTagihan);
      return aptDetails;

    } else {
      return false;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Rumah Sehat'),
        centerTitle: true,
      ),
      body: FutureBuilder(
        future: getDetailAppointment(kodeApt),
        builder: (context, snapshot) {
          if (snapshot.data == false) {
            return SafeArea(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
                child: SizedBox (
                  width: double.infinity,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget> [
                      const Text(
                        'Not Found: 404',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontSize: 24,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 20,),
                      const Text(
                        'Ada kesalahan dalam fetch API.',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontSize: 16,
                        ),
                      ),
                      Container(
                          padding: const EdgeInsets.only(top: 12),
                          child: ElevatedButton(
                            onPressed: () {
                              Navigator.pop(context);
                            },
                            child: const Text('Back to Home'),
                          )
                      ),
                    ],
                  ),
                ),
              )
            );
          } else if (snapshot.data == null) {
            return SafeArea(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: const <Widget> [
                    Text(
                      'Detail Appointment',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    SizedBox(height: 20),
                    LinearProgressIndicator(),
                    SizedBox(height: 20,),
                    Text(
                      'Membaca detail appointment Anda.',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ],
                ),
              )
            );
          } else {
            return SafeArea(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 24, horizontal: 16),
                child: SizedBox(
                  width: double.infinity,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget> [
                      const Text(
                        'Detail Appointment',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontSize: 24,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 24,),
                      SizedBox(
                        width: double.infinity,
                        child: Card(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15.0),
                            side: const BorderSide(
                              color: Colors.black12,
                            ),
                          ),
                          elevation: 20,
                          shadowColor: Colors.black26,
                          child: Padding(
                            padding: const EdgeInsets.all(16),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: <Widget> [
                                Text(
                                  'Kode: ${aptDetails.kode}',
                                  style: const TextStyle(
                                      fontSize: 16
                                  ),
                                ),
                                const SizedBox(height: 8,),
                                Text(
                                  'Dokter: ${aptDetails.namaDokter}',
                                  style: const TextStyle(
                                      fontSize: 16
                                  ),
                                ),
                                const SizedBox(height: 8,),
                                Text(
                                  'Tanggal: ${aptDetails.tanggal}',
                                  style: const TextStyle(
                                      fontSize: 16
                                  ),
                                ),
                                const SizedBox(height: 8,),
                                Text(
                                  'Jam: ${aptDetails.jam}',
                                  style: const TextStyle(
                                      fontSize: 16
                                  ),
                                ),
                                const SizedBox(height: 8,),
                                Text(
                                  aptDetails.isDone ? 'Status: Is Done' : 'Status: Is Not Done',
                                  style: const TextStyle(
                                      fontSize: 16
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ),
                      ),
                      Row(
                        children: <Widget> [
                          Expanded(
                            child: Container(
                              padding: const EdgeInsets.only(top: 12),
                              child: OutlinedButton(
                                onPressed: () {
                                  Navigator.pop(context);
                                },
                                child: const Text('Back'),
                              )
                            ),
                          ),
                          const SizedBox(width: 12,),
                          Expanded(
                            child: ButtonTagihan(kodeTagihan: aptDetails.kodeTagihan),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            );
          }
        },
      ),
    );
  }
}

class ButtonTagihan extends StatefulWidget {
  late final String kodeTagihan;
  ButtonTagihan({required this.kodeTagihan});

  _ButtonTagihan createState() => _ButtonTagihan(kodeTagihan: kodeTagihan);
}

class _ButtonTagihan extends State<ButtonTagihan> {
  late final String kodeTagihan;
  _ButtonTagihan({required this.kodeTagihan});

  Widget build(BuildContext context) {
    if (kodeTagihan == "NO TAGIHAN") {
      return Container(
        padding: const EdgeInsets.only(top: 12),
        child: const ElevatedButton(
          onPressed: null,
          child: Text('Tidak Ada Tagihan'),
        )
      );
    } else {
      return Container(
        padding: const EdgeInsets.only(top: 12),
        child: ElevatedButton(
          onPressed: () {}, // TODO: navigate to detail tagihan
          child: const Text('Lihat Tagihan'),
        )
      );
    }
  }
}