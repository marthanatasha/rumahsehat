import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import '../DTO/GetAppointmentDTO.dart';

class ViewAllAppointment extends StatelessWidget {
  List<GetAppointmentDTO> listApt = [];
  // bool runGetAllAppointment = false;

  // Function to get list of Appointment
  Future getAppointment(String pasienId) async {
    var response = await http.get(Uri.parse('http://10.0.2.2:8080/api/v1/appointment/$pasienId'));
    var jsonData = jsonDecode(response.body);
    print(response.body); // TODO: debug
    print('done'); // TODO: debug
    print('list apt: $listApt'); // TODO: debug
    
    listApt = [];
    for (var a in jsonData) {
      print('loop'); // TODO: debug
      var raw = a["waktuAwal"].split('T');
      GetAppointmentDTO apt = GetAppointmentDTO(a["kode"], a["dokter"]["nama"], raw[0], raw[1], a["isDone"]);
      print('get dokter ${apt.kode}'); // TODO: debug
      listApt.add(apt);
    }
    print('list apt: $listApt'); // TODO: debug
    return listApt;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Rumah Sehat'),
        centerTitle: true,
      ),
      body: FutureBuilder(
        future: getAppointment("pasien1"), // TODO: pasienId masih hard code
        builder: (context, snapshot) {
          if (snapshot.data == null) {
            return SafeArea(
                child: Padding(
                  padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: const <Widget> [
                      Text(
                        'Daftar Appointment',
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
                        'Mencari appointment Anda.',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontSize: 16,
                        ),
                      ),
                    ],
                  ),
                )
            );
          } else if (snapshot.data.length == 0) {
            return SafeArea(
                child: Padding(
                  padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
                  child: SizedBox (
                    width: double.infinity,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget> [
                        const Text(
                          'Daftar Appointment',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            fontSize: 24,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 20,),
                        const Text(
                          'Anda belum memiliki appointment.',
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
          } else {
            return SafeArea(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 24, horizontal: 16),
                child: Column(
                  children: <Widget> [
                    const Text(
                      'Daftar Appointment',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 12,),
                    Expanded(
                      child: ListView.separated(
                        itemCount: snapshot.data.length,
                        separatorBuilder: (BuildContext context, int index) => const SizedBox(height: 12,),
                        itemBuilder: (context, index) {
                          final GetAppointmentDTO aptNow = snapshot.data[index];
                          return InkWell(
                            onTap: () {}, // TODO: navigation push ke page detail appointment
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
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: <Widget> [
                                    Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: <Widget> [
                                        Text(
                                          'Appointment ${aptNow.kode}',
                                          style: const TextStyle(
                                            fontSize: 20,
                                            fontWeight: FontWeight.w500,
                                          ),
                                        ),
                                        const SizedBox(height: 12,),
                                        Text(
                                          'Dokter: ${aptNow.namaDokter}',
                                          style: const TextStyle(
                                            fontSize: 16
                                          ),
                                        ),
                                        const SizedBox(height: 4,),
                                        Text(
                                          'Tanggal: ${aptNow.tanggal}',
                                          style: const TextStyle(
                                              fontSize: 16
                                          ),
                                        ),
                                        const SizedBox(height: 4,),
                                        Text(
                                          'Jam: ${aptNow.jam}',
                                          style: const TextStyle(
                                              fontSize: 16
                                          ),
                                        ),
                                        const SizedBox(height: 4,),
                                        Text(
                                          aptNow.isDone ? 'Status: Is Done' : 'Status: Is Not Done',
                                          style: const TextStyle(
                                              fontSize: 16
                                          ),
                                        ),
                                      ],
                                    ),
                                    IconButton(
                                      icon: const Icon(Icons.chevron_right_outlined),
                                      iconSize: 28,
                                      color: Colors.green,
                                      splashColor: Colors.green.withOpacity(0.3),
                                      onPressed: () {}, // TODO: navigation push ke page detail appointment
                                    ),
                                  ],
                                ),
                              ),
                            ),
                          );
                        }
                      ),
                    ),
                  ],
                ),
              ),
            );
          }
        },
      ),
    );
  }
}