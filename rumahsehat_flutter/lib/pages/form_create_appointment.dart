import 'package:flutter/material.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'package:intl/intl.dart';

class FormCreateAppointment extends StatefulWidget {
  const FormCreateAppointment({super.key});

  @override
  _FormCreateAppointment createState() => _FormCreateAppointment();
}

class _FormCreateAppointment extends State<FormCreateAppointment> {
  final _formKey = GlobalKey<FormState>();
  final dateTimeController = TextEditingController();
  List<String> doctorOptions = ['doctor 1', 'doctor 2', 'doctor 3', 'doctor 4']; // TODO: Tembak API
  // Form values
  DateTime? chosenDateTime;
  String? chosenDoctor = 'doctor 1'; // TODO: Hrs nya gak hard code

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget> [
              const Text(
                'Membuat Appointment',
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 12),
              const Text(
                'Pilih waktu dan dokter untuk sesi appointment Anda.',
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 16,
                ),
              ),
              const SizedBox(height: 20),
              TextFormField(
                controller: dateTimeController,
                decoration: const InputDecoration(
                  hintText: 'Pilih tanggal & jam',
                  labelText: 'Waktu Appointment',
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Pilih waktu appointment!';
                  }
                  return null;
                },
                onTap: () async {
                  FocusScope.of(context).requestFocus(FocusNode());
                  await DatePicker.showDateTimePicker(
                    context,
                    showTitleActions: true,
                    minTime: DateTime.now(),
                    currentTime: DateTime.now(),
                    locale: LocaleType.en,
                    onConfirm: (date) {
                      dateTimeController.text = DateFormat('dd-MM-yyyy hh:mm').format(date);
                      chosenDateTime = date;
                    },
                  );
                },
              ),
              const SizedBox(height: 12),
              DropdownButton(
                isExpanded: true,
                icon: const Icon(Icons.keyboard_arrow_down),
                items: doctorOptions.map((String doctorName) {
                  return DropdownMenuItem(
                    value: doctorName,
                    child: Text(doctorName),
                  );
                }).toList(),
                value: chosenDoctor,
                onChanged: (String? newDoctor) {
                  setState(() {
                    chosenDoctor = newDoctor!;
                  });
                },
                ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget> [
                  Container(
                    padding: const EdgeInsets.only(top: 12),
                    child: OutlinedButton(
                      onPressed: () {}, // TODO: Navigator pop
                      child: const Text('Cancel'),
                    )
                  ),
                  const SizedBox(width: 12,),
                  Container(
                    padding: const EdgeInsets.only(top: 12),
                    child: ElevatedButton(
                      onPressed: () {
                        if (_formKey.currentState!.validate()) {
                          showDialog (
                            context: context,
                            builder: (BuildContext context) {
                              return AlertDialog(
                                title: const Text('Belom Siap Bang'),
                                content: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: <Widget> [
                                    Text('Waktu: ${DateFormat('dd-MM-yyyy hh:mm').format(chosenDateTime!)}'),
                                    Text('Dokter: ${chosenDoctor!}'),
                                  ],
                                ),
                                actions: <Widget> [
                                  TextButton(
                                    child: const Text('Oke deh bang'),
                                    onPressed: () {
                                      Navigator.of(context).pop();
                                    },
                                  ),
                                ],
                              );
                            }
                          );
                        }
                      },
                      child: const Text('Submit'),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}