import config.Values;
import controller.BukuController;
import controller.CartController;
import entities.Buku;
import entities.CartTemporer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    static BukuController bukuController = new BukuController();
    static CartController cartController = new CartController();

    public static void main(String[] args) {

        mainMenu();

    }

    static void mainMenu() {
        boolean isExit = false;
        try {
            do {
                // data buku
                bukuController.getAllBuku();

                System.out.println("============ Main Menu ============");
                System.out.println();
                System.out.println("1. Detail Buku  |  5. Restore Buku");
                System.out.println("2. Tambah Buku  |  6. Transaksi");
                System.out.println("3. Update Buku  |  7. Data Transaksi");
                System.out.println("4. Delete Buku  |  8. Keluar..");
                System.out.println();
                System.out.println("===================================");

                try {

                    System.out.print("Pilih menu : ");
                    int menuSelect = Integer.parseInt(input.readLine());
                    if (menuSelect == 8) {
                        isExit = true;
                    } else {
                        if (menuSelect == 1) {
                            System.out.println();
                            System.out.print("Kode Buku : ");
                            String kode = input.readLine();
                            detailBuku(kode);
                        } else if (menuSelect == 2) {
                            addBuku();
                        } else if (menuSelect == 3) {
                            editBuku();
                        } else if (menuSelect == 4) {
                            System.out.println();
                            System.out.print("Masukan Kode Buku : ");
                            String kodeHapus = input.readLine();
                            System.out.print("Apakah anda yakin akan menghapus buku ini ? (y/n) : ");
                            String confirm = input.readLine();
                            if(confirm=="y"){
                                deleteBuku(kodeHapus);
                            } else if(confirm=="n"){
                                System.out.println("Kembali ke mnu utama!!");
                            } else {
                                System.out.println("Inputan salah, hapu buku gagal..!!!");
                            }
                        } else if (menuSelect == 5) {
                            System.out.println();
                            System.out.print("Masukan Kode Buku : ");
                            String kodeRestore = input.readLine();
                            System.out.print("Apakah anda yakin akan merestore buku ini ? (y/n) : ");
                            String confirm = input.readLine();
                            if(confirm=="y"){
                                restoreBuku(kodeRestore);
                            } else if(confirm=="n"){
                                System.out.println("Kembali ke mnu utama!!");
                            } else {
                                System.out.println("Inputan salah, restore buku gagal..!!!");
                            }
                        } else if (menuSelect == 6) {
                            System.out.println("Lakukan Transaksi ");
                            addTransaksi();
                        } else if (menuSelect == 7) {
                            System.out.println("Data Transaksi");
                            transaksi();
                        }

                        System.out.print("Ketik 8 untuk keluar, ketik 0 untuk ke menu utama : ");
                        menuSelect = Integer.parseInt(input.readLine());
                        if (menuSelect == 8)
                            isExit = true;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
//                    System.out.println("Masukan sesuai menu..!!");
                }

            } while (!isExit);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // menu 1
    static void detailBuku(String kodeBuku) {
        BukuController.detailBuku(kodeBuku);
    }

    // menu 2
    static void addBuku() {

        try {
            System.out.println("Masukan data buku !");
            System.out.println("Judul Buku : ");
            String judul = input.readLine();
            System.out.println("Penulis : ");
            String penulis = input.readLine();
            System.out.println("Penerbit : ");
            String penerbit = input.readLine();
            System.out.println("Harga : ");
            int harga = Integer.parseInt(input.readLine());
            System.out.println("Stok  : ");
            int stok = Integer.parseInt(input.readLine());

            Buku nBuku = new Buku();
            nBuku.setJudul(judul);
            nBuku.setPenulis(penulis);
            nBuku.setPenerbit(penerbit);
            nBuku.setHarga(harga);
            nBuku.setStok(stok);

            bukuController.addBuku(nBuku);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void editBuku() {

        try {
            System.out.println("Masukan data buku !");
            System.out.println("Kode Buku : ");
            String kode = input.readLine();

            if(bukuController.isEmpty(kode)){
                System.out.println("Judul Buku : ");
                String judul = input.readLine();
                System.out.println("Penulis : ");
                String penulis = input.readLine();
                System.out.println("Penerbit : ");
                String penerbit = input.readLine();
                System.out.println("Harga : ");
                int harga = Integer.parseInt(input.readLine());
                System.out.println("Stok  : ");
                int stok = Integer.parseInt(input.readLine());

                Buku uBuku = new Buku();
                uBuku.setKode_buku(kode);
                uBuku.setJudul(judul);
                uBuku.setPenulis(penulis);
                uBuku.setPenerbit(penerbit);
                uBuku.setHarga(harga);
                uBuku.setStok(stok);

                bukuController.updateBuku(uBuku);

            } else {
                notFound(kode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void deleteBuku(String kodeBuku) {
        if(bukuController.isEmpty(kodeBuku)){
            BukuController.deleteBuku(kodeBuku);
        } else {
            notFound(kodeBuku);
        }
    }

    static void restoreBuku(String kodeBuku) {
        if(bukuController.isEmpty(kodeBuku)){
            BukuController.restoreBuku(kodeBuku);
        } else {
            notFound(kodeBuku);
        }
    }

    static void addTransaksi() {

        List<CartTemporer> trans = new ArrayList<>();

        // get random int to create kode transaksi
        int number = new Random().nextInt(1000 + 5000);
        String kode_trans = "TR-" + String.valueOf(number);

        boolean keluar = false;
        do {

            try {
                System.out.println("Masukan Kode Buku dan Jumlah buku yang ingin anda beli !");
                System.out.println("Kode Buku : ");
                String kode = input.readLine();

                if(bukuController.isEmpty(kode)){

                    System.out.println("Jumlah : ");
                    String jumlah = input.readLine();
                    if (Values.isNumeric(jumlah)){
                        int kuantitas = Integer.parseInt(jumlah);
                        if(kuantitas > 0) {

                            if (cartController.tersedia(kode, kuantitas)) {
                                CartTemporer intrans = new CartTemporer();
                                intrans.setKode_buku(kode);
                                intrans.setJumlah(kuantitas);
                                trans.add(intrans);
                            } else {
                                System.out.println("Mohon maaf stok tidak mencukupi");
                            }
                        } else {
                            System.out.println("Minimal pebelian adalah 1");
                        }
                    } else {
                        System.out.println("Masukan Jumlah Buku...!!");
                    }

                } else {
                    System.out.println("Buku dengan Kode "+ kode +" tidak tersedia");
                }

                System.out.println();
                System.out.print("Ketik 0 selesai | 1 lanjut transaksi : ");
                int selesai = Integer.parseInt(input.readLine());
                if (selesai == 0) {
                    keluar = true;
                }
            } catch (Exception e) {
                System.out.println("Inputan salah..");
//                e.printStackTrace();
            }

        } while (!keluar);

        for (CartTemporer trx : trans) {
            cartController.addTransaksi(trx.getKode_buku(), trx.getJumlah(), kode_trans);
        }

        if (trans.size() > 0){
            System.out.println("                         Terimakasih, Selamat Datang Kemnali");
            cartController.getInvoice(kode_trans);
        } else {
            System.out.println("Terimakasih atas kunjungan anda.");
        }

    }

    static void transaksi(){

        cartController.allTransaksi();
        try {

            System.out.print("Masukan kode transaksi untuk melihat detail: ");
            String kodeTransaksi = input.readLine();
            cartController.getInvoice(kodeTransaksi);

        } catch (Exception e){
            System.out.println("Transaksi tidak di temukan");
        }

    }

    // not found message
    static void notFound(String kode){
        System.out.println("Buku dengan Kode: "+kode+" tidak ditemukan.");
    }
}
