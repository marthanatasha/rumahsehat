class GetDetailAppointmentDTO {
  String kode;
  bool isDone;
  String tanggal;
  String jam;
  String namaDokter;
  String kodeTagihan;

  GetDetailAppointmentDTO(this.kode, this.isDone, this.tanggal, this.jam, this.namaDokter, this.kodeTagihan);
}