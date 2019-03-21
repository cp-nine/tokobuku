package controller;

import config.BorderPading;
import dao.BukuDao;
import dao.impl.BukuDaoImpl;
import entities.Buku;
import entities.Cart;

public class BukuController extends BorderPading {

    private static BukuDao bukuDao = new BukuDaoImpl();

    public static void getAllBuku(){

        Cart tr = new Cart();

        int no = 1; // create list number

        border(46); // create horizontal border table
        System.out.println("| " + padRight("No", 5)
                + "| " + padRight("Kode", 8)
                + "| " + padRight("Judul Book", 20)
                + "| " + padRight("Penulis", 15)
                + "| " + padRight("Penerbit", 10)
                + "| " + padRight("Harga", 13)
                + "| " + padRight("Stok", 5)
                + " |"
        );
        border(46);
//-----------------------------------------
        // looping data to print all list, referrece of ServiceBook
        for (Buku lbk : bukuDao.getAll()) {

            System.out.println("| " + padRight("" + no + "", 5)
                    + "| " + padRight(lbk.getKode_buku(), 8)
                    + "| " + padRight(lbk.getJudul(), 20)
                    + "| " + padRight(lbk.getPenulis(), 15)
                    + "| " + padRight(lbk.getPenerbit(), 10)
                    + "| Rp." + padRight(String.valueOf(lbk.getHarga()), 10)
                    + "| " + padRight(String.valueOf(lbk.getStok()), 5)
                    + " |"
            );

            no++;
        }
//-----------------------------------------
        border(46);

    }

    public static void detailBuku(String kode){

        if (bukuDao.getByKodeBuku(kode) != null){
            System.out.println("========= Detail Buku ==========");

            Buku sbk = bukuDao.getByKodeBuku(kode); // call service get buku to be value of instace object
            System.out.println("Kode       : " + sbk.getKode_buku());
            System.out.println("Judul Book : " + sbk.getJudul());
            System.out.println("Penulis    : " + sbk.getPenulis());
            System.out.println("Penerbit   : " + sbk.getPenerbit());
            System.out.println("Harga      : Rp." + sbk.getHarga());
            System.out.println("Stok       : " + sbk.getStok());
        } else {
            System.out.println("Buku dengan Kode: "+kode+" tidak ditemukan.");
        }

    }

    public static void addBuku(Buku buku){
        bukuDao.addBuku(buku);
    }

    public static void updateBuku(Buku buku){
        bukuDao.updateBuku(buku);
    }

    public static void deleteBuku(String kodebuku){
        bukuDao.deleteBuku(kodebuku);
    }

    public static void restoreBuku(String kodebuku){
        bukuDao.restoreBuku(kodebuku);
    }

    // cek buku
    public boolean isEmpty(String kode){
        if (bukuDao.getByKodeBuku(kode) == null) {
            return false;
        }
        return true;
    }
}
