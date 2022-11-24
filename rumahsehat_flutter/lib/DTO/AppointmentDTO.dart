class AppointmentDTO {
  final DateTime waktuAwal;
  final String dokterId;
  final String pasienId;

  AppointmentDTO(this.waktuAwal, this.dokterId, this.pasienId);

  Map<String, dynamic> toJson() {
    return {'waktu_awal':waktuAwal.toIso8601String(), 'dokter_id': dokterId, 'pasien_id': pasienId};
  }
}