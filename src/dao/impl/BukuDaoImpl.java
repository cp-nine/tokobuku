package dao.impl;

import config.MyConnection;
import dao.BukuDao;
import database.DataBuku;
import entities.Buku;

import java.util.ArrayList;
import java.util.List;

public class BukuDaoImpl implements BukuDao {

    private static DataBuku dataBuku;

    public BukuDaoImpl() {
        dataBuku = new DataBuku(MyConnection.makeConnection());
    }

    @Override
    public List<Buku> getAll() {
        List<Buku> list = new ArrayList<>();

        try {
            list = dataBuku.getAllBuku();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Buku getById(Integer id) {
        return dataBuku.getById(id);
    }

    @Override
    public Buku getByKodeBuku(String kode) {
        return dataBuku.getDetailBuku(kode);
    }

    @Override
    public void addBuku(Buku buku) {
        dataBuku.insertBuku(buku);
    }

    @Override
    public void updateStokBuku(String kode, int jumlah) {
        Buku upbuku = getByKodeBuku(kode);
        Buku newBuku = new Buku();
        newBuku.setId(upbuku.getId());
        newBuku.setStok(upbuku.getStok() - jumlah);

        dataBuku.updateStokBuku(newBuku);
    }

    @Override
    public void updateBuku(Buku buku) {
        dataBuku.updateBuku(buku);
    }

    @Override
    public void deleteBuku(String kode) {
        dataBuku.deleteBuku(kode);
    }

    @Override
    public void restoreBuku(String kode) {
        dataBuku.restoreBuku(kode);
    }

}
