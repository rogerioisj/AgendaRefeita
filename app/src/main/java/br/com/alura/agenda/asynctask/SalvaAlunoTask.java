package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends AsyncTask<Void, Void, Void> {
private final RoomAlunoDAO alunoDAO;
private final TelefoneDAO telefoneDAO;
private final Telefone telefoneFixo;
private final Telefone telefoneCelular;
private final Aluno aluno;
private final QuandoAlunoSalvoListener listener;

    public SalvaAlunoTask(RoomAlunoDAO alunoDAO,
                          TelefoneDAO telefoneDAO,
                          Telefone telefoneFixo,
                          Telefone telefoneCelular, Aluno aluno, QuandoAlunoSalvoListener listener) {
        this.alunoDAO = alunoDAO;
        this.telefoneDAO = telefoneDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.aluno = aluno;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoSalvo();
    }

    private void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface QuandoAlunoSalvoListener{
        void quandoSalvo();
    }
}
