package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.conversor.ConversorCalendar;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.database.DatabaseConstantes.AGENDA_DATABASE;
import static br.com.alura.agenda.database.DatabaseMigrations.TODAS_MIGRATIONS;

@Database(entities = Aluno.class,version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {


    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, AGENDA_DATABASE)
                .allowMainThreadQueries()
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
