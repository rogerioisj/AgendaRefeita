package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO dao;
    private final TextView telefone;
    private final int alunoId;

    public BuscaPrimeiroTelefoneTask(TelefoneDAO dao, TextView telefone, int alunoId) {
        this.dao = dao;
        this.telefone = telefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        telefone.setText(primeiroTelefone.getNumero());
    }
}
