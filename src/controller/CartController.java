package controller;

import config.BorderPading;
import dao.BukuDao;
import dao.CartDao;
import dao.impl.BukuDaoImpl;
import dao.impl.CartDaoImpl;
import entities.Buku;
import entities.Cart;

public class CartController extends BorderPading {

    private static CartDao transaksi = new CartDaoImpl();
    private static BukuDao buku = new BukuDaoImpl();

    // get all transaksi
    public void allTransaksi() {

        if (transaksi.getAllTransaksi() != null) {
            //-----------------------------------------
            //  Head of table
            border(31);
            System.out.println("| " + padRight("No", 5)
                    + "| " + padRight("No Transaksi", 15)
                    + "| " + padRight("Qty", 5)
                    + "| " + padRight("Total Transaksi", 16)
                    + "| " + padRight("Tanggal", 10)
                    + " |"
            );
            border(31);
            //-----------------------------------------

            int total = 0; // set default totalHarga = 0

            // loop as much cart
            int i = 1;
            for (Cart trans : transaksi.getAllTransaksi()) {

//                Buku tbuku = buku.getById(trans.getId_buku());

                System.out.println("| " + padRight("" + (i++) + "", 5)
                        + "| " + padRight(trans.getNo_transaksi(), 15)
                        + "| " + padRight(String.valueOf(trans.getQty()), 5)
                        + "| Rp." + padRight(trans.getTotal_harga().toString(), 13)
                        + "| " + padRight(trans.getTgl_transaksi(), 10)
                        + " |"
                );

                total += trans.getTotal_harga(); // get total harga

            }
            //-----------------------------------------
            border(31);
            System.out.println("| " + padRight("Total Harga", 47)
                    + "| " + padRight("Rp." + total, 10)
                    + " |"
            );
            border(31);

        } else {
            System.out.println("Data tidak tersedia");
        }
    }

    // get invoice
    public void getInvoice(String kodePenjualan) {

        if (transaksi.getInvoice(kodePenjualan)==null || transaksi.getInvoice(kodePenjualan).size()== 0){
            System.out.println("Kode transaksi "+kodePenjualan+" tidak ditemukan");
        } else {
            //-----------------------------------------
            //  Head of table
            border(45);
            System.out.println("| " + padRight("Kode Transaksi", 15)
                    + "| " + padRight(kodePenjualan, 70)
                    + " |"
            );
            border(45);
            System.out.println("| " + padRight("No", 5)
                    + "| " + padRight("Produk", 40)
                    + "| " + padRight("Qty", 5)
                    + "| " + padRight("Harga", 15)
                    + "| " + padRight("Sub Harga", 13)
                    + " |"
            );
            border(45);
//-----------------------------------------

            int total = 0; // set default totalHarga = 0

            // loop as much cart
            int i = 1;
            for (Cart trans : transaksi.getInvoice(kodePenjualan)) {

                Buku tbuku = buku.getById(trans.getId_buku());

                System.out.println("| " + padRight("" + (i++) + "", 5)
                        + "| " + padRight(tbuku.getKode_buku() + " - " + tbuku.getJudul(), 40)
                        + "| " + padRight(String.valueOf(trans.getQty()), 5)
                        + "| " + padRight(String.valueOf(tbuku.getHarga()), 15)
                        + "| Rp." + padRight(trans.getTotal_harga().toString(), 10)
                        + " |"
                );

                total += trans.getTotal_harga(); // get total harga

            }
//-----------------------------------------
            border(45);
            System.out.println("| " + padRight("Total Harga", 71)
                    + "| " + padRight("Rp." + total, 13)
                    + " |"
            );
            border(45);
        }

    }

    // add transaksi
    public void addTransaksi(String kodeBuku, int jumlah, String kodeTrans) {
        transaksi.addTransaksi(kodeBuku, jumlah, kodeTrans);
    }

    public boolean tersedia(String kodeBuku, int jumlah) {
        boolean tersedia = true;

        if (!transaksi.cekKetersediaan(kodeBuku, jumlah)) {
            tersedia = false;

        }

        return tersedia;
    }

}
