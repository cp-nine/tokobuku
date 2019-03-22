package database;

import config.Code;
import entities.Buku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBuku {

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement pst;

    public DataBuku(Connection conn) {
        this.conn = conn;
    }

    public List<Buku> getAllBuku(){

        List<Buku> list = new ArrayList<>();

        try {

            pst = conn.prepareStatement("SELECT * FROM tb_buku WHERE flag_hapus=0 ORDER BY kode_buku ASC");
            rs = pst.executeQuery();

            while (rs.next()){
                Buku allBuku = new Buku();

                allBuku.setId(rs.getInt("id_buku"));
                allBuku.setJudul(rs.getString("judul"));
                allBuku.setPenulis(rs.getString("penulis"));
                allBuku.setPenerbit(rs.getString("penerbit"));
                allBuku.setHarga(rs.getInt("harga"));
                allBuku.setStok(rs.getInt("stock"));
                allBuku.setKode_buku(rs.getString("kode_buku"));
                list.add(allBuku);
            }

            pst.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public Buku getDetailBuku(String idBuku){

        Buku buku = null;

        try {

            pst = conn.prepareStatement("SELECT * FROM tb_buku WHERE kode_buku=? ");
            pst.setString(1, idBuku);
            rs = pst.executeQuery();

            if (!rs.wasNull()){
                while (rs.next()){

                    buku = new Buku();
                    buku.setId(rs.getInt("id_buku"));
                    buku.setJudul(rs.getString("judul"));
                    buku.setPenulis(rs.getString("penulis"));
                    buku.setPenerbit(rs.getString("penerbit"));
                    buku.setHarga(rs.getInt("harga"));
                    buku.setStok(rs.getInt("stock"));
                    buku.setKode_buku(rs.getString("kode_buku"));
                }
            }

            pst.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return buku;

    }

    public Buku getById(Integer id){

        Buku buku = null;

        try {

            pst = conn.prepareStatement("SELECT * FROM tb_buku WHERE id_buku=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (!rs.wasNull()){
                while (rs.next()){

                    buku = new Buku();
                    buku.setId(rs.getInt("id_buku"));
                    buku.setJudul(rs.getString("judul"));
                    buku.setPenulis(rs.getString("penulis"));
                    buku.setPenerbit(rs.getString("penerbit"));
                    buku.setHarga(rs.getInt("harga"));
                    buku.setStok(rs.getInt("stock"));
                    buku.setKode_buku(rs.getString("kode_buku"));
                }
            }

            pst.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return buku;

    }



    // add buku
    public void insertBuku(Buku buku){

        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("INSERT INTO tb_buku (judul, penulis, penerbit, harga, stock, kode_buku) " +
                    "VALUE (?, ?, ?, ?, ?, ?)");
            pst.setString(1, buku.getJudul());
            pst.setString(2, buku.getPenulis());
            pst.setString(3, buku.getPenerbit());
            pst.setInt(4, buku.getHarga());
            pst.setInt(5, buku.getStok());
            pst.setString(6, getKode());

            // simpan siswa
            pst.execute();

            System.out.println("Tambah buku berhasil");

        } catch (Exception e) {
            System.out.println("Tambah buku gagal");
        }
    }

    public void updateStokBuku(Buku buku){
        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("UPDATE tb_buku SET stock=? WHERE id_buku=? ");
            pst.setInt(1, buku.getStok());
            pst.setInt(2, buku.getId());

            // update
            pst.execute();

            System.out.println("Update stok buku berhasil");
        } catch (Exception e) {
            System.out.println("Update stok buku berhasil");
        }
    }

    // update buku
    public void updateBuku(Buku buku){
        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("UPDATE tb_buku SET judul=?, penulis=?, penerbit=?, harga=?, stock=? WHERE kode_buku=? ");
            pst.setString(1, buku.getJudul());
            pst.setString(2, buku.getPenulis());
            pst.setString(3, buku.getPenerbit());
            pst.setInt(4, buku.getHarga());
            pst.setInt(5, buku.getStok());
            pst.setString(6, buku.getKode_buku());

            // update
            pst.execute();

            System.out.println("Update buku berhasil");
        } catch (Exception e) {
            System.out.println("Update buku gagal");
        }
    }

    // delete buku
    public void deleteBuku(String kodebuku){
        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("UPDATE tb_buku SET flag_hapus=? WHERE kode_buku=? ");
            pst.setInt(1, 1);
            pst.setString(2, kodebuku);

            // update
            pst.execute();

            System.out.println("Delete buku berhasil");
        } catch (Exception e) {
            System.out.println("Delete buku gagal");
        }
    }

    // restore buku
    public void restoreBuku(String kodebuku){
        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("UPDATE tb_buku SET flag_hapus=? WHERE kode_buku=? ");
            pst.setInt(1, 0);
            pst.setString(2, kodebuku);

            // update
            pst.execute();

            System.out.println("Restore berhasil");
        } catch (Exception e) {
            System.out.println("Restore gagal");
        }
    }

    // restore buku
    public void updateStok(String kodebuku, int jumlah){
        try {

            // prepare statement query, (menghindari sql injection)
            pst = conn.prepareStatement("UPDATE tb_buku SET stock=? WHERE kode_buku=? ");
            pst.setInt(1, 0);
            pst.setString(2, kodebuku);

            // update
            pst.execute();

            System.out.println("Update stok berhasil");
        } catch (Exception e) {
            System.out.println("Update stok gagal");
        }
    }

    // get kode
    public String getKode(){
        String lastValue = "";
        try {
            Code kode = new Code();
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(id_buku)+1 FROM tb_buku");
            String lastIndex = "00000";
            while (rs.next()){
                lastIndex = lastIndex+rs.getString(1);
            }
            lastValue = kode.makeCode("BK",lastIndex,6);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastValue;
    }
}
