package br.com.alura.agenda;

import android.app.Application;

import androidx.room.Room;

import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.database.DatabaseConstantes.AGENDA_DATABASE;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AgendaDatabase database = Room.databaseBuilder(this, AgendaDatabase.class, AGENDA_DATABASE).build();
        RoomAlunoDAO dao = database.getRoomAlunoDAO();
        dao.salva(new Aluno("Roger", "1122223333", "alex@alura.com.br"));
        dao.salva(new Aluno("Raquel", "1122223333", "fran@gmail.com"));
    }
}
