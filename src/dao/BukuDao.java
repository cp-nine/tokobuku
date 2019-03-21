package dao;

import entities.Buku;

import java.util.List;

public interface BukuDao {

    List<Buku> getAll();
    Buku getById(Integer id);
    Buku getByKodeBuku(String kode);
    void addBuku(Buku buku);
    void updateStokBuku(String kode, int jumlah);

    void updateBuku(Buku buku);
    void deleteBuku(String kode);
    void restoreBuku(String kode);

}
