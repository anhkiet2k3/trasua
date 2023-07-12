/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.XJdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;



public class NhanVienDAO extends EduSysDAO<NhanVien, String> {
     String INSERT_SQL = "INSERT INTO NhanVien(MaNV,MatKhau,HoTen,VaiTro) VALUES(?,?,?,?)";
     String UPDATE_SQL = "UPDATE NhanVien SET MatKhau =? ,HoTen = ?, VaiTro = ? WHERE MaNV =?";
     String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV =?";
     String SELECT_ALL_SQL ="SELECT * FROM NhanVien";
     String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?"; 
    
    @Override
    public void insert(NhanVien entity) {
        XJdbcHelper.update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.getVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbcHelper.update(UPDATE_SQL, entity.getMatKhau(),entity.getHoTen(),entity.getVaiTro(),entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        XJdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbcHelper.query(sql, args);
                while(rs.next()){
                    NhanVien nv = readFromResultSet(rs);
                    list.add(nv);
                }
                
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }
    
    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("MaNV"));
        nv.setMatKhau(rs.getString("MatKhau"));
        nv.setHoTen(rs.getString("HoTen"));
        nv.setVaiTro(rs.getBoolean("VaiTro"));
        return nv;
    }
    
}
