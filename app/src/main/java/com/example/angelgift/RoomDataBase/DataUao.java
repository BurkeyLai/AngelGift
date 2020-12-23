package com.example.angelgift.RoomDataBase;

import android.provider.ContactsContract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataUao {
    String tableName = "MyTable";
    /**=======================================================================================*/
    /**簡易新增所有資料的方法*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)//預設萬一執行出錯怎麼辦，REPLACE為覆蓋
    void insertData(MyData myData);

    /**複雜(?)新增所有資料的方法*/
    @Query("INSERT INTO " + tableName + "(date, class_id, number1, number2, number3, number4, number5) " +
            "VALUES(:date, :class_id, :number1, :number2, :number3, :number4, :number5)")
    void insertData(String date, int class_id, int number1, int number2, int number3, int number4, int number5);

    /**=======================================================================================*/
    /**撈取全部資料*/
    @Query("SELECT * FROM " + tableName)
    List<MyData> displayAll();

    /**撈取某個日期的相關資料*/
    @Query("SELECT * FROM " + tableName +" WHERE date = :date")
    List<MyData> findDataByDate(String date);

    /**撈取某個類別的相關資料*/
    @Query("SELECT * FROM " + tableName +" WHERE class_id = :class_id")
    List<MyData> findDataByClassID(int class_id);

    /**撈取某個類別的相關資料*/
    @Query("SELECT * FROM " + tableName +" WHERE date = :date AND class_id = :class_id")
    List<MyData> findDataByDateAndClassID(String date, int class_id);

    /**=======================================================================================*/
    /**簡易更新資料的方法*/
    //@Update
    //void updateData(MyData myData);

    /**複雜(?)更新資料的方法*/
    //@Query("UPDATE " + tableName + " SET date = :date, class_id = :class_id, " +
    //        "number1 = :number1, number2 = :number2, number3 = :number3, number4 = :number4, number5 = :number5 WHERE id = :id" )
    //void updateData(int id, int date, int class_id, int number1, int number2, int number3, int number4, int number5);

    /**=======================================================================================*/
    /**簡單刪除資料的方法*/
    @Delete
    void deleteData(MyData myData);

    /**複雜(?)刪除資料的方法*/
    //@Query("DELETE  FROM " + tableName + " WHERE daily_id = :daily_id")
    //void deleteDataByDailyId(int daily_id);

    /**複雜(?)刪除資料的方法*/
    @Query("DELETE  FROM " + tableName + " WHERE date = :date")
    void deleteDataByDate(String date);

    /**複雜(?)刪除資料的方法*/
    @Query("DELETE  FROM " + tableName + " WHERE class_id = :class_id")
    void deleteDataByClassID(int class_id);

    /**複雜(?)刪除資料的方法*/
    @Query("DELETE  FROM " + tableName + " WHERE date = :date AND class_id = :class_id")
    void deleteDataByDateAndClassID(String date, int class_id);
}
