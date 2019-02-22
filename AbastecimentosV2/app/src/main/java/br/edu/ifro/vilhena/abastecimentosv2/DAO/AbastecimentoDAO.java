package br.edu.ifro.vilhena.abastecimentosv2.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AbastecimentoDAO extends SQLiteOpenHelper {
    public AbastecimentoDAO(Context context) {
        super(context, "Abastecimentos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table abastecimentos (id integer primary key, nomePosto text, tipocombustivel integer, quilometragem real, valorLitro real, quantLitros real, total real, data text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
