/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.XDate;
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
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {
    java.sql.Date sqlDate;
    String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH,HoTen,GioiTinh,NgaySinh,DienThoai,Email,GhiChu,MaNV,NgayDK) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen =? ,GioiTinh = ?, NgaySinh = ?,DienThoai=?,Email=?,GhiChu=?,MaNV=?,NgayDK=? WHERE MaNH =?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH =?";
    String SELECT_ALL_SQL ="SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH = ?"; 
    
    @Override
    public void insert(NguoiHoc entity) {
        XJdbcHelper.update(INSERT_SQL, entity.getMaNH(),entity.getHoTen(),entity.getGioiTinh(),entity.getNgaySinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
    }
    
    public Date convertDate(java.util.Date date){
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    @Override
    public void update(NguoiHoc entity) {
        XJdbcHelper.update(UPDATE_SQL, entity.getHoTen(),entity.getGioiTinh(),entity.getNgaySinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(),entity.getMaNH());
    }

    @Override
    public void delete(String id) {
        XJdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbcHelper.query(sql, args);
                while(rs.next()){
                NguoiHoc nv = new NguoiHoc(rs.getString("MaNH"),rs.getString("HoTen"),rs.getBoolean("GioiTinh"),rs.getDate("NgaySinh"),rs.getString("DienThoai"),rs.getString("Email"),rs.getString("GhiChu"),rs.getString("MaNV"),rs.getDate("NgayDK"));
                list.add(nv);
            }
            } finally {
                rs.getStatement().getConnection().close();
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeywords(String key){
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySQL(sql, "%"+key+"%");
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql="SELECT * FROM NguoiHoc "
                + " WHERE HoTen LIKE ? AND "
                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.selectBySQL(sql, "%"+keyword+"%", makh);
    }
}
