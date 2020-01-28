package br.com.alura.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.database.DatabaseConstantes.AGENDA_DATABASE;

@Database(entities = Aluno.class,version = 2, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, AGENDA_DATABASE)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                })
                .build();
    }
}
