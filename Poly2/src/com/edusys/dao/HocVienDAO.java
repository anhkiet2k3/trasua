/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.XJdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public  class HocVienDAO extends EduSysDAO<HocVien, Integer> {
    String INSERT_SQL = "INSERT INTO HocVien(MaKH,MaNH,Diem) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE HocVien SET MaKH =? ,MaNH = ?, Diem = ? WHERE MaHV =?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV =?";
    String SELECT_ALL_SQL ="SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV = ?"; 
    
    @Override
    public void insert(HocVien entity) {
        XJdbcHelper.update(INSERT_SQL, entity.getMaKH(),entity.getMaNH(),entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        XJdbcHelper.update(UPDATE_SQL, entity.getMaKH(),entity.getMaNH(),entity.getDiem(),entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        XJdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<HocVien>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbcHelper.query(sql, args);
                while(rs.next()){
                HocVien hv = new HocVien(rs.getInt("MaHV"),rs.getInt("MaKH"),rs.getString("MaNH"),rs.getDouble("Diem"));
                list.add(hv);
                }
            } finally {
                rs.getStatement().getConnection().close();
            } 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql="SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySQL(sql, maKH);
    }
}
