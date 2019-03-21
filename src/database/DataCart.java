package database;

import entities.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataCart {

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement pst;

    public DataCart(Connection conn) {
        this.conn = conn;
    }

    public List<Cart> getAllTransaksi(){

        List<Cart> list = null;

        try {

            pst = conn.prepareStatement("SELECT * FROM tb_transaksi");
            rs = pst.executeQuery();

            if(!rs.wasNull()){
                list = new ArrayList<>();
                while (rs.next()){
                    Cart trx = new Cart();

                    trx.setId_transaksi(rs.getInt("id_transaksi"));
                    trx.setId_buku(rs.getInt("id_buku"));
                    trx.setQty(rs.getInt("jumlah"));
                    trx.setTotal_harga(rs.getInt("total_harga"));
                    trx.setNo_transaksi(rs.getString("no_penjualan"));
                    list.add(trx);
                }
            } else {
                System.out.println("Data tidak tersedia");
            }

            pst.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public List<Cart> getTransaksi(String npj){

        List<Cart> list = new ArrayList<>();

        try {

            pst = conn.prepareStatement("SELECT * FROM tb_transaksi WHERE no_penjualan=?");
            pst.setString(1,npj);
            rs = pst.executeQuery();

            while (rs.next()){
                Cart trx = new Cart();

                trx.setId_transaksi(rs.getInt("id_transaksi"));
                trx.setId_buku(rs.getInt("id_buku"));
                trx.setQty(rs.getInt("jumlah"));
                trx.setTotal_harga(rs.getInt("total_harga"));
                trx.setNo_transaksi(rs.getString("no_penjualan"));
                list.add(trx);
            }

            pst.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }



    // add cart
    public void insertTransaksi(Cart cart, String kodetrans){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-mm-dd");

        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("INSERT INTO tb_transaksi (id_buku, jumlah, total_harga, no_penjualan, tgl_transaksi) " +
                    "VALUE (?, ?, ?, ?, ?)");
            pst.setInt(1, cart.getId_buku());
            pst.setInt(2, cart.getQty());
            pst.setInt(3, cart.getTotal_harga());
            pst.setString(4, kodetrans);
            pst.setDate(5, java.sql.Date.valueOf(ft.format(dNow)));
            // simpan siswa
            pst.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
