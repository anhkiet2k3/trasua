/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.XJdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhatt
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    String INSERT_SQL = "INSERT INTO KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV) VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD =? ,HocPhi = ?, ThoiLuong = ?,NgayKG=?,GhiChu=?,MaNV=? WHERE MaKH =?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH =?";
    String SELECT_ALL_SQL ="SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH = ?"; 
    
    @Override
    public void insert(KhoaHoc entity) {
        XJdbcHelper.update(INSERT_SQL, entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV());
    }
    
    java.sql.Date sqlDate;
    public Date convertDate(java.util.Date date){
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    @Override
    public void update(KhoaHoc entity) {
        XJdbcHelper.update(UPDATE_SQL, entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        XJdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = null;
            try {
                rs =XJdbcHelper.query(sql, args);
                while(rs.next()){
                KhoaHoc kh = new KhoaHoc(rs.getInt("MaKH"),rs.getString("MaCD"),rs.getDouble("HocPhi"),rs.getInt("ThoiLuong"),rs.getDate("NgayKG"),rs.getString("GhiChu"),rs.getString("MaNV"),rs.getDate("NgayTao"));
                list.add(kh);
            }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<KhoaHoc> selectByChuyenDe(String maCD){
        String sql="SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySQL(sql, maCD);
    }
    
    public List<Integer> selectYears() {
        String sql="SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list=new ArrayList<>();
        try {
           ResultSet rs = XJdbcHelper.query(sql);
           while(rs.next()){
                 list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
