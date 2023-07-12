/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.XJdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhatt
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD =? ,HocPhi = ?, ThoiLuong = ?,Hinh=?,MoTa=? WHERE MaCD =?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD =?";
    String SELECT_ALL_SQL ="SELECT * FROM ChuyenDe";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD = ?"; 
    
    @Override
    public void insert(ChuyenDe entity) {
        XJdbcHelper.update(INSERT_SQL, entity.getMaCD(),entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        XJdbcHelper.update(UPDATE_SQL, entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(),entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        XJdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbcHelper.query(sql, args);
                while(rs.next()){
                ChuyenDe cd = new ChuyenDe(rs.getString("MaCD"),rs.getString("TenCD"),rs.getDouble("HocPhi"),rs.getInt("ThoiLuong"),rs.getString("Hinh"),rs.getString("MoTa"));
                list.add(cd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<ChuyenDe> selectByKeywords(String key){
        String sql = "SELECT * FROM ChuyenDe WHERE HoTen LIKE ?";
        return this.selectBySQL(sql, "%"+key+"%");
    }
    
}
