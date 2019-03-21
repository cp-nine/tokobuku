package dao;

import entities.Cart;

import java.util.List;

public interface CartDao {

    List<Cart> getAllTransaksi();

    List<Cart> getInvoice(String npj);

    void addTransaksi(String ibk, int jml, String kodetrans);

//    boolean cekStokBuku(String kode);
    boolean cekKetersediaan(String kode, int jumlah);

}
