package dao.impl;

import config.MyConnection;
import dao.BukuDao;
import dao.CartDao;
import database.DataCart;
import entities.Buku;
import entities.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    private static DataCart dataCart;
    private static BukuDao bukuDao = new BukuDaoImpl();

    public CartDaoImpl() {
        dataCart = new DataCart(MyConnection.makeConnection());
    }

    @Override
    public List<Cart> getAllTransaksi() {
        List<Cart> listAll = new ArrayList<>();

        try {
            listAll = dataCart.getAllTransaksi();
        } catch (Exception e){
            e.printStackTrace();
        }

        return listAll;
    }

    @Override
    public List<Cart> getInvoice(String npj) {
        List<Cart> listin = new ArrayList<>();

        try {
            listin = dataCart.getTransaksi(npj);
        } catch (Exception e){
            e.printStackTrace();
        }

        return listin;
    }

    @Override
    public void addTransaksi(String ibk, int jml, String kodetrans) {

        Buku getBuku = bukuDao.getByKodeBuku(ibk);

        Cart cart = new Cart();
        cart.setId_buku(getBuku.getId());
        cart.setQty(jml);
        cart.setTotal_harga(jml * getBuku.getHarga());
        dataCart.insertTransaksi(cart, kodetrans);

        bukuDao.updateStokBuku(ibk, jml);
    }

    @Override
    public boolean cekKetersediaan(String kode, int jumlah) {
        boolean cukup = true;
        Buku cekstok = bukuDao.getByKodeBuku(kode);
        int hasilStok = cekstok.getStok() - jumlah;
        if (hasilStok < 1){

            cukup = false; // jika lebih kecil dari 1 maka cukup false

        }
        return cukup;
    }
}
