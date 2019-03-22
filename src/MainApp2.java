import controller.BukuController;
import controller.CartController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainApp2 {

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    static BukuController bukuController = new BukuController();
    static CartController cartController = new CartController();

    public static void main(String[] args) {

        try {
            System.out.println("---- Login ----");
            System.out.print("Username : ");
            String username = input.readLine();
            System.out.print("Password : ");
            String password = input.readLine();



        } catch (Exception e){
            System.out.println("Username atau password salah");
        }

//        mainMenu();

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
//                            detailBuku(kode);
                        } else if (menuSelect == 2) {
//                            addBuku();
                        } else if (menuSelect == 3) {
//                            editBuku();
                        } else if (menuSelect == 4) {
                            System.out.println();
                            System.out.print("Masukan Kode Buku : ");
                            String kodeHapus = input.readLine();
                            System.out.print("Apakah anda yakin akan menghapus buku ini ? (y/n) : ");
                            String confirm = input.readLine();
                            if(confirm=="y"){
//                                deleteBuku(kodeHapus);
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
//                                restoreBuku(kodeRestore);
                            } else if(confirm=="n"){
                                System.out.println("Kembali ke mnu utama!!");
                            } else {
                                System.out.println("Inputan salah, restore buku gagal..!!!");
                            }
                        } else if (menuSelect == 6) {
                            System.out.println("Lakukan Transaksi ");
//                            addTransaksi();
                        } else if (menuSelect == 7) {
                            System.out.println("Data Transaksi");
                            cartController.allTransaksi();
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



    // not found message
    static void notFound(String kode){
        System.out.println("Buku dengan Kode: "+kode+" tidak ditemukan.");
    }
}
